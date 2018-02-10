<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html lang="en">
<head>

    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="Keywords" content="IO"/>
    <meta name="description" content="海思数据中心IO管理系统"/>
    <title>储备人员管理列表</title>

    <link rel="stylesheet" href="http://at.alicdn.com/t/font_562689_bvfsvay973edbo6r.css">
    <link rel="stylesheet" href="/static/css/public.css">
    <link rel="stylesheet" href="/static/layui/css/layui.css"/>

    <link rel='stylesheet' type="text/css" href='/static/css/credit.css'>
    <jsp:include page="../base.jsp"/>

</head>
<body>
<div class='evaluation'>
    <div class='search-box clearfix'>
        <div class='form-title fl'>
            <input type="text" placeholder="输入姓名以查询" id="searchName" name="name" class='seaerch-txt'>
            <input type="button" value="搜 索" class="layui-btn layui-btn-normal s-btn" id="searchSubmit" onclick="searchSubmit()">
        </div>
        <div class='search-add fr'>
            <i class='iconfont icon-tianjia' onclick='addEva()' title='添加'></i>
            <i class='iconfont icon-icon-refresh' onclick='refreach()' title='刷新'></i>
        </div>
    </div>

    <table class="layui-hide" id="test" lay-filter="infoTable"></table>
</div>


<script>
    var reserveId=null;
    var searchTable;
    var reserveDept = null;

    layui.use('table', function(){
        var loadIndex;
        var twidth = $("#test").width;

        layui.use(['element', 'table'], function () {
            var element = layui.element;
            var table = layui.table;
            //一些事件监听
            element.on('tab(demo)', function (data) {
            });

            searchTable = table.render({
            elem: '#test'
            ,url:'system/human/api/getStoreList'
            ,height: 'full-78'
            ,cols: [[
                {field:'index', width: 66, title: '序号',sort: true, align: 'center'}
                ,{field:'createBy', width: 160, title: '创建人', align: 'center'}
                ,{field:'name', width: 160, title: '姓名', align: 'center'}
                ,{field:'sex', width: 88, title: '性别', align: 'center'}
                ,{field:'idCard', width: 200, title: '身份证号', align: 'center'}
                ,{field:'tel', title: '手机号', width: twidth * 0.2, align: 'center'}
                ,{field:'education', width: twidth*0.1, title: '学历',  align: 'center'}
                ,{field:'deptName', width: twidth*0.1, title: '所属部门',  align: 'center'}
                ,{field:'salary', width: twidth*0.1, title: '工资待遇', sort: true, align: 'center'}
                ,{field:'practiceTime', width: twidth*0.1, title: '实习时间', sort: true, align: 'center'}
                ,{field:'status', width: twidth*0.1, title: '是否转正', sort: true, align: 'center'}
                ,{
                    width: twidth * 0.18,
                    fixed: 'right',
                    align: 'center',
                    toolbar: '#infoToolBar',
                    title: '操作'
                }
            ]]
            , limits: [15, 30, 45, 60, 75]
            , limit: 15
            ,page: true
        });

            table.on('tool(infoTable)', function (obj) {
                var data = obj.data;
                var layEvent = obj.event;
                if (layEvent === 'del') {
                    layer.confirm('确认删除？', function (index) {
                        obj.del();
                        layer.close(index);

                        loadIndex = layer.load();
                        $.ajax({
                            type: "GET",
                            dataType: "json",
                            url: 'system/human/api/deleteStore',
                            data: {'id': data.id},
                            success: function (result) {
                                layer.close(loadIndex);
                                if (result.status == 0) {
                                    obj.del();
                                    layer.alert("删除成功！");
                                }else if(result.status==1){
                                    layer.alert("删除失败！");
                                }else if(result.status==2){
                                    layer.alert("请求参数异常！");
                                }else if(result.status==10){
                                    layer.alert("请重新登录。。。");
                                    window.location.href="system/login";
                                }else if(result.status==20){
                                    layer.alert("你没有操作权限！");
                                }
                            },
                            error: function (data) {
                                layer.close(loadIndex);
                                layer.alert("出现异常！请刷新页面重试");
                            }
                        });
                    });
                } else if (layEvent === 'edit') { //编辑
                    editorUser(data);
                }
            });
        });
    });

    function searchSubmit() {
        var userName = $(":input[name='name']").val();

        searchTable.reload({
            url: 'system/human/api/getStoreList?name=' + userName
        });
    }


    function editorUser(data) {
        layui.use('layer', function () {
            var layer = layui.layer;
            reserveId = data.id;
            reserveDept = data.deptName;
            layer.open({
                title: data.name + ' 用户编辑',
                type: 2,
                area: ['50%', '70%'],
                content: 'reserve/reserveEdit?id=' + data.id,
            });
        });
    }

    function addEva() {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                title: '添加储备人才信息',
                type: 2,
                area: ['50%', '70%'],
                content: 'reserve/reserveAdd'
            });
        });
    }

    function refreach() {

            searchTable.reload({
                url:'system/human/api/getStoreList'
            });

    }

</script>
<script type="text/html" id="infoToolBar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>

    <!-- 这里同样支持 laytpl 语法，如： -->
    {{#  if(d.auth > 2){ }}
    <a class="layui-btn layui-btn-xs" lay-event="check">审核</a>
    {{#  } }}
</script>

</body>
</html>