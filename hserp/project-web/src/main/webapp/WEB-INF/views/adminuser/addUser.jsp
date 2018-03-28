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

    <form class="layui-form" id="addAdmin">

        <div class="layui-form-item" >
            <label class="layui-form-label">头像:</label>
            <div class="layui-input-inline">
                <input type="file" name="file" lay-verify="required">
            </div>
        </div>

        <div class="layui-form-item" >
            <label class="layui-form-label">账号:</label>
            <div class="layui-input-inline">
                <input type="text" name="account" lay-verify="required" placeholder="请输入账号" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">Email:</label>
            <div class="layui-input-inline">
                <input type="email" name="email" placeholder="请输入Email" lay-verify="required|email" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: #f00;">*</span>角色:</label>
            <div class="layui-input-block">
                <select name="roles" id="roles" multiple lay-ignore style="width: 500px;">
                    <%--<c:forEach var="r" items="${roles}">--%>
                        <%--<option value="${r.id}" ${!r.available ? 'disabled':''}>${r.description}</option>--%>
                    <%--</c:forEach>--%>
                </select>
                <span style="margin-top: 5px;margin-left: 20px;">按住Shift多选</span>
            </div>
            <label class="layui-form-label"></label>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">编号:</label>
            <div class="layui-input-inline">
                <input type="text" name="identifier" placeholder="请输入编号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">备注:</label>
            <div class="layui-input-inline">
                <textarea name="remarks" placeholder="请输入内容" class="layui-textarea layui-input"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-inline sub" style="margin-left: 150px;">
                <button class="layui-btn" lay-submit="" lay-filter="addAdmin">立即提交</button>
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
        form.on('submit(addAdmin)', function (data) {
            var loadIndex = layer.load();
            var fromData = new FormData($("#addAdmin")[0]);

            $.ajax({
                type: "POST",
                dataType: "json",
                url: 'system/addAdminUser',
                data: fromData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (result) {
                    layer.close(loadIndex);
                    if (result.status == 0) {
                        $("#addAdmin")[0].reset();
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
        $.get('system/roleList', {}, function (result) {
            console.log(JSON.stringify(result));
            if (result.code == 0) {

                var html = '';
                $(result.data).each(function (i, item) {
                        html += '<option value="' + item.id + '">' + item.rolename + '</option>';
                 });
                console.log(html);
                $("#roles").html(html);
                form.render();
            } else {
                console.log(result.msg);
            }
        });
    });
</script>
</body>
</html>