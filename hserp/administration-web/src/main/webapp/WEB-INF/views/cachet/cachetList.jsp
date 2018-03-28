<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html lang="en">
<head>

    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="Keywords" content="IO"/>
    <meta name="description" content="海思数据中心IO管理系统"/>
    <title>公章管理列表</title>

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
            <input type="search" placeholder="输入审批人以查询" id="searchName" name="approver" class='seaerch-txt'>
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
    var cachetId = null;
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
                ,url:'system/reimbursement/api/reimbursementList'
                ,height: 'full-78'
                ,cols: [[
                    {field:'approver', width: 180, title: '报销审批人', align: 'center'}
                    ,{field:'amount', width: 180, title: '报销金额',  align: 'center'}
                    ,{field:'category', width: 180, title: '报销类别',  align: 'center'}
                    ,{field:'time', width: 180, title: '报销时间',  align: 'center'}
                    ,{field:'details', width: twidth*0.1, title: '费用明细',  align: 'center'}
                    ,{field:'ccperson', width: 180, title: '抄送人',  align: 'center'}
                    ,{
                        width: 200,
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
                            url: 'system/reimbursement/api/reimbursementDelete',
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
        var userName = $(":input[name='approver']").val();

        searchTable.reload({
            url: 'system/reimbursement/api/reimbursementList?approver=' + userName
        });
    }
    function editorUser(data) {
        layui.use('layer', function () {
            var layer = layui.layer;
            cachetId = data.id;
            layer.open({
                title: data.approver + ' 报销编辑',
                type: 2,
                area: ['50%', '70%'],
                content: 'system/administration/cachetEdit?id=' + data.id,
            });
        });
    }
    function addEva() {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                title: '添加报销信息',
                type: 2,
                area: ['50%', '70%'],
                content: 'system/administration/cachetAdd'
            });
        });
    }
    function refreach() {
        searchTable.reload({
            url:'system/reimbursement/api/reimbursementList'
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