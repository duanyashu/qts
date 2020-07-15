<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/7/2
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp"%>
<!doctype html>
<html  class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>质检系统登录页面</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body class="login-bg">

<div class="login layui-anim layui-anim-up">
    <div class="message">用户登录</div>
    <div id="darkbannerwrap"></div>

    <form method="post" class="layui-form" >
        <input name="username" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
        <hr class="hr15">
        <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
        <hr class="hr15">
        <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
        <hr class="hr20" >
    </form>
</div>

<script>
    $(function  () {
        layui.use('form', function(){
            var form = layui.form;
            var $ =layui.jquery;
            //监听提交
            form.on('submit(login)', function(data){
                // alert(888)
                $.getJSON('${ctx}/login',data.field,function (obj) {
                    if (obj.code==200){
                        location.href='${ctx}'+obj.data;
                    }else{
                        layer.msg(obj.msg,{icon:5});
                    }
                })
                return false;
            });
        });
    })
</script>

</body>
</html>