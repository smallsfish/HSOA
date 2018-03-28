<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="Keywords" content="IO"/>
    <meta name="description" content="海思数据中心IO管理系统"/>
    <title>报销管理修改</title>

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

    <form class="layui-form" id="meansEdit">

        <div class="layui-form-item">
            <label class="layui-form-label">注册资料上传:</label>
            <div class="layui-input-inline">
                <button type="button" class="layui-btn" id="file" layui><i class="layui-icon"></i>上传文件</button>
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
                <button class="layui-btn" lay-submit="" lay-filter="meansEdit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script>
    var layer = null;
    layui.use(['form', 'layer', 'upload'], function () {
        var form = layui.form,
            layer = layui.layer,
            upload = layui.upload;

        $.post('system/data/api/dataEditById',{ "id" : window.parent.meansId },function (result) {
            if(result.code == 0){
                var ue = UE.getEditor("newsContent");
                ue.ready(function() {
                    ue.setContent(result.data.text);
                });

                form.render();

            }else {
                console.log(result.msg);
            }
        });

        var uploadInst = upload.render({
            elem: '#file' //绑定元素
            , url: 'system/data/api/dataUpdateFile' //上传接口
            , accept: 'file'
            , auto: true
            , data: {id: window.parent.meansId}
            , done: function (res) {
                layer.msg('更新文件成功！');
            }
            , error: function () {
                layer.msg('更新文件失败！');
            }
        });

        form.on('submit(meansEdit)', function (data) {
            var loadIndex = layer.load();
            var fromData = new FormData($("#meansEdit")[0]);
            fromData.append("id",window.parent.meansId);
            $.ajax({
                type: "POST",
                dataType: "json",
                url: 'system/data/api/dataSave',
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
                    layer.msg("出现异常！");
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