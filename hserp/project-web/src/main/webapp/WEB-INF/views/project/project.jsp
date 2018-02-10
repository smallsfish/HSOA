<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html lang="en">
<head>

    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="Keywords" content="IO"/>
    <meta name="description" content="海思数据中心IO管理系统"/>
    <title>项目管理列表</title>
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
            <input type="text" placeholder="输入项目名称" id="searchName" name="name" class='seaerch-txt'>
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
    var projectId=null;
    var searchTable;

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
            ,url:'system/project/api/getProjectList'
            ,height: 'full-78'
            ,cols: [[
                {field:'orderNumber', width: 66, title: '序号', align: 'center'}
                ,{field:'projectName', width: 220, title: '项目名称', align: 'center'}
                ,{field:'projectFinishDate', width: 300, title: '预计完工时间', align: 'center'}
                ,{field:'projectRange', width: 200, title: '项目周期(周)', align: 'center'}
                ,{field:'projectCreateTime', title: '项目创建时间', width: twidth * 0.2, align: 'center'}
                ,{field:'projectLevel', width: twidth*0.1, title: '项目难度等级', sort: true, align: 'center'}
                ,{
                    width: twidth * 0.18,
                    fixed: 'right',
                    align: 'center',
                    toolbar: '#infoToolBar',
                    title: '操作'
                }
            ]]
            , limits: [30, 60, 120, 240, 500]
            , limit: 30
            ,page: true
        });

            table.on('tool(infoTable)', function (obj) {
                var data = obj.data;
                var layEvent = obj.event;
                if (layEvent === 'del') {

                } else if (layEvent === 'edit') { //编辑
                    editorUser(data);
                } else if (layEvent === 'manager') { //管理
                    manager(data);
                }
            });
        });
    });

    function searchSubmit() {
        var projectName = $(":input[name='name']").val();

        searchTable.reload({
            url: 'system/project/api/getProjectByLike?projectName=' + encodeURI(encodeURI(projectName))
        });
    }


    function editorUser(data) {
        layui.use('layer', function () {
            var layer = layui.layer;
            projectId=data.id;
            layer.open({
                title: data.projectName + ' 用户编辑',
                type: 2,
                area: ['50%', '70%'],
                content: 'system/project/getView?viewPage=project/projectEditor',
            });
        });
    }

    function addEva() {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                title: '添加项目信息',
                type: 2,
                area: ['50%', '70%'],
                content: 'system/project/getView?viewPage=project/projectAdd'
            });
        });
    }

    function manager() {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                title: '项目管理',
                type: 2,
                area: ['70%', '90%'],
                content: 'system/project/getView?viewPage=project/projectAdd'
            });
        });
    }

    function refreach() {

            searchTable.reload({
                url:'system/project/api/getProjectList'
            });

    }

</script>
<script type="text/html" id="infoToolBar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="manager">管理</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">进度</a>
</script>

</body>
</html>