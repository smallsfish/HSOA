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

    <form class="layui-form" id="evaluationAdd">

        <div class="layui-form-item" >
            <label class="layui-form-label">选择人员:</label>
            <div class="layui-input-block">
                <select name="userId" id="dept">
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">基础评分:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入基础评分" id="basics" name="basics" class="layui-input" required lay-verify="required" autocomplete="off"   onkeyup="value=test(value)?value:''">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">输出技术类文档:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入输出技术类文档评分" name="technology" class="layui-input" required lay-verify="required" autocomplete="off"  onkeyup="value=test(value)?value:''">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">输出非技术文档:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入输出非技术文档评分" name="nontechnology" class="layui-input" required lay-verify="required" autocomplete="off"  onkeyup="value=test(value)?value:''">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">客户满意:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入客户满意评分" name="customer" class="layui-input" required lay-verify="required" autocomplete="off"  onkeyup="value=test(value)?value:''">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">行为效率:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入行为效率评分" name="behaviorquality" class="layui-input" required lay-verify="required" autocomplete="off"  onkeyup="value=test(value)?value:''">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">开发效率:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入开发效率评分" name="development" class="layui-input" required lay-verify="required" autocomplete="off"  onkeyup="value=test(value)?value:''">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">稳定性:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入稳定性评分" name="comprehensive" class="layui-input" required lay-verify="required" autocomplete="off"  onkeyup="value=test(value)?value:''">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-inline sub" style="margin-left: 150px;">
                <button class="layui-btn" lay-submit="" lay-filter="evaluationAdd">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<script>
    function test(num) {
        var reg = /^((?!0)\d{1,2}|100)$/;
        if(!num.match(reg)){
            layer.alert("请输入0-100的整数")
            return false;
        }else{
            return true;
        }
    }
</script>
<script>

    var layer = null;
    layui.use(['form', 'layer'], function () {
        var form = layui.form,
        layer = layui.layer;


        form.on('submit(evaluationAdd)', function (data) {
            var loadIndex = layer.load();
            var fromData = new FormData($("#evaluationAdd")[0]);

            $.ajax({
                type: "POST",
                dataType: "json",
                url:'system/performance/addAssessment',
                data: fromData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (result) {
                    layer.close(loadIndex);
                    if (result.status == 0) {
                        $("#evaluationAdd")[0].reset();
                        window.parent.refreach();
                        layer.msg("添加成功！");
                    }else{
                        layer.msg(result.msg);
                    }

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