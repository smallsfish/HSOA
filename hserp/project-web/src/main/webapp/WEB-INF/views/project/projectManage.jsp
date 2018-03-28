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
<div class="layui-tab layui-tab-brief" lay-filter="projectTabBrief">
    <ul class="layui-tab-title">
        <li class="layui-this">项目资料</li>
        <li>项目输出资料</li>
        <li>参与人员</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <div class='evaluation'>
                <div class='search-box clearfix'>
                    <div class='form-title fl'>
                        <input type="text" placeholder="输入项目资料名称" id="searchName" name="projectDataName"
                               class='seaerch-txt'>
                        <input type="button" value="搜 索" class="layui-btn layui-btn-normal s-btn"
                               id="searchProjectDataSubmit"
                               onclick="searchProjectDataSubmit()">
                    </div>
                    <div class='search-add fr'>
                        <i class='iconfont icon-tianjia' onclick='addProjectDataEva()' title='添加'></i>
                        <i class='iconfont icon-icon-refresh' onclick='refreachProjectData()' title='刷新'></i>
                    </div>
                </div>

                <table class="layui-hide" id="projectData" lay-filter="projectDataTable"></table>
            </div>
        </div>
        <div class="layui-tab-item">
            <div class='evaluation'>
                <div class='search-box clearfix'>
                    <div class='form-title fl'>
                        <input type="text" placeholder="输入项目输出资料名称" id="searchProjcetOutDataName"
                               name="projectOutDataName" class='seaerch-txt'>
                        <input type="button" value="搜 索" class="layui-btn layui-btn-normal s-btn"
                               id="searchProjectOutDataSubmit"
                               onclick="searchProjectOutDataSubmit()">
                    </div>
                    <div class='search-add fr'>
                        <i class='iconfont icon-tianjia' onclick='addProjectOutDataEva()' title='添加'></i>
                        <i class='iconfont icon-icon-refresh' onclick='refreachProjectOutData()' title='刷新'></i>
                    </div>
                </div>

                <table class="layui-hide" id="projectOutData" lay-filter="projectOutDataTable"></table>
            </div>
        </div>
        <div class="layui-tab-item">
            <div class='evaluation'>
                <div class='search-box clearfix'>
                    <div class='form-title fl'>
                        <input type="text" placeholder="输入参与人员名称" id="searchProjectStaffName" name="projectStaffName"
                               class='seaerch-txt'>
                        <input type="button" value="搜 索" class="layui-btn layui-btn-normal s-btn"
                               id="searchProjectStaffSubmit"
                               onclick="searchProjectStaffSubmit()">
                    </div>
                    <div class='search-add fr'>
                        <i class='iconfont icon-tianjia' onclick='addProjectStaffEva()' title='添加'></i>
                        <i class='iconfont icon-icon-refresh' onclick='refreachProjectStaff()' title='刷新'></i>
                    </div>
                </div>

                <table class="layui-hide" id="projectStaff" lay-filter="projectStaffTable"></table>
            </div>
        </div>
    </div>
</div>


<script>
    var pid='${ki}';
    var projectDataTable,projectOutDataTable,projectStaffTable;
    var humanDept=null;
    layui.use(['element', 'table'], function () {
        var tabLoadIndex=new Array(true,false,false);
        var element = layui.element;
        var table = layui.table;
        //一些事件监听
        element.on('tab(projectTabBrief)', function (data) {
            if(!tabLoadIndex[data.index]){
                switch (data.index){
                    case 1:
                        loadProjectOutDataTable();
                        break;
                    case 2:
                        loadProjectStaffTable();
                        break;
                }
                tabLoadIndex[data.index]=true;
            }
        });
        projectDataTable = table.render({
            elem: '#projectData'
            , url: 'system/project/api/getProjectDataList?pid='+pid
            , height: 'full-153'
            , cols: [[
                {field: 'orderNumber', width: 66, title: '序号', align: 'center'}
                , {field: 'projectDataName', width: 420, title: '项目资料名称', align: 'center'}
                , {field: 'projectDataType', width: 150, title: '项目资料类型',templet:'#projectDataType', align: 'center'}
                , {field: 'projectDataWay', width: 380, title: '项目资料路径', align: 'center'}
                , {field: 'projectDataInfo', title: '项目资料描述', width: 450, align: 'center'}
                , {
                    fixed: 'right',
                    align: 'center',
                    toolbar: '#projectDataToolBar',
                    title: '操作'
                }
            ]]
            , limits: [30, 60, 120, 240, 500]
            , limit: 30
            , page: true
        });

        table.on('tool(projectDataTable)', function (obj) {
            var data = obj.data;
            var layEvent = obj.event;
            if (layEvent === 'del') {//删除项目资料
                layer.confirm('确认删除？',function (index) {
                    obj.del();
                    layer.close(index);

                    loadIndex = layer.load();
                    $.ajax({
                        type: "GET",
                        dataType: "json",
                        url: 'system/project/api/delProjectData',
                        data: {'id': data.id},
                        success: function (result) {
                            layer.close(loadIndex);
                            if (result.status == 0) {
                                layer.msg("删除成功！");
                                refreachProjectData()
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
            } else if (layEvent === 'edit') { //编辑项目资料
                editorProjectData(data);
            } else if (layEvent === 'cat') { //查看项目资料
                layer.msg("查看");
            }
        });

        function loadProjectOutDataTable(){
            projectOutDataTable = table.render({
                elem: '#projectOutData'
                , url: 'system/project/api/getProjectOutDataList?pid='+pid
                , height: 'full-153'
                , cols: [[
                    {field: 'orderNumber', width: 66, title: '序号', align: 'center'}
                    , {field: 'projectOutDataName', width: 420, title: '项目输出资料名称', align: 'center'}
                    , {field: 'projectOutDataType', width: 150, title: '项目输出资料类型',templet:'#projectOutDataType', align: 'center'}
                    , {field: 'projectOutDataWay', width: 380, title: '项目输出资料路径', align: 'center'}
                    , {field: 'projectOutDataInfo', title: '项目资料描述', width: 450, align: 'center'}
                    , {
                        fixed: 'right',
                        align: 'center',
                        toolbar: '#projectOutDataToolBar',
                        title: '操作'
                    }
                ]]
                , limits: [30, 60, 120, 240, 500]
                , limit: 30
                , page: true
            });
            table.on('tool(projectOutDataTable)', function (obj) {
                var data = obj.data;
                var layEvent = obj.event;
                if (layEvent === 'del') {//删除项目资料
                    layer.confirm('确认删除？',function (index) {
                        obj.del();
                        layer.close(index);

                        loadIndex = layer.load();
                        $.ajax({
                            type: "GET",
                            dataType: "json",
                            url: 'system/project/api/delProjectOutData',
                            data: {'id': data.id},
                            success: function (result) {
                                layer.close(loadIndex);
                                if (result.status == 0) {
                                    layer.msg("删除成功！");
                                    refreachProjectOutData();
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
                } else if (layEvent === 'edit') { //编辑项目资料
                    editProjectOutDataEva(data);
                } else if (layEvent === 'cat') { //查看项目资料
                    layer.msg("查看");
                }
            });
        }
        function loadProjectStaffTable(){
            projectStaffTable = table.render({
                elem: '#projectStaff'
                , url: 'system/project/api/getProjectStaffList?pid='+pid
                , height: 'full-153'
                , cols: [[
                    {field: 'orderNumber', width: 66, title: '序号', align: 'center'}
                    , {field: 'sid', width: 320, title: '人员名称',  align: 'center'}
                    , {field: 'projectStaffModule', width: 1100, title: '参与模块', align: 'center'}
                    , {
                        fixed: 'right',
                        align: 'center',
                        toolbar: '#projectStaffToolBar',
                        title: '操作'
                    }
                ]]
                , limits: [30, 60, 120, 240, 500]
                , limit: 30
                , page: true
            });
            table.on('tool(projectStaffTable)', function (obj) {
                var data = obj.data;
                var layEvent = obj.event;
                if (layEvent === 'del') {//删除项目资料
                    layer.confirm('确认删除？',function (index) {
                        obj.del();
                        layer.close(index);

                        loadIndex = layer.load();
                        $.ajax({
                            type: "GET",
                            dataType: "json",
                            url: 'system/project/api/delProjectStaff',
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
                } else if (layEvent === 'edit') { //编辑项目资料
                    editProjectStaffEva(data);
                }
            });
        }
    });

    function searchProjectDataSubmit() {
        var projectDataName = $(":input[name='projectDataName']").val();
        projectDataTable.reload({
            url: 'system/project/api/getProjectDataByLike?pid='+pid+'&projectDataName=' + encodeURI(encodeURI(projectDataName))
        });
    }
    function searchProjectOutDataSubmit() {
        var projectDataName = $(":input[name='projectOutDataName']").val();
        projectOutDataTable.reload({
            url: 'system/project/api/getProjectOutDataByLike?pid='+pid+'&projectOutDataName=' + encodeURI(encodeURI(projectDataName))
        });
    }
    function searchProjectStaffSubmit() {
        var projectDataName = $(":input[name='projectStaffName']").val();
        projectStaffTable.reload({
            url: 'system/project/api/getProjectStaffDataByLike?pid='+pid+'&projectOutDataName=' + encodeURI(encodeURI(projectDataName))
        });
    }

    function editorProjectData(data) {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                title: ' 用户编辑',
                type: 2,
                area: ['50%', '70%'],
                content: 'system/project/getView?viewPage=project/projectDataEditor&ki=' + data.id
            });
        });
    }
    function addProjectDataEva() {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                title: '添加项目资料信息',
                type: 2,
                area: ['50%', '70%'],
                content: 'system/project/getView?viewPage=project/projectDataAdd&ki='+pid
            });
        });
    }
    function addProjectOutDataEva() {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                title: '添加项目输出资料信息',
                type: 2,
                area: ['50%', '70%'],
                content: 'system/project/getView?viewPage=project/projectOutDataAdd&ki='+pid
            });
        });
    }
    function editProjectOutDataEva(data) {
        layui.use('layer', function () {
            console.log(data);
            var layer = layui.layer;
            layer.open({
                title: '添加项目输出资料信息',
                type: 2,
                area: ['50%', '70%'],
                content: 'system/project/getView?viewPage=project/projectOutDataEdit&ki='+ data.id
            });
        });
    }
    function addProjectStaffEva() {
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                title: '添加参与人员信息',
                type: 2,
                area: ['50%', '70%'],
                content: 'system/project/getView?viewPage=project/projectStaffAdd&ki='+pid
            });
        });
    }
    function editProjectStaffEva(data) {
        layui.use('layer', function () {
            var layer = layui.layer;
            humanDept = data.sid;
            layer.open({
                title: '添加项目输出资料信息',
                type: 2,
                area: ['50%', '70%'],
                content: 'system/project/getView?viewPage=project/projectStaffEdit&ki='+ data.id
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
    function refreachProjectData() {
        projectDataTable.reload({
            url: 'system/project/api/getProjectDataList?pid='+pid
        });
    }
    function refreachProjectOutData() {
        projectOutDataTable.reload({
            url: 'system/project/api/getProjectOutDataList?pid='+pid
        });
    }

    function refreachProjectStaff(){
        projectStaffTable.reload({
            url: 'system/project/api/getProjectStaffList?pid='+pid
        });
    }
</script>
<script type="text/html" id="projectDataToolBar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <%--<a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="cat">查看</a>--%>
    {{#
    var fn = function(name){
    return "<a class='layui-btn layui-btn-xs layui-btn-normal' target='_blank'  href='"+name+"'>查看</a>";
    };
    }}
    {{ fn(d.projectDataWay) }}
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/html" id="projectOutDataToolBar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    {{#
    var fn = function(name){
    return "<a class='layui-btn layui-btn-xs layui-btn-normal' target='_blank' href='"+name+"'>查看</a>";
    };
    }}
    {{ fn(d.projectOutDataWay) }}
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/html" id="projectStaffToolBar">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
    switch (0){
        case 0:
            break;
    }
</script>
<script type="text/html" id="projectDataType">
    {{# if(d.projectDataType === 0){ }}
        文本
    {{# }else if(d.projectDataType === 1){ }}
        图片
    {{# }else if(d.projectDataType === 2){ }}
        视频
    {{# }else if(d.projectDataType === 3){ }}
        链接
    {{# } }}
</script>
<script type="text/html" id="projectOutDataType">
    {{# if(d.projectOutDataType === 0){ }}
    文本
    {{# }else if(d.projectOutDataType === 1){ }}
    图片
    {{# }else if(d.projectOutDataType === 2){ }}
    视频
    {{# }else if(d.projectOutDataType === 3){ }}
    链接
    {{# } }}
</script>

</body>
</html>