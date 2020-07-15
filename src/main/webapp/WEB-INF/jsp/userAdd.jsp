<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/7/14
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp"%>
<html class="x-admin-sm">
<head>
    <title>Title</title>
</head>
<body>
<form class="layui-form" lay-filter="form">
    <input type="hidden" name="id" value="${user.id}">
    <div class="layui-form-item">
        <label class="layui-form-label">账号</label>
        <div class="layui-input-inline">
            <input type="text" name="username" required  lay-verify="required" placeholder="请输入账号" autocomplete="off" value="${user.realName}" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-inline">
            <input type="password" name="password" required  lay-verify="required" placeholder="请输入密码" autocomplete="off" value="${user.realName}" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户姓名</label>
        <div class="layui-input-inline">
            <input type="text" name="realName" required  lay-verify="required" placeholder="请输入姓名" autocomplete="off" value="${user.realName}" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户角色</label>
        <div class="layui-input-block">
            <c:forEach items="${roleList}" var="role" varStatus="status">
                <input type="checkbox" name="roles[${status.index}]" value="${role.id}"  title="${role.role_name}"
                       <c:choose><c:when test="${role.a==1}">lay-filter="role-radio" </c:when><c:otherwise>lay-filter="role-checkbox" </c:otherwise></c:choose> >
            </c:forEach>
        </div>
    </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="submit">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script>
    layui.use('form',function () {
        var form = layui.form;
        var $ = layui.$;
        form.on('checkbox(role-radio)', function(data){
            console.log(data.elem); //得到checkbox原始DOM对象
            console.log(data.elem.checked); //是否被选中，true或者false
            console.log(data.value); //复选框value值，也可以通过data.elem.value得到
            console.log(data.othis); //得到美化后的DOM对象
            var v = {};
            $('input[name^="roles"]:checked').each(function (i) {
                if ($(this).val()!=data.value){
                    var like=$(this).attr('name');
                    v[like] =false;
                }
            })
            form.val("form", v);
        });
        form.on('checkbox(role-checkbox)', function(data){
            console.log(data.elem); //得到checkbox原始DOM对象
            console.log(data.elem.checked); //是否被选中，true或者false
            console.log(data.value); //复选框value值，也可以通过data.elem.value得到
            console.log(data.othis); //得到美化后的DOM对象
            var v = {};
            $('input[name^="roles"][lay-filter="role-radio"]').each(function (i) {
                    var like=$(this).attr('name');
                    v[like] =false;
            })
            form.val("form", v);
        });

        form.on('submit(submit)', function(data){
            console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
            console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
            console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
            var reqeustData =  data.field;
            var roles = new Array();
            $('input[name^="roles"]:checked').each(function(){
                roles.push($(this).val());
            })
            reqeustData.roles = roles.join(",");
            console.log(reqeustData);
            var openIndex = layer.open({type:3});
            <%--$.post('${ctx}/assay/addDataSubmit',data.field,function (obj) {--%>
            <%--    if (obj.code=200){--%>
            <%--        layer.msg("保存成功", {icon: 6,time:500},function() {--%>
            <%--            //关闭当前frame--%>
            <%--            xadmin.close();--%>
            <%--            // 可以对父窗口进行刷新--%>
            <%--            xadmin.father_reload();--%>
            <%--        });--%>
            <%--    }else{--%>
            <%--        layer.close(openIndex);--%>
            <%--        layer.alert("保存失败", {icon: 5})--%>
            <%--    }--%>
            <%--})--%>
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        },'json');
    })
</script>
</body>
</html>
