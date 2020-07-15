package com.qts.controller;

import com.alibaba.excel.EasyExcel;
import com.github.duanyashu.EasyExcelUtils;
import com.qts.dao.UserMapper;
import com.qts.entity.MenuVo;
import com.qts.pojo.User;
import com.qts.pojo.UserImpost;
import com.qts.request.UserForm;
import com.qts.response.ReslutData;
import com.qts.service.IUserService;
import com.qts.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 登录控制器
 * @author: duanyashu
 * @time: 2020-07-02 11:58
 */
@Controller
public class LoginController {

    Logger log  = LoggerFactory.getLogger(LoggerFactory.class);

    @Autowired
    IUserService userService;
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/")
    public String index(){
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public ReslutData login(String username,String password){
        return userService.login(username,password);

    }

    @RequestMapping("/index")
    public String main(Model model){
        List<MenuVo> menuVoList = userService.getMenuTree();
        model.addAttribute("menus",menuVoList);
        return "main";
    }

    @RequestMapping("/file")
    @ResponseBody
    public ReslutData uploadFile(HttpServletResponse response,MultipartFile file){
        try {
            EasyExcelUtils.webImportExcel(response,file.getInputStream(), UserImpost.class);
            return ReslutData.success();
        } catch (Exception e) {
            return  ReslutData.fail("导入失败：错误"+e.getMessage());
        }
    }

    @RequestMapping("/poi")
    public void poiDownload(HttpServletResponse response) throws IOException {
         response.setCharacterEncoding("utf-8");
         //指定下载文件的类型
         response.setContentType("application/vnd.ms-excel");
         response.setHeader("Content-disposition","attachment;filename="+ URLEncoder.encode("用户表.xls","UTF-8"));
        UserForm userForm = new UserForm();
        userForm.setPageSize(100000000);
        List<User> userList = userMapper.selectAll(userForm);
        List<List<String>> rowList = new ArrayList<>();
        for (User user : userList) {
            List<String> cellList = new ArrayList<>();
            cellList.add(user.getUsername());
            cellList.add(user.getRealName());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            cellList.add(dateFormat.format(user.getCreateTime()));
            //将创建的当前行放入行列表中
            rowList.add(cellList);
        }
        //创建excel
        Workbook workbook = ExcelUtil.creatExcel(rowList, new String[]{"账号", "用户名", "创建时间"}, "用户表");
        //实现输出
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("/easyExcel")
    public void easyExcelDownload(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        //指定下载文件的类型
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("用户表.xls", "UTF-8"));
        UserForm userForm = new UserForm();
        userForm.setPageSize(100000000);
        List<User> userList = userMapper.selectAll(userForm);
        log.info("【easyExcel下载用户信息】："+userList.toString());
        //实现创建excel导出
        EasyExcel.write(response.getOutputStream(), User.class).sheet("用户信息").doWrite(userList);
    }
    }
