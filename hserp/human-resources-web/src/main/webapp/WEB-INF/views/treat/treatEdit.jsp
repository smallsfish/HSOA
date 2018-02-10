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

    <form class="layui-form" id="treatEdit">

        <div class="layui-form-item" >
            <label class="layui-form-label">姓名:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入姓名" name="name" class="layui-input" required lay-verify="required" autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">工资:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入工资" name="salary" class="layui-input" required lay-verify="required" autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">所属部门:</label>
            <div  class="layui-input-block" style="z-index: 99;">
                <select name="deptId" id="deptId"></select>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-inline sub" style="margin-left: 150px;">
                <button class="layui-btn" lay-submit="" lay-filter="treatEdit">立即提交</button>
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

        $.get('system/human/api/getTreatById',{ "id" : window.parent.treatId },function (result) {
            layer.msg(JSON.stringify(result));
            if(result.code == 0){
                $("input[name=name]").val(result.data.name);
                $("input[name=salary]").val(result.data.salary);
                $("select[name=deptId]").val(result.data.id);
                form.render();
            }else {
                console.log(result.msg);
            }
        });

        form.on('submit(treatEdit)', function (data) {
            var loadIndex = layer.load();
            var fromData = new FormData($("#treatEdit")[0]);
            fromData.append("id",window.parent.treatId);
            $.ajax({
                type: "POST",
                dataType: "json",
                url: 'system/human/api/updateTreat',
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
        $.get('system/human/api/getDeptList', {},
            function(result) {
                if (result.code == 0) {
                    var html = '<option value="">请选择部门</option>';
                    $(result.data).each(
                        function(i, item) {
                            if(item.name === window.parent.treatDept){
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