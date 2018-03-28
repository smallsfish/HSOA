<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="Keywords" content="IO"/>
    <meta name="description" content="海思数据中心IO管理系统"/>
    <title>项目参与人员新增</title>

    <link rel="stylesheet" href="/static/layui/css/layui.css"/>
    <link rel='stylesheet' type="text/css" href='/static/css/credit.css'>
    <jsp:include page="../base.jsp"/>

</head>
<body>
<div class='mt'>

    <form class="layui-form" id="projectAdd">

        <div class="layui-form-item" >
            <label class="layui-form-label">选择人员:</label>
            <div class="layui-input-block">
                <select name="sid" lay-verify="" id="dept">
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">模块说明:</label>
            <div class="layui-input-block">
                <textarea name="projectStaffModule" placeholder="请输入内容" class="layui-textarea layui-input" ></textarea>
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
    var pid='${ki}';
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
            fromData.append("pid",pid);

            $.ajax({
                type: "POST",
                dataType: "json",
                url: 'system/project/api/addProjectStaff',
                data: fromData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (result) {
                    layer.close(loadIndex);
                    if (result.status == 0) {
                        $("#projectAdd")[0].reset();
                        window.parent.refreachProjectStaff();
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
        $.get('system/human/api/getEmpList', {},
            function (result) {
                if (result.code == 0) {
                    var html = '<option value="">请选择一个人员</option>';
                    $(result.data).each(
                        function (i, item) {
                            html += '<option value="' + item.id + '">' + item.name + '</option>';
                        });
                    $("#dept").html(html);
                    form.render();
                } else {
                    console.log(result.msg);
                }
            });
    });
</script>
</body>
</html>