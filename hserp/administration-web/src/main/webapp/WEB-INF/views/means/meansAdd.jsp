<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="Keywords" content="IO"/>
    <meta name="description" content="海思数据中心IO管理系统"/>
    <title>注册资料新增</title>

    <link rel="stylesheet" href="/static/layui/css/layui.css"/>
    <link rel='stylesheet' type="text/css" href='/static/css/credit.css'>

    <!-- 配置文件 -->
    <script type="text/javascript" src="/static/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="/static/ueditor/ueditor.all.js"></script>

    <jsp:include page="../base.jsp"/>

</head>
<body>
<div class='mt'>
    <form class="layui-form" id="meansAdd">
        <div class="layui-form-item">
            <label class="layui-form-label">注册资料上传:</label>
            <div class="layui-input-inline">
                <input type="file" name="file" lay-verify="required" >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">注册资料描述:</label>
            <div class="layui-input-inline">
                <textarea id="newsContent"  name="text" style="width: 500px"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-inline sub" style="margin-left: 150px;">
                <button class="layui-btn" lay-submit="" lay-filter="meansAdd">立即提交</button>
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


        form.on('submit(meansAdd)', function (data) {
            var loadIndex = layer.load();
            var fromData = new FormData($("#meansAdd")[0]);

            $.ajax({
                type: "POST",
                dataType: "json",
                url: 'system/data/api/dataAdd',
                data: fromData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (result) {
                    layer.close(loadIndex);
                    if (result.code == 0) {
                        $("#meansAdd")[0].reset();
                        window.parent.refreach();
                    }
                    layer.msg(result.msg);
                },
                error: function (data) {
                    layer.close(loadIndex);
                    layer.msg("出现异常！");
                }
            });
            return false; //阻止
        });
    });

</script>
<script type="text/javascript">
    var ue = UE.getEditor("newsContent");
</script>
</body>
</html>