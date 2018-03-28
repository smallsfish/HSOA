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
    var customerId = null;
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
            ,url:'system/project/api/getCustomResourceList'
            ,height: 'full-78'
            ,cols: [[
                {field:'orderNumber', width: 66, title: '序号', align: 'center', sort:true}
                ,{field:'company', width: twidth * 0.18, title: '公司名称', align: 'center'}
                ,{field:'size', width: twidth * 0.18, title: '公司规模',templet:'#companySize', align: 'center'}
                ,{field:'lead', width: twidth * 0.18, title: '公司领导', align: 'center'}
                ,{field:'responsiblePerson', width: twidth * 0.18, title: '公司负责人', align: 'center'}
                ,{field:'companyTel', width: twidth * 0.18, title: '公司电话', align: 'center'}
                ,{field:'companyEamil', width: twidth * 0.18, title: '公司email', align: 'center'}
                ,{field:'companyTime', width: 120, title: '公司成立时间', align: 'center'}
                ,{field:'status', width: 120, title: '客户状态',templet:'#companyStatus',  align: 'center'}
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
                            url: 'system/project/api/delCustomResource',
                            data: {'id': data.id},
                            success: function (result) {
                                layer.close(loadIndex);
                                if (result.status == 0) {
                                    layer.msg("删除成功！");
                                    refreach();
                                }else if(result.status==1){
                                    layer.msg("删除失败！");
                                }else if(result.status==2){
                                    layer.msg("请求参数异常！");
                                }else if(result.status==10){
                                    layer.msg("请重新登录。。。");
                                    window.location.href="system/login";
                                }else if(result.status==20){
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
                } else if (layEvent === 'detail') { //详情
                    detail(data);
                } else if (layEvent === 'manager') { //管理
                    manager(data);
                }
            });
        });
    });

    function searchSubmit() {
        var userName = $(":input[name='name']").val();

        searchTable.reload({
            url: 'system/project/api/getCustomResourceListByLike?company=' + userName
        });
    }


    function editorUser(data) {
        layui.use('layer', function () {
            var layer = layui.layer;
            customerId=data.id;
            layer.open({
                title: '客户资源编辑',
                type: 2,
                area: ['50%', '70%'],
                content: 'system/project/getView?viewPage=custom/customerEdit&ki=' + data.id,
            });
        });
    }


    function detail(data) {
        layui.use('layer', function () {
            var layer = layui.layer;
            humanId=data.id;
            layer.open({
                title: data.name + '项目详情',
                type: 2,
                area: ['50%', '70%'],
                content: 'system/project/getView?viewPage=custom/customerDetail&ki=' + data.id,
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
                content: 'system/project/getView?viewPage=custom/customerAdd'
            });
        });
    }

    function manager(data) {
        window.parent.createTab({title: data.company+'管理',isShowClose:true,url:'system/project/getView?viewPage=custom/customerManage&ki='+data.id});
    }
    function refreach() {
        searchTable.reload({
            url:'system/project/api/getCustomResourceList'
        });
    }

</script>
<script type="text/html" id="infoToolBar">
    <%--<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="detail">详情</a>--%>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="manager">管理</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>

    <!-- 这里同样支持 laytpl 语法，如： -->
    {{#  if(d.auth > 2){ }}
    <a class="layui-btn layui-btn-xs" lay-event="check">审核</a>
    {{#  } }}
</script>
<script type="text/html" id="companySize">
    {{# if(d.size === 0){ }}
    初创公司
    {{# }else if(d.size === 1){ }}
    稳定发展
    {{# }else if(d.size === 2){ }}
    大型公司
    {{# }else if(d.size === 3){ }}
    上市公司
    {{# } }}
</script>

<script type="text/html" id="companyStatus">
    {{# if(d.status === 0){ }}
    正常
    {{# }else if(d.status === 1){ }}
    删除
    {{# }else if(d.status === 2){ }}
    锁定
    {{# } }}
</script>

</body>
</html>