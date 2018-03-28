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
<div class='humanAdd mt'>

    <form class="layui-form" id="customerAdd">

        <div class="layui-form-item" >
            <label class="layui-form-label">公司名称:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入公司名称" name="company" class="layui-input" required lay-verify="required" autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">公司规模:</label>
            <div class="layui-input-inline">
                <input type="radio" name="size" value="0" title="初创公司" checked="">
                <input type="radio" name="size" value="1" title="稳定发展">
                <input type="radio" name="size" value="2" title="大型公司">
                <input type="radio" name="size" value="3" title="上市公司">
            </div>
        </div>
        <div class="layui-form-item" >
            <label class="layui-form-label">公司负责人:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入公司负责人" name="responsiblePerson" class="layui-input" required lay-verify="required" autocomplete="off" >
            </div>
        </div>
        <div class="layui-form-item" >
            <label class="layui-form-label">公司领导:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入公司领导" name="lead" class="layui-input" required lay-verify="required" autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">客户状态:</label>
            <div class="layui-input-inline">
                <input type="radio" name="status" value="0" title="正常     " checked="">
                <input type="radio" name="status" value="1" title="删除     ">
                <input type="radio" name="status" value="2" title="锁定     ">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">公司电话:</label>
            <div class="layui-input-inline">
                <input type="tel" name="companyTel" placeholder="请输入公司电话" lay-verify="required|phone" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">公司邮箱:</label>
            <div class="layui-input-inline">
                <input type="tel" name="companyEamil" placeholder="请输入公司邮箱" lay-verify="email" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">公司联系人电话:</label>
            <div class="layui-input-inline">
                <input type="tel" name="responsibleTel" placeholder="请输入公司联系人电话" lay-verify="required|phone" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">公司成立时间:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请选择公司成立时间" name="companyTime" class="layui-input" id="date" lay-verify="date"  autocomplete="off">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">联系人生日:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请选择联系人生日" name="responsibleBirthday" class="layui-input" id="date1" lay-verify="date"  autocomplete="off">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-inline sub" style="margin-left: 150px;">
                <button class="layui-btn" lay-submit="" lay-filter="customerAdd">立即提交</button>
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
        form.on('submit(customerAdd)', function (data) {
            var loadIndex = layer.load();
            var fromData = new FormData($("#customerAdd")[0]);

            $.ajax({
                type: "POST",
                dataType: "json",
                url: 'system/project/api/addCustomResource',
                data: fromData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (result) {
                    layer.close(loadIndex);
                    if (result.status == 0) {
                        $("#customerAdd")[0].reset();
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