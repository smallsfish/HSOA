<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html lang="en">
<head>

    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="Keywords" content="IO"/>
    <meta name="description" content="海思数据中心IO管理系统"/>
    <title>考勤管理列表</title>

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
            <input type="search" placeholder="输入姓名以查询" id="searchName" name="name" class='seaerch-txt'>
            <input type="button" value="搜 索" class="layui-btn layui-btn-normal s-btn" id="searchSubmit" onclick="searchSubmit()">
        </div>
        <div class='search-add fr'>
            <%--<i class='iconfont icon-tianjia' onclick='addEva()' title='添加'></i>--%>
            <i class='iconfont icon-icon-refresh' onclick='refreach()' title='刷新'></i>
        </div>
    </div>

    <table class="layui-hide" id="test" lay-filter="infoTable"></table>
</div>
<script>
    var attendanceId=null;
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
                ,url:'system/attendance/api/attendanceList'
                ,height: 'full-78'
                ,cols: [[
                    {field:'name', width: 160, title: '姓名', align: 'center'}
                    ,{field:'onTime', width: twidth*0.1, title: '上班打卡时间',  align: 'center'}
                    ,{field:'underTime', width: twidth*0.1, title: '下班打卡时间',  align: 'center'}
                    ,{field:'timeresult', width: twidth*0.1, title: '时间结果',  align: 'center',templet:'#result'}
                    ,{field:'locationresult', width: twidth*0.1, title: '位置结果',  align: 'center',templet:'#location'}
                    ,{field:'remarks', width: twidth*0.1, title: '备注', align: 'center',edit:'text'}

                ]]
                , limits: [15, 30, 45, 60, 75]
                , limit: 15
                ,page: true
            });

            // table.on('tool(infoTable)', function (obj) {
            //     var data = obj.data;
            //     var layEvent = obj.event;
            //     if (layEvent === 'edit') { //编辑
            //         editorUser(data);
            //     }
            // });

            table.on('edit(infoTable)', function(obj){
                loadIndex = layer.load();

                $.ajax({
                    type:'POST',
                    dataType:'json',
                    url:'system/attendance/api/attendanceEdit',
                    data:{'id':obj.data.id,'remarks':obj.value},
                    success: function (result) {
                        layer.close(loadIndex);
                        layer.msg(result.msg);
                    },
                    error: function(data) {
                        layer.close(loadIndex);
                        layer.alert("出现异常！");
                    }
                })
            });
        });
    });

    function searchSubmit() {
        var userName = $(":input[name='name']").val();
        searchTable.reload({
            url: 'system/attendance/api/attendanceList?name=' + userName
        });
    }
    // function editorUser(data) {
    //     layui.use('layer', function () {
    //         var layer = layui.layer;
    //         attendanceId = data.id;
    //         layer.open({
    //             title: data.name + ' 用户编辑',
    //             type: 2,
    //             area: ['50%', '70%'],
    //             content: 'system/administration/attendanceEdit?id=' + data.id,
    //         });
    //     });
    // }

    function refreach() {
        searchTable.reload({
            url:'system/attendance/api/attendanceList'
        });
    }
</script>
<%--<script type="text/html" id="infoToolBar">--%>
    <%--<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>--%>
    <%--&lt;%&ndash;<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>&ndash;%&gt;--%>

    <%--<!-- 这里同样支持 laytpl 语法，如： -->--%>
    <%--{{#  if(d.auth > 2){ }}--%>
    <%--<a class="layui-btn layui-btn-xs" lay-event="check">审核</a>--%>
    <%--{{#  } }}--%>
<%--</script>--%>
<script type="text/html" id="result">
    {{# if(d.timeresult === "Normal"){ }}
    正常
    {{# }else if(d.timeresult === "Early"){ }}
    早退
    {{# }else if(d.timeresult === "Late"){ }}
    迟到
    {{# }else if(d.timeresult === "SeriousLate"){ }}
    严重迟到
    {{# }else if(d.timeresult === "Absenteeism"){ }}
    旷工
    {{# }else if(d.timeresult === "NotSigned"){ }}
    未打卡
    {{# } }}
</script>
<script type="text/html" id="location">
    {{# if(d.locationresult === "Normal"){ }}
    范围内
    {{# }else if(d.locationresult === "Outside"){ }}
    范围外
    {{# }else if(d.locationresult === "NotSigned"){ }}
    未打卡
    {{# } }}
</script>

</body>
</html>