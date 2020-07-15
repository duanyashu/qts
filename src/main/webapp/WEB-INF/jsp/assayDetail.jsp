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
    <title>化验详情</title>
    <style>
        .layui-col-xs4{
            height: 40px;
        }
    </style>
</head>
<body>
<fieldset class="layui-elem-field" style="margin: 10px">
    <legend>化验信息</legend>
    <div class="layui-field-box">
        <div class="layui-col-xs4">
            <span>化验编号：${assay.margeCode}</span>
        </div>
        <div class="layui-col-xs4">
            <span>来源地：${assay.sender}</span>
        </div>
        <div class="layui-col-xs4">
            <span>类型：${assay.type}</span>
        </div>
        <div class="layui-col-xs4">
            <span>厂区标识：${assay.branchCode}</span>
        </div>
        <c:if test="${assay.templateId==1}">
        <div class="layui-col-xs4">
            <span>合样编号：${assay.sampleCodes}</span>
        </div>
        <div class="layui-col-xs4">
            <span>采样车号：${assay.margeCarnums}</span>
        </div>
        </c:if>
        <div class="layui-col-xs4">
            <span>创建时间：<fmt:formatDate value="${assay.createTime}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate> </span>
        </div>
        <div class="layui-col-xs4">
            <span>化验时间：<fmt:formatDate value="${assay.addTime}" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate> </span>
        </div>
        <div class="layui-col-xs4">
            <span>化验人：${assay.addName}</span>
        </div>
        <div class="layui-col-xs4">
            <span>备注：${assay.note}</span>
        </div>
    </div>
</fieldset>
<fieldset class="layui-elem-field" style="margin: 10px">
    <legend>化验详情</legend>
    <div class="layui-field-box">
        <c:forEach items="${assayDetails}" var="assayDetail">
            <div class="layui-col-xs4">
            <span>${assayDetail.itemName}：${assayDetail.value}</span>
        </div>
        </c:forEach>
    </div>
</fieldset>

</body>
</html>