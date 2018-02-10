<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="Keywords" content="IO"/>
    <meta name="description" content="海思数据中心IO管理系统"/>
    <title>人事管理新增</title>

    <link rel="stylesheet" href="/static/layui/css/layui.css"/>
    <link rel='stylesheet' type="text/css" href='/static/css/credit.css'>
    <jsp:include page="../base.jsp"/>

</head>
<body>
<div class='mt'>

    <form class="layui-form" id="projectAdd">

        <div class="layui-form-item" >
            <label class="layui-form-label">项目名称:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入项目名称" name="projectName" class="layui-input" lay-verify="required" autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item" >
            <label class="layui-form-label">项目周期(周):</label>
            <div class="layui-input-inline">
                <input type="number" placeholder="请输入项目周期(周)" name="projectRange" class="layui-input" lay-verify="required" autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">预计完工时间:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="预计完工时间" name="projectFinishDate" class="layui-input" id="date" lay-verify="date"  autocomplete="off">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">难度等级:</label>
            <div class="layui-input-inline">
                <input type="radio" name="projectLevel" value="1" title="1级">
                <input type="radio" name="projectLevel" value="2" title="2级" checked>
                <input type="radio" name="projectLevel" value="3" title="3级" checked>
                <input type="radio" name="projectLevel" value="4" title="4级" checked>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-inline sub" style="margin-left: 150px;">
                <button class="layui-btn" lay-submit="" lay-filter="projectAdd">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<script>
    var layer = null;
    layui.use(['form', 'layer', 'laydate'], function () {
        var form = layui.form,
        laydate = layui.laydate;
        layer = layui.layer;

        laydate.render({
            elem: '#date'
        });
        form.on('submit(projectAdd)', function (data) {
            var loadIndex = layer.load();
            var fromData = new FormData($("#projectAdd")[0]);

            $.ajax({
                type: "POST",
                dataType: "json",
                url: 'system/project/api/addProject',
                data: fromData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (result) {
                    layer.close(loadIndex);
                    if (result.status == 0) {
                        $("#projectAdd")[0].reset();
                        window.parent.refreach();
                    }
                    layer.msg(result.msg);
                },
                error: function (data) {
                    layer.close(loadIndex);
                    alert("出现异常！");
                }
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });
    });
</script>
</body>
</html>