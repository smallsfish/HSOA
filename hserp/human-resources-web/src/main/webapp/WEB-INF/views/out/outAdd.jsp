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

    <form class="layui-form" id="outAdd">

        <div class="layui-form-item" >
            <label class="layui-form-label">姓名:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入姓名" name="name" class="layui-input" required lay-verify="required" autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">性别:</label>
            <div class="layui-input-inline">
                <input type="radio" name="sex" value="true" title="男" checked>
                <input type="radio" name="sex" value="false" title="女">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">身份证号:</label>
            <div class="layui-input-inline">
                <input type="text"  placeholder="请输入身份证号" name="idCard" lay-verify="identity" placeholder="" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">手机号码:</label>
            <div class="layui-input-inline">
                <input type="tel" name="tel" placeholder="请输入手机号码" lay-verify="required|phone" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">学历:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入学历" name="education" class="layui-input" required lay-verify="required" autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">储备人员部门:</label>
            <div  class="layui-input-block" style="z-index: 99;">
                <select name="deptId" id="deptId">

                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">工资:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入工资" name="salary" class="layui-input" required lay-verify="required" autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">离职时间:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请选择离职时间" name="outTime" class="layui-input" id="date" lay-verify="date"  autocomplete="off">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">离职原因:</label>
            <div class="layui-input-inline">
                <textarea placeholder="请输入离职原因" class="layui-textarea layui-input" name="reason"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-inline sub" style="margin-left: 150px;">
                <button class="layui-btn" lay-submit="" lay-filter="outAdd">立即提交</button>
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
        laydate.render({
            elem: '#date1'
        });
        form.on('submit(outAdd)', function (data) {
            var loadIndex = layer.load();
            var fromData = new FormData($("#outAdd")[0]);
            console.log(fromData)
            $.ajax({
                type: "POST",
                dataType: "json",
                url: 'system/human/api/addOutEmp',
                data: fromData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (result) {
                    layer.close(loadIndex);
                    if (result.status == 0) {
                        $("#outAdd")[0].reset();
                        window.parent.refreach();
                    }
                    layer.msg(result.msg);
                },
                error: function (data) {
                    layer.close(loadIndex);
                    layer.msg("出现异常！");
                }
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });
        $.get('system/human/api/getDeptList', {},
            function (result) {
                if (result.code == 0) {
                    var html = '<option value="">请选择部门</option>';
                    $(result.data).each(
                        function (i, item) {
                            html += '<option value="' + item.id + '">' + item.name + '</option>';
                        });
                    $("#deptId").html(html);
                    form.render();
                } else {
                    console.log(result.msg);
                }
            });
    });
</script>
<script>

</script>
</body>
</html>