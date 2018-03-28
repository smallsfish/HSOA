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
    <!-- 配置文件 -->
    <script type="text/javascript" src="/static/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="/static/ueditor/ueditor.all.js"></script>

</head>
<body>
<div class='mt'>

    <form class="layui-form" id="customerDataAdd">

        <div class="layui-form-item" >
            <label class="layui-form-label">客户资料名称:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入客户资料名称" name="customDataName" class="layui-input" required lay-verify="required" autocomplete="off" >
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">客户资料类型:</label>
            <div class="layui-input-inline">
                <input type="radio" name="customDataType" value="0" title="图片" checked="">
                <input type="radio" name="customDataType" value="1" title="视频">
                <input type="radio" name="customDataType" value="2" title="文档">
                <input type="radio" name="customDataType" value="3" title="语音">
            </div>
        </div>
        <div class="layui-form-item" >
            <label class="layui-form-label">资料文件:</label>
            <div class="layui-input-inline">
                <input type="file" name="file" lay-verify="required" >
            </div>
        </div>
        <div class="layui-form-item" >
            <label class="layui-form-label">客户资料信息:</label>
            <div class="layui-input-inline">
                <textarea id="newsContent"  name="customDataInfo" style="width: 540px"></textarea>
            </div>
        </div>



        <div class="layui-form-item">
            <div class="layui-input-inline sub" style="margin-left: 150px;">
                <button class="layui-btn" lay-submit="" lay-filter="customerDataAdd">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<script>
    var layer = null;
    var cid='${ki}';

    layui.use(['form', 'layer'], function () {
        var form = layui.form,
        layer = layui.layer;

        form.on('submit(customerDataAdd)', function (data) {
            var loadIndex = layer.load();
            var fromData = new FormData($("#customerDataAdd")[0]);
            fromData.append('cid',cid);
            $.ajax({
                type: "POST",
                dataType: "json",
                url: 'system/project/api/addCustomData',
                data: fromData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (result) {
                    layer.close(loadIndex);
                    if (result.status == 0) {
                        $("#customerDataAdd")[0].reset();
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
<script type="text/javascript">
    var ue = UE.getEditor("newsContent");
</script>
</body>
</html>