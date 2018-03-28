<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html lang="en">
<head>

    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="Keywords" content="IO"/>
    <meta name="description" content="海思数据中心IO管理系统"/>
    <title>项目进度列表</title>
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_562689_bvfsvay973edbo6r.css">
    <link rel="stylesheet" href="/static/css/public.css">
    <link rel="stylesheet" href="/static/layui/css/layui.css"/>

    <link rel='stylesheet' type="text/css" href='/static/css/credit.css'>
    <jsp:include page="../base.jsp"/>

</head>
<body>
<div class='evaluation'>
    <div class='search-box clearfix'>
        <div class='form-title fl' style="width: 1000px;margin-top: 12px;">
            <div class="layui-progress layui-progress-big" lay-showpercent="true" lay-filter="demo">
                <div class="layui-progress-bar layui-bg-green" lay-percent="%"></div>
            </div>
        </div>
        <div class='search-add fr'>
            <i class='iconfont icon-tianjia' onclick='addEva()' title='添加'></i>
            <i class='iconfont icon-icon-refresh' onclick='refreach()' title='刷新'></i>
        </div>
    </div>

    <table class="layui-hide" id="test" lay-filter="infoTable"></table>
</div>


<script>
    var pid='${ki}';
    var projectId = null;
    var precends;

    var searchTable;


    layui.use(['element', 'table'], function () {
        var element = layui.element;
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
                , url: 'system/project/api/getProjectProgressList?pid='+pid
                , height: 'full-78'
                , cols: [[
                    // {field: 'orderNumber', width: 66, title: '序号', align: 'center'}
                    {field: 'precend', width:  twidth * 0.1, title: '项目进度', align: 'center'}
                    , {field: 'descript', width:  twidth * 0.1, title: '项目进度描述', align: 'center'}
                    , {
                        width: twidth * 0.18,
                        fixed: 'right',
                        align: 'center',
                        toolbar: '#infoToolBar',
                        title: '操作'
                    }
                ]]
                , limits: [30, 60, 120, 240, 500]
                , limit: 30
                , page: true
            });
            table.on('tool(infoTable)', function (obj) {
                var data = obj.data;
                var layEvent = obj.event;
                if (layEvent === 'del') {
                    layer.confirm('确认删除？',function (index) {
                        obj.del();
                        layer.close(index);

                        loadIndex = layer.load();
                        $.ajax({
                            type: "GET",
                            dataType: "json",
                            url: 'system/project/api/delProjectProgress',
                            data: {'id': data.id},
                            success: function (result) {
                                layer.close(loadIndex);
                                if (result.status == 0) {
                                    layer.msg("删除成功！");
                                    refreachProjectStaff();
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
    function editorUser(data) {
        layui.use('layer', function () {
            var layer = layui.layer;
            projectId = data.id;
            layer.open({
                title: '项目进度编辑',
                type: 2,
                area: ['50%', '70%'],
                content: 'system/project/getView?viewPage=project/projectSpeedEdit&ki=' + data.id
            });
        });
    }
    function addEva() {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                title: '添加项目进度信息',
                type: 2,
                area: ['50%', '70%'],
                content: 'system/project/getView?viewPage=project/projectSpeedAdd&ki='+ pid
            });
        });
    }
    function refreach() {
        searchTable.reload({
            url: 'system/project/api/getProjectProgressList?pid='+pid
        });
    }
    layui.use('element', function() {
        var element = layui.element;

        $.ajax({
            type: 'GET',
            url: 'system/project/api/getProjectProgressList?pid='+pid,
            success: function (data) {
                console.log( data.data[0].precend );
                element.progress('demo', data.data[0].precend +'%');

                // precends = new Array();
                //
                // for(var i=0;i<data.data.length;i++){
                //
                // }

            }
        });
    });


</script>
<script type="text/html" id="infoToolBar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

</body>
</html>