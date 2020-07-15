<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/7/8
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>添加化验数据</title>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form">
            <input type="hidden" name="assayId" value="${assay.id}">
            <input type="hidden" name="margeCode" value="${assay.margeCode}">
            <c:forEach items="${assayItems}" var="item" varStatus="status">
            <div class="layui-form-item layui-inline">
                <label for="L_email" class="layui-form-label">${item.itemName}:</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_email" name="itemValues[${status.index}]" autocomplete="off" class="layui-input">
                    <input type="hidden" name="itemNames[${status.index}]" value="${item.itemName}" autocomplete="off" class="layui-input">
                    <input type="hidden" name="itemStandards[${status.index}]" value="${item.standard}" autocomplete="off" class="layui-input">
                </div>
            </div>
            </c:forEach>
            <div class="layui-form-item">
                <button class="layui-btn" lay-filter="*" lay-submit>增加</button>
            </div>
        </form>
    </div>
</div>
<script>
    layui.use('form',function () {
        var form = layui.form;
        var $ = layui.$;
        form.on('submit(*)', function(data){
            console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
            console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
            console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
            var openIndex = layer.open({type:3});
            $.post('${ctx}/assay/addDataSubmit',data.field,function (obj) {
                if (obj.code=200){
                    layer.msg("保存成功", {icon: 6,time:500},function() {
                            //关闭当前frame
                            xadmin.close();
                            // 可以对父窗口进行刷新
                            xadmin.father_reload();
                        });
                }else{
                    layer.close(openIndex);
                    layer.alert("保存失败", {icon: 5})
                }
            })
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        },'json');
    })
</script>
</body>
</html>