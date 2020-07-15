<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/7/3
  Time: 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>采样操作台</title>
    <link rel="stylesheet" href="${ctx}/plugins/front/extend/layui/css/layui.css">
    <link rel="stylesheet"href="${ctx}/plugins/front/extend/layui/style.css">
    <script src="${ctx}/plugins/front/extend/layui/layui.js"></script>
    <script type="text/javascript" src="${ctx}/plugins/front/extend/assets/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/plugins/front/extend/assets/js/playVoice.js"></script>
    <link rel="stylesheet" href="${ctx}/plugins/front/extend/assets/css/samping.css">
    <script type="text/javascript">
            var ws =new WebSocket('ws://${pageContext.request.serverName}:${pageContext.request.serverPort}/${ctx}/sampleWebSocket');
            //连接成功
            ws.onopen = function(){
               $.getJSON('${ctx}/sample/data');
            };
            //接收到数据
            ws.onmessage = function(evt){
                //websocket传过来的参数的是字符串 需要通过JSON进行转为json对象
               var obj = JSON.parse(evt.data);
               switch (obj.code) {
                   //从新加载数据
                 case 1:
                     loadData(obj.data);
                     break;
                    //刷卡错误
                 case 2:
                     playVoice('${ctx}/plugins/front/voice/emptyCard.mp3');
                     break;
                    //扫码错误
                 case 4:
                     playVoice('${ctx}/plugins/front/voice/codeError.mp3');
                     break;
                     // 编码已合样
                     playVoice('${ctx}/plugins/front/voice/yiheyang.mp3');
                 case 5:
                     break;
               }
            }

     //加载数据
     var readSampleListCopy = new Array();
    function loadData(dataList){
        //清空页面ui标签
        $('#tbody').empty();
        //保存需要进行语音提示的采样码
        var readSampleList = new Array();
        for (var i = 0; i < dataList.length; i++) {
           var data = dataList[i];
           //获取采样总数
           var sampleCount = data.sampleCount;
           //获取所有的采样码
           var sampleCodes = data.sampleCodes;
           //获取采样码的状态 （0 刷卡  1 扫码）
           var states = data.state;
           //合样规则数
           var mergeRule = data.mergeRule;
            //创建li标签
            var li = $('<li></li>');
            //判断是否达到合样条件
            if (sampleCount==mergeRule){
                li.addClass('hy');
                //将需要进行语音播报的数据放入数组   /,替换，/g 替换全部
                readSampleList.push(sampleCodes.replace(/,/g,''));
            }
            //采样码数组
           var sampleCodeList =  sampleCodes.split(',');
           //状态码数组
            var  satateList = states.split(',');
            for (var j = 0; j <sampleCodeList.length ; j++) {
                var span = $('<span>'+sampleCodeList[j]+'</span>');
                //判断状态 是否达到扫码
                if(satateList[j]==1){
                    span.addClass('on');
                }
                //放入li标签
                li.append(span);
            }
            //添加数据
            $('#tbody').append(li);
        }
        //判断是否需要重新生成播放的语音文件
        if (readSampleListCopy.length!=readSampleList.length){
            readSampleListCopy=readSampleList;
            //结束上传的循环
            clearInterval(controlVoiceIndex);
             fomterReadList();
        }
    }

            //拼装最终播放的语音字符列表
            var playDataList = new Array();
            var controlVoiceIndex;
           function fomterReadList(){
               //清空播放列表
               playDataList.length=0;
               for (var i = 0; i <readSampleListCopy.length ; i++) {
                   //拼装开始语音
                   playDataList.push('start');
                   //添加播放的采样码  concat 合并数组
                  playDataList = playDataList.concat(readSampleListCopy[i].split(''));
                   //拼装结束语音
                   playDataList.push('end');
               }
               controlVoicePlay(playDataList);
               //实现重复播放 指定 间隔播放文件长度*700毫秒
          controlVoiceIndex =  setInterval('controlVoicePlay(playDataList)',playDataList.length*700);
            }

    </script>
</head>
<body>
<div class="container-fluid">
    <div class="fixed-top">
        <div class="title"><img src="${ctx}/plugins/front/images/luanIcon.png"><H1>潞安焦化质检系统采样平台</H1></div>
        <div class="operation">
            <ul>
                <li id="voiceSwitch">
                    <img src="${ctx}/plugins/front/extend/assets/images/icon-play.png" title="关闭语音播报">
                </li>
                <li>
                    <img src="${ctx}/plugins/front/extend/assets/images/icon-input.png" title="手工输入">
                </li>
                <li>
                    <img src="${ctx}/plugins/front/extend/assets/images/icon-print.png" title="补打二维码">
                </li>
                <li>
                    <img src="${ctx}/plugins/front/extend/assets/images/icon-query.png" title="查询数据">
                </li>
            </ul>
        </div>
        <div class="user">操作员：${sessionUser.realName}<a href="/logout" >退出</a></div>
    </div>
    <div class="display">
        <ul id="tbody">
            <li>
                <span class="on">B0003</span>
                <span>B0004</span>
                <span>B0005</span>
                <span>B0006</span>
                <b class="hide"><a class="heyanbtn" href="">合样</a></b>
            </li>

        </ul>
    </div>
</div>
</body>
</html>
</html>
