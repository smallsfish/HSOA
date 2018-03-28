<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>
    <jsp:include page="../base.jsp"/>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="Keywords" content="IO"/>
    <meta name="description" content="海思数据中心IO管理系统"/>
    <title>公章管理新增</title>

    <link rel="stylesheet" href="/static/layui/css/layui.css"/>
    <link rel='stylesheet' type="text/css" href='/static/css/credit.css'>

    <!-- 配置文件 -->
    <script type="text/javascript" src="/static/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="/static/ueditor/ueditor.all.js"></script>



</head>
<body>
<div class='mt'>
    <form class="layui-form" id="rmbAdd">

        <div class="layui-form-item">
            <label class="layui-form-label">姓名:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入姓名" name="user" class="layui-input" required lay-verify="required"
                       autocomplete="off">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">使用明细:</label>
            <div class="layui-input-inline">
                <textarea id="newsContent"  name="usedetails" style="width: 500px"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-inline sub" style="margin-left: 150px;">
                <button class="layui-btn" lay-submit="" lay-filter="rmbAdd">立即提交</button>
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

        form.on('submit(rmbAdd)', function (data) {
            var loadIndex = layer.load();
            var fromData = new FormData($("#rmbAdd")[0]);

            $.ajax({
                type: "POST",
                dataType: "json",
                url: 'system/administration/api/chapterAdd',
                data: fromData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (result) {
                    layer.close(loadIndex);
                    if (result.code == 0) {
                        $("#rmbAdd")[0].reset();
                        window.parent.refreach();
                    }
                    layer.msg(result.msg);
                },
                error: function (data) {
                    layer.close(loadIndex);
                    layer.msg("出现异常！");
                }
            });
            return false; //阻止-
        });
    });
</script>
<script type="text/javascript">
    var ue = UE.getEditor("newsContent");
</script>
</body>
</html>