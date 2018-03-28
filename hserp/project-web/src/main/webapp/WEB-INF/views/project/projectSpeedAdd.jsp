<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="Keywords" content="OA"/>
    <meta name="description" content="海思数据中心OA管理系统"/>
    <title>项目进度新增</title>

    <link rel="stylesheet" href="/static/layui/css/layui.css"/>
    <link rel='stylesheet' type="text/css" href='/static/css/credit.css'>
    <jsp:include page="../base.jsp"/>

</head>
<body>
<div class='mt'>

    <form class="layui-form" id="projectAdd">

        <div class="layui-form-item" >
            <label class="layui-form-label">项目进度:</label>
            <div class="layui-input-block">
                <input type="text" placeholder="请输入项目进度（百分比）" name="precend" class="layui-input" lay-verify="required" autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">项目进度描述:</label>
            <div class="layui-input-block">
                <textarea name="descript" placeholder="请输入项目进度描述" class="layui-textarea layui-input" ></textarea>
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
    layui.use(['form', 'layer'], function () {
        var form = layui.form,
        layer = layui.layer;

        form.on('submit(projectAdd)', function (data) {
            var loadIndex = layer.load();
            var fromData = new FormData($("#projectAdd")[0]);
            fromData.append("pid",pid);

            $.ajax({
                type: "POST",
                dataType: "json",
                url: 'system/project/api/addProjectProgress',
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

    $("input[name='precend']").keyup(function(){
        var tmptxt=$(this).val();
        var reg = new RegExp("^(\\d|[1-9]\\d|100)$");
        if(!reg.test(tmptxt)) {
            layer.alert("请输入0-100的整数！");
            this.value = '';
        }
    })
</script>
</body>
</html>