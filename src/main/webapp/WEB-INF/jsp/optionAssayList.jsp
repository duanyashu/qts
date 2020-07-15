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
    <title>化验列表</title>
</head>
<body>
<div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="">化验室</a>
            <a>
              <cite>化验列表</cite></a>
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
                            <input type="text" name="margeCode"  placeholder="请输入合样码" autocomplete="off" class="layui-input">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn"  lay-submit="" lay-filter="search"><i class="layui-icon">&#xe615;</i></button>
                        </div>
                    </form>
                </div>
                <div class="layui-card-body ">
                    <!-- 表格容器 -->
                    <table id="assayTable" lay-filter="tableFilter"></table>
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
            elem: '#assayTable'
            ,height: h
            ,url: '${ctx}/assay/listData' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'margeCode', title: '合样编号', width:120}
                ,{field: 'sender', title: '来源地', width:180}
                ,{field: 'type', title: '类别', width:90}
                ,{field: 'branchCode', title: '厂区', width: 60}
                ,{field: 'state', title: '状态', width: 90,templet: '#stateTpl'}
                ,{field: 'createTime', title: '创建时间', width: 160}
                ,{fixed: 'right', width:280, align:'center',title:'操作', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]]
            ,request: {
                pageName: 'currentPage' //页码的参数名称，默认：page
                ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
        });
        //监听工具条
        table.on('tool(tableFilter)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
            console.log(data);
            if(layEvent === 'addData'){ //查看
                xadmin.open('录入数据','${ctx}/assay/assayAddData?id='+data.id+"&margeCode="+data.margeCode+"&templateId="+data.templateId,900,500);
            }else if(layEvent=='detail'){//详情
                xadmin.open('详情','${ctx}/assay/detail?id='+data.id,900,500);
            }
        });


    });
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="detail">详情</a>
    <!-- 这里同样支持 laytpl 语法，如： -->
    {{#  if(d.state == 0){ }}
    <a class="layui-btn layui-btn-xs" lay-event="addData">录入化验数据</a>
    {{#  } }}
    {{#  if(d.state == 1){ }}
    <a class="layui-btn layui-btn-xs" lay-event="check">审核</a>
    {{#  } }}
</script>
<script type="text/html" id="stateTpl">
    {{#  if(d.state ==0){ }}
    未录入
    {{#  } else if(d.state ==1) { }}
    <span style="color: yellow">待审核</span>
    {{#  } else if(d.state ==2) { }}
    <span style="color: green; font-weight: bold">通过</span>
    {{#  } else if(d.state ==3) { }}
    <span style="color: red">不通过</span>
    {{#  } }}
</script>
</html>