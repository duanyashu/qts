package com.qts.service.impl;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qts.dao.AssayMapper;
import com.qts.dao.RegisterMapper;
import com.qts.dao.RuleMergeMapper;
import com.qts.dao.SampleMapper;
import com.qts.entity.SampleSaoMaVo;
import com.qts.pojo.*;
import com.qts.request.SampleForm;
import com.qts.response.LayuiData;
import com.qts.response.ReslutData;
import com.qts.service.ISampleService;
import com.qts.utils.IdUtils;
import com.qts.websocket.SampleWebsocketHandler;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.IdGenerator;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @description: 采样服务层
 * @author: duanyashu
 * @time: 2020-07-03 15:46
 */
@Service
public class SampleServiceImpl implements ISampleService {

    //刷卡成功
    private final static int SHUAKA_SUCCESS=1;
    //刷卡错误
    private final static int SHUAKA_ERROR=2;
    //扫码成功
    private final static int SAOMA_SUCCESS=3;
    //扫码错误
    private final static int SAOMA_ERROR=4;
    //扫码已合样
    private final static int SAOMA_MERGE=5;


    @Autowired
    SampleMapper sampleMapper;
    @Autowired
    RegisterMapper registerMapper;
    @Autowired
    RuleMergeMapper ruleMergeMapper;
    @Autowired
    AssayMapper assayMapper;

    @Override
    public void data() {
        loadSampleData(SHUAKA_SUCCESS);
    }

    /**
     * 实现刷卡操作的方法
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String shuaka(String id) {
     User user = (User) SecurityUtils.getSubject().getPrincipal();
     String createId=user.getId();
    //获取当前用户的厂区信息
    Session session = SecurityUtils.getSubject().getSession();
    List<Branch> branches = (List<Branch>) session.getAttribute("branchs");
    Branch branch=branches.get(0);
        //查询数据是否正确，是否存在
        Register register = registerMapper.selectByPrimaryKey(id);
        if (register==null){
            //提醒页面播报无效卡的语音
            loadSampleData(SHUAKA_ERROR);
            return "-1";
        }
        //判断卡是否已经刷过
        Sample sample = sampleMapper.selectByLinkId(register.getLinkid());
        if (sample!=null){
            //重新打印二维码
            return sample.getSampleCode();
        }
        //拼装要插入数据库的数据
        //获取合样规则
        RuleMerge ruleMerge = getRuleMerge(register.getSupplier(), register.getCoaltype(), createId);
        //实现插入
        int shifts=8;
        //采样码  A0002  厂区编码+4位数（1-9999）
        String sampleNum = generaterSampleNum(branch.getBranchCode());
        String sampleCode =branch.getBranchCode()+ sampleNum;
        //合样码 A812120002   厂区编号+班次（0,8,4）+当前日期+四位数编号
        String mergeCode=branch.getBranchCode()+shifts+"1210"+sampleNum;
        //======判断是否需要使用新的合样码=====
        /**
         * 当前来源地的所有采样数%合样规则数==0  重新生成   ！=0 直接使用最新组的合样码
         * 采样数    合样码
         */
        Map<String, Object> map = sampleMapper.selectLastMergeCodeAndCount(branch.getBranchCode(), ruleMerge.getId());
        long sampleCodeNum = (long) map.get("count");
        //判断是否需要新建合样组
        if (sampleCodeNum%ruleMerge.getCount()!=0){
            mergeCode = (String) map.get("mergeCode");
        }
        addSample(sampleCode,register,branch,mergeCode,createId,shifts,ruleMerge);
        //通知页面刷新数据
        loadSampleData(SHUAKA_SUCCESS);
        return sampleCode;
    }

    /**
     * 生成采样编码
     * @param branchCode
     * @return
     */
    private String generaterSampleNum(String branchCode){
            //需要查询数据库中的最大数 +1   A0016
        String maxSampleCode = sampleMapper.selectMaxSampleCode(branchCode);
        int maxSampleNum = 1;
        //如果数据库中已存在编码 就在原来的基础上加1
        if (!StringUtils.isEmpty(maxSampleCode)){
            //0016+1  17
            maxSampleNum =Integer.parseInt(maxSampleCode.substring(1))+1;
        }
        //判断是否超过最大值
        maxSampleNum = maxSampleNum>9999? 1:maxSampleNum;
        //将数字10 格式成0010  %04d 是指定转为4位 不够位数补0
        return String.format("%04d",maxSampleNum);

    }

    /**
     * 获取合样规则方法
     * @param sender  来源地
     * @param type   类型
     * @return
     */
    private RuleMerge getRuleMerge(String sender,String type,String createId){
        RuleMerge ruleMerge = ruleMergeMapper.selectBySender(sender, type);
        if (ruleMerge==null){
            //设置默认值
            ruleMerge = new RuleMerge();
            ruleMerge.setId(IdUtils.getId());
            ruleMerge.setCount(3);
            ruleMerge.setSender(sender);
            ruleMerge.setType(type);
            ruleMerge.setCreateId(createId);
            ruleMerge.setCreateTime(new Date());
            ruleMergeMapper.insertSelective(ruleMerge);
        }
        return  ruleMerge;
    }

    /**
     * 添加采样数据
     * @param sampleCode  采样码
     * @param register  原始数据对象
     * @param branch    厂区对象
     * @param mergeCode   合样码
     * @param createId   添加人
     * @param shifts     班次
     * @param ruleMerge  合样规则
     */
    public void addSample(String sampleCode, Register register,Branch branch, String mergeCode,
                          String createId, int shifts, RuleMerge ruleMerge){

        Sample sample = new Sample();
        sample.setId(IdUtils.getId());
        sample.setSampleCode(sampleCode);
        sample.setLinkId(register.getLinkid());
        sample.setSender(register.getSupplier());
        sample.setType(register.getCoaltype());
        sample.setCargoname(register.getCargoname());
        sample.setCarnum(register.getCarnum());
        sample.setBranchName(branch.getBranchName());
        sample.setState(false);
        sample.setMergeState(false);
        sample.setMergeCode(mergeCode);
        sample.setMergeRule(ruleMerge.getCount());
        sample.setCreateId(createId);
        sample.setShifts(shifts);
        sample.setCreateTime(new Date());
        sample.setBranchCode(branch.getBranchCode());
        sample.setRuleMergeId(ruleMerge.getId());
        sampleMapper.insertSelective(sample);
    }

    /**
     * 扫码的方法
     * @param sampleCode
     * @return
     */
    @Override
    public String saoma(String sampleCode) {
        SampleSaoMaVo saoMaVo = sampleMapper.selectSaomaData(sampleCode);
        //判断二维码是否正确 ，是否已扫码
        if (saoMaVo==null){
            loadSampleData(SAOMA_ERROR);
            return "-1";
        }
        //判断是否已合样
        if (saoMaVo.getMergeState()){
            loadSampleData(SAOMA_MERGE);
            return "-1";
        }
        //已扫码
        if(saoMaVo.getState()){
            return "-1";
        }
        //判断是合样组中的最后一个采样码
        //状态state为1的个数 3  +1==4
         if(saoMaVo.getStateCount()+1<saoMaVo.getMergeRule()){
             //修改当前采样码的状态为已扫码
             Sample sample = new Sample();
             sample.setId(saoMaVo.getId());
             sample.setState(true);
             sampleMapper.updateByPrimaryKeySelective(sample);
             loadSampleData(SHUAKA_SUCCESS);
             return "-1";
         }
         //如果是扫的最后一个码 需要将当前合样码下的采样码的状态都改为已合样
        sampleMapper.updateMergeStateByMergeCode(saoMaVo.getMargeCode(),true,true);
        //最终需要将数据推送到化验表
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        addAssay(saoMaVo, user.getId());
        loadSampleData(SHUAKA_SUCCESS);
        return saoMaVo.getMargeCode();
    }

    /**
     * 添加化验数据
     * @param saoMaVo 添加对象
     * @param userId
     */
    private void addAssay(SampleSaoMaVo saoMaVo, String userId){
        Assay assay = new Assay();
        assay.setId(IdUtils.getId());
        assay.setMargeCode(saoMaVo.getMargeCode());
        assay.setSender(saoMaVo.getSender());
        assay.setType(saoMaVo.getType());
        assay.setRuleMergeId(saoMaVo.getRuleMergeId());
        assay.setBranchCode(saoMaVo.getBranchCode());
        assay.setSampleCodes(saoMaVo.getSampleCodes());
        assay.setMargeCarnums(saoMaVo.getMargeCarnums());
        assay.setState(0);
        assay.setTemplateId("1");
        assay.setTemplateName("原料煤");
        assay.setCreateTime(new Date());
        assay.setCreateId(userId);
        assayMapper.insertSelective(assay);
    }

    /**
     * 实现采样控制台推送数据方法
     * @param code
     */
    private void loadSampleData(int code){
        //获取用户id
        User user =(User)SecurityUtils.getSubject().getPrincipal();
        String userId= user.getId();
        //获取当前用户的厂区信息
        Session session = SecurityUtils.getSubject().getSession();
        List<Branch> branches = (List<Branch>) session.getAttribute("branchs");
        List<Map<String,Object>> sampleData = sampleMapper.selectConsoleData(branches.get(0).getBranchCode());
        //结果集对象
        ReslutData reslutData = new ReslutData(code, null, sampleData);
        try {
            //使用jackson将对象转为json字符串
            ObjectMapper objectMapper = new ObjectMapper();
            String data = objectMapper.writeValueAsString(reslutData);
            //调用websocket发送数据
            SampleWebsocketHandler.sendByUserId(userId,data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public LayuiData list(SampleForm sampleForm) {
        List<Sample> sampleList = sampleMapper.selectSampleList(sampleForm);
        return new LayuiData(sampleList,sampleMapper.selectSampleListCount(sampleForm));
    }
}
