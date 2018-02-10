<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="Keywords" content="IO"/>
    <meta name="description" content="海思数据中心IO管理系统"/>
    <title>部门新增</title>

    <link rel="stylesheet" href="/static/layui/css/layui.css"/>
    <link rel='stylesheet' type="text/css" href='/static/css/credit.css'>
    <jsp:include page="../base.jsp"/>

</head>
<body>
<div class='deptAdd mt'>

    <form class="layui-form" id="deptAdd">

        <div class="layui-form-item" >
            <label class="layui-form-label">部门名称:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入部门名称" name="name" class="layui-input" required lay-verify="required" autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-inline sub" style="margin-left: 150px;">
                <button class="layui-btn" lay-submit="" lay-filter="deptAdd">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="layui/layui.js"></script>

<script>
    var layer = null;
    layui.use(['form', 'layer'], function () {
        var form = layui.form,
        layer = layui.layer;

        form.on('submit(deptAdd)', function (data) {
            var loadIndex = layer.load();
            var fromData = new FormData($("#deptAdd")[0]);

            $.ajax({
                type: "POST",
                dataType: "json",
                url: 'system/human/api/addDept',
                data: fromData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (result) {
                    layer.close(loadIndex);
                    if (result.status == 0) {
                        $("#deptAdd")[0].reset();
                        window.parent.refreach();
                    }
                    layer.msg(result.msg);
                },
                error: function (data) {
                    layer.close(loadIndex);
                    alert("出现异常！");
                }
            });
            return false;
        });
    });
</script>
</body>
</html>