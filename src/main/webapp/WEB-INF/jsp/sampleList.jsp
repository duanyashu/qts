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
    <title>采样列表</title>
</head>
<body>
<div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="">采样管理</a>
            <a>
              <cite>采样列表</cite></a>
          </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5">
                        <div class="layui-inline layui-show-xs-block">
                            <input class="layui-input"  autocomplete="off" placeholder="开始日" name="startTime" id="startTime">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <input class="layui-input"  autocomplete="off" placeholder="截止日" name="endTime" id="endTime">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <input type="text" name="sampleCode"  placeholder="请输入采样码" autocomplete="off" class="layui-input">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn"  lay-submit="" lay-filter="search"><i class="layui-icon">&#xe615;</i></button>
                        </div>
                    </form>
                </div>
                <div class="layui-card-body ">
                    <!-- 表格容器 -->
                    <table id="sampleTable" lay-filter="tabelFilter"></table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    layui.use(['laydate','form','table'], function(){
        var laydate = layui.laydate;
        var  form = layui.form;
        var table = layui.table;
        //执行一个laydate实例
        laydate.render({
            elem: '#startTime' //指定元素
            ,type: 'datetime'
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#endTime' //指定元素
            ,type: 'datetime'
        });
//监听查询提交事件
        form.on('submit(search)', function(data){
            console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
            console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
            console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
            //表格重载
            tableIns.reload({
                where: data.field
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });
        //第一个实例
        var h = $(window).height()-140;
        var tableIns =  table.render({
            elem: '#sampleTable'
            ,height: h
            ,url: '${ctx}/sample/list' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                ,{field: 'sampleCode', title: '采样编号', width:'12%'}
                ,{field: 'sender', title: '来源地', width:160}
                ,{field: 'type', title: '类别', width:80}
                ,{field: 'cargoname', title: '货名', width:80}
                ,{field: 'carnum', title: '车号', width: 100}
                ,{field: 'branchName', title: '厂区', width: 60}
                ,{field: 'state', title: '是否扫码', width: 80}
                ,{field: 'mergeState', title: '是否合样', width: 80}
                ,{field: 'mergeCode', title: '合样码', width: 100}
                ,{field: 'shifts', title: '班次', width: '60'}
                ,{field: 'createTime', title: '采集时间', width: 140}
            ]]
            ,request: {
                pageName: 'currentPage' //页码的参数名称，默认：page
                ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
        });


    });

</script>
</html>