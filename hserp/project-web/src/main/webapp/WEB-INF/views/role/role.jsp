<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html lang="en">
<head>

    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="Keywords" content="IO"/>
    <meta name="description" content="海思数据中心IO管理系统"/>
    <title>管理员列表</title>

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
            <input type="text" placeholder="输入账号以查询" id="searchName" name="name" class='seaerch-txt'>
            <input type="button" value="搜 索" class="layui-btn layui-btn-normal s-btn" id="searchSubmit"
                   onclick="searchSubmit()">
        </div>
        <div class='search-add fr'>
            <i class='iconfont icon-tianjia' onclick='addEva()' title='添加'></i>
            <i class='iconfont icon-icon-refresh' onclick='refreach()' title='刷新'></i>
        </div>
    </div>

    <table class="layui-hide" id="test" lay-filter="infoTable"></table>
</div>


<script>
    var searchTable;

    layui.use('table', function () {
        var loadIndex;
        var twidth = $("#test").width;

        layui.use(['element', 'table','form'], function () {
            var element = layui.element;
            var table = layui.table;
            var form = layui.form;
            //一些事件监听
            form.on('switch(roleAvailable)', function (data) {
                var available = 0;
                if (data.elem.checked) {
                    available = 1;
                }
                loadIndex = layer.load();
                $.ajax({
                    type: "GET",
                    dataType: "json",
                    url: 'system/roleAvailableUpdate',
                    data: {'id': data.value, 'available': available},
                    success: function (result) {
                        layer.close(loadIndex);
                        layer.msg(result.msg);
                    },
                    error: function (data) {
                        layer.close(loadIndex);
                        layer.alert("出现异常！");
                    }
                });
            });
            element.on('tab(demo)', function (data) {
            });

            searchTable = table.render({
                elem: '#test'
                , url: 'system/roleList'

                , height: 'full-78'
                , cols: [[
                    {field: 'aid', width: 66, title: '序号', align: 'center', sort: true}
                    , {field: 'rolename', width: twidth * 0.18, title: '角色名称', align: 'center'}
                    , {field: 'resources', width: twidth * 0.18, title: '资源', align: 'center'}
                    , {field: 'description', width: twidth * 0.18, title: '描述', align: 'center'}
                    , {field: 'available', width: twidth * 0.18, title: '是否可用', toolbar: '#available',align: 'center'}
                    , {
                        width: twidth * 0.18,
                        fixed: 'right',
                        align: 'center',
                        toolbar: '#infoToolBar',
                        title: '操作'
                    }
                ]]
                , limits: [15, 30, 45, 60, 75]
                , limit: 15
                , page: true
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
                            url: 'system/roleDel',
                            data: {'id': data.id},
                            success: function (result) {
                                layer.close(loadIndex);
                                if (result.status == 0) {
                                    layer.msg("删除成功！");
                                    refreach();
                                } else if (result.status == 1) {
                                    layer.msg("删除失败！");
                                } else if (result.status == 2) {
                                    layer.msg("请求参数异常！");
                                } else if (result.status == 10) {
                                    layer.msg("请重新登录。。。");
                                    window.location.href = "system/login";
                                } else if (result.status == 20) {
                                    layer.msg("你没有操作权限！");
                                }
                            },
                            error: function (data) {
                                layer.close(loadIndex);
                                layer.msg("出现异常！请刷新页面重试");
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
            url: 'system/searchRole?name=' + userName
        });
    }
    function editorUser(data) {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                title: '客户资源编辑',
                type: 2,
                area: ['50%', '70%'],
                content: 'system/project/getView?viewPage=role/editorRole&ki=' + data.id,
            });
        });
    }

    function addEva() {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                title: '管理员添加',
                type: 2,
                area: ['50%', '70%'],
                content: 'system/project/getView?viewPage=role/addRole'
            });
        });
    }

    function refreach() {
        searchTable.reload({
            url: 'system/roleList'
        });
    }

</script>
<script type="text/html" id="infoToolBar">
    <%--<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="detail">详情</a>--%>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <!-- 这里同样支持 laytpl 语法，如： -->
    {{#  if(d.auth > 2){ }}
    <a class="layui-btn layui-btn-xs" lay-event="check">审核</a>
    {{#  } }}
</script>
<script id="available" type="text/html">
    <form class="layui-form">
        <input type="checkbox" name="switch"
               {{# if(d.available==true){ }}
               checked
               {{# } }}
               value="{{d.id}}" lay-filter="roleAvailable" lay-skin="switch">
    </form>
</script>

</body>
</html>