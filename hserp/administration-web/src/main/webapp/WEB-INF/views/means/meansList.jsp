<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html lang="en">
<head>

    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="Keywords" content="IO"/>
    <meta name="description" content="海思数据中心IO管理系统"/>
    <title>注册资料管理列表</title>

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
            <input type="text" placeholder="输入注册资料描述以查询" id="searchName" name="text" class='seaerch-txt'>
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
    var meansId = null;
    var searchTable;

    layui.use('table', function(){
        var loadIndex;
        var twidth = $("#test").width();

        layui.use(['element', 'table'], function () {
            var element = layui.element;
            var table = layui.table;
            //一些事件监听
            element.on('tab(demo)', function (data) {
            });
            searchTable = table.render({
                elem: '#test'
                ,url:'system/data/api/dataList'
                ,height: 'full-78'
                ,cols: [[
                    {field:'annex', width: twidth*0.1, title: '注册资料文件',  align: 'center'}
                    ,{field:'text', width:  twidth*0.1, title: '注册资料描述',  align: 'center'}
                    ,{field:'time', width: 180, title: '注册资料上传时间',  align: 'center'}
                    ,{
                        width: 240,
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
                            type: "POST",
                            dataType: "json",
                            url: 'system/data/api/dataDelete',
                            data: {'id': data.id},
                            success: function (result) {
                                layer.close(loadIndex);
                                if (result.code == 0) {
                                    obj.del();
                                    layer.msg("删除成功！");
                                    refreach();
                                }else if(result.code==1){
                                    layer.msg("删除失败！");
                                }else if(result.code==2){
                                    layer.msg("请求参数异常！");
                                }else if(result.code==10){
                                    layer.msg("请重新登录。。。");
                                    window.location.href="system/login";
                                }else if(result.code==20){
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
        var userName = $(":input[name='text']").val();

        searchTable.reload({
            url: 'system/data/api/dataList?text=' + userName
        });
    }
    function editorUser(data) {
        layui.use('layer', function () {
            var layer = layui.layer;
            meansId = data.id;
            layer.open({
                title: '编辑注册资料信息',
                type: 2,
                area: ['50%', '70%'],
                content: 'system/administration/meansEdit?id=' + data.id,
            });
        });
    }
    function addEva() {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                title: '添加注册资料信息',
                type: 2,
                area: ['50%', '70%'],
                content: 'system/administration/meansAdd'
            });
        });
    }
    function refreach() {
        searchTable.reload({
            url:'system/data/api/dataList'
        });
    }

</script>
<script type="text/html" id="infoToolBar">
    {{#
        var fn = function(name){
            return "<a class='layui-btn layui-btn-xs layui-btn-normal' target='_blank' href='static/File/doc/"+name+"'>下载</a>";
        };
    }}

    {{ fn(d.annex) }}
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>

    <!-- 这里同样支持 laytpl 语法，如： -->
    {{#  if(d.auth > 2){ }}
    <a class="layui-btn layui-btn-xs" lay-event="check">审核</a>
    {{#  } }}
</script>

</body>
</html>