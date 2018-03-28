<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="Keywords" content="IO"/>
    <meta name="description" content="海思数据中心IO管理系统"/>
    <title>人事管理修改</title>

    <link rel="stylesheet" href="/static/layui/css/layui.css"/>
    <link rel='stylesheet' type="text/css" href='/static/css/credit.css'>
    <jsp:include page="../base.jsp"/>

</head>
<body>
<div class='mt'>

    <form class="layui-form" id="customerEdit">

        <div class="layui-form-item" >
            <label class="layui-form-label">公司名称:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入公司名称" name="company" class="layui-input" required lay-verify="required" autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">公司规模:</label>
            <div class="layui-input-inline">
                <input type="radio" name="size" value="0" title="初创公司" id="first">
                <input type="radio" name="size" value="1" title="稳定发展" id="second">
                <input type="radio" name="size" value="2" title="大型公司" id="thred">
                <input type="radio" name="size" value="3" title="上市公司" id="four">
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
                <input type="radio" name="status" value="0" title="正常     " id="normal">
                <input type="radio" name="status" value="1" title="删除     " id="del">
                <input type="radio" name="status" value="2" title="锁定     " id="lock">
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
                <button class="layui-btn" lay-submit="" lay-filter="customerEdit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script>
    var layer = null;
    var id='${ki}';
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

        $.get('system/project/api/getCustomResourceById',{ "id" : window.parent.customerId },function (result) {
            if(result.code == 0){
                $("input[name=company]").val(result.data.company);
                $("input[name=responsiblePerson]").val(result.data.responsiblePerson);
                $("input[name=lead]").val(result.data.lead);
                $("input[name=companyTel]").val(result.data.companyTel);
                $("input[name=companyEamil]").val(result.data.companyEamil);
                $("input[name=companyTime]").val(result.data.companyTime);
                $("input[name=responsibleTel]").val(result.data.responsibleTel);
                $("input[name=responsibleBirthday]").val(result.data.responsibleBirthday);
                console.log(result.data.id);

                if ( result.data.size == 0 ) {
                    $("#first").prop("checked", true);
                } else if( result.data.size == 1 ){
                    $("#second").prop("checked", true);
                }else if( result.data.size == 2 ){
                    $("#thred").prop("checked", true);
                } else if( result.data.size == 3 ){
                    $("#four").prop("checked", true);
                }

                if (result.data.status == 0) {
                    $("#normal").prop("checked", true);
                } else if (result.data.status == 1){
                    $("#del").prop("checked", true);
                }else if (result.data.status == 2){
                    $("#lock").prop("checked", true);
                }
                form.render();
            }else {
                console.log(result.msg);
            }
        });

        form.on('submit(customerEdit)', function (data) {
            var loadIndex = layer.load();
            var fromData = new FormData($("#customerEdit")[0]);
            fromData.append("id",window.parent.customerId);
            $.ajax({
                type: "POST",
                dataType: "json",
                url: 'system/project/api/updateCustomResource',
                data: fromData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (result) {
                    layer.close(loadIndex);
                    if (result.status == 0) {
                        layer.msg("修改成功！");
                        window.parent.refreach();
                    }
                    layer.msg(result.msg);
                },
                error: function (data) {
                    layer.close(loadIndex);
                    layer.alert("出现异常！");
                }
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });
    });
</script>
</body>
</html>