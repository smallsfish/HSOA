<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="Keywords" content="IO"/>
    <meta name="description" content="海思数据中心IO管理系统"/>
    <title>项目输出资料修改</title>

    <link rel="stylesheet" href="/static/layui/css/layui.css"/>
    <link rel='stylesheet' type="text/css" href='/static/css/credit.css'>
    <jsp:include page="../base.jsp"/>

</head>
<body>
<div class='mt'>

    <form class="layui-form" id="projectStaffEdit">

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
                <textarea name="projectStaffModule" id="textarea" placeholder="请输入内容" class="layui-textarea layui-input" ></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-inline sub" style="margin-left: 150px;">
                <button class="layui-btn" lay-submit="" lay-filter="projectStaffEdit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<script>
    var layer = null;
    var id='${ki}',
    type;
    layui.use(['form', 'layer'], function () {
        var form = layui.form,
            layer = layui.layer;


        form.on('submit(projectStaffEdit)', function (data) {
            var loadIndex = layer.load();
            var fromData = new FormData($("#projectStaffEdit")[0]);
            fromData.append("id",id);
            $.ajax({
                type: "POST",
                dataType: "json",
                url: 'system/project/api/updateProjectStaff',
                data: fromData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (result) {
                    layer.close(loadIndex);
                    if (result.status == 0) {
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
        $.get('system/project/api/getProjectStaffById', {"id": id}, function (result) {

            if (result.code == 0) {
                $("select[name=sid]").val(result.data.sid);
                $("#textarea").val(result.data.projectStaffModule);
                form.render();
            } else {
                console.log(result.msg);

            }
        });

        $.get('system/human/api/getEmpList', {}, function (result) {
            if (result.code == 0) {

                var html = '<option value="">请选择一个人员</option>';
                $(result.data).each(
                    function (i, item) {
                        if(item.name === window.parent.humanDept) {

                            html += '<option value="' + item.id + '" selected>' + item.name + '</option>';
                        }else{
                            html += '<option value="' + item.id + '">' + item.name + '</option>';

                            console.log(window.parent.humanDept);

                            console.log(html);
                        }
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