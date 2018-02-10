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

    <form class="layui-form" id="outEdit">

        <div class="layui-form-item" >
            <label class="layui-form-label">姓名:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入姓名" name="name" class="layui-input" required lay-verify="required" autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">性别:</label>
            <div class="layui-input-inline">
                <input type="radio" name="sex" value="true" title="男" id="man">
                <input type="radio" name="sex" value="false" title="女" id="woman">
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
            <label class="layui-form-label">职员所属部门:</label>
            <div  class="layui-input-block" style="z-index: 99;">
                <select name="deptId" id="deptId"></select>
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
                <button class="layui-btn" lay-submit="" lay-filter="outEdit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script>
    var layer = null;
    layui.use(['form', 'layer'], function () {
        var form = layui.form;
        layer = layui.layer;

        $.get('system/human/api/getOutEmpById',{ "id" : window.parent.outId },function (result) {

            if(result.code == 0){
                $("input[name=name]").val(result.data.name);
                $("input[name=idCard]").val(result.data.idCard);
                $("input[name=tel]").val(result.data.tel);
                $("input[name=education]").val(result.data.education);
                $("select[name=deptId]").val(result.data.id);
                $("input[name=salary]").val(result.data.salary);
                $("input[name=outTime]").val(result.data.outTime);
                $("textarea[name=reason]").val(result.data.reason);

                if (result.data.sex == "男") {
                    $("#man").prop("checked", true);
                } else {
                    $("#woman").prop("checked", true);
                }
                form.render();

            }else {
                console.log(result.msg);
            }
        });

    form.on('submit(outEdit)', function (data) {
            var loadIndex = layer.load();
            var fromData = new FormData($("#outEdit")[0]);
            console.log(data)
            fromData.append("id",window.parent.outId);
            $.ajax({
                type: "POST",
                dataType: "json",
                url: 'system/human/api/updateOutEmp',
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
        // form.on('submit(outEdit)', function (data) {
        //     var loadIndex = layer.load();
        //     console.log(data.field.reason)
        //    $.post("system/human/api/updateOutEmp",{
        //        id: data.field.deptId,
        //        name: data.field.name,
        //        idCard: data.field.idCard,
        //        tel: data.field.tel,
        //        education: data.field.education,
        //        salary:data.field.salary,
        //        outTime: data.field.outTime,
        //        reason: data.field.reason
        //        },function (result) {
        //             layer.close(loadIndex);
        //             if(result.code==0){
        //                 layer.msg(result.msg)
        //             }else {
        //                 layer.msg(result.msg)
        //             }
        //        }
        //    )
        //     return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        // });
        $.get('system/human/api/getDeptList', {},
            function(result) {
                if (result.code == 0) {
                    var html = '<option value="">请选择部门</option>';
                    $(result.data).each(
                        function(i, item) {
                            if(item.name === window.parent.outDept){
                                html += '<option value="' + item.id + '" selected>' + item.name + '</option>';
                            }else{
                                html += '<option value="' + item.id + '">' + item.name + '</option>';
                            }
                        });
                    $("#deptId").html(html);
                    form.render();
                } else {
                    console.log(result.msg);
                }
            });
    });
</script>
</body>
</html>