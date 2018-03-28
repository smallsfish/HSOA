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
    <!-- 配置文件 -->
    <script type="text/javascript" src="/static/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="/static/ueditor/ueditor.all.js"></script>


</head>
<body>
<div class='mt'>
    <form class="layui-form" id="customerDataEdit">
        <div class="layui-form-item">
            <label class="layui-form-label">客户资料名称:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入客户资料名称" name="customDataName" class="layui-input" required
                       lay-verify="required" autocomplete="off">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">客户资料类型:</label>
            <div class="layui-input-inline">
                <input type="radio" name="customDataType" value="0" title="图片" id="pic">
                <input type="radio" name="customDataType" value="1" title="视频" id="vid">
                <input type="radio" name="customDataType" value="2" title="文档" id="word">
                <input type="radio" name="customDataType" value="3" title="语音" id="hello">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">资料文件:</label>
            <div class="layui-input-inline">

                <button type="button" class="layui-btn" id="file" layui><i class="layui-icon"></i>上传文件</button>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">客户资料信息:</label>
            <div class="layui-input-inline">
                <textarea id="newsContent" name="customDataInfo" style="width: 540px"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-inline sub" style="margin-left: 150px;">
                <button class="layui-btn" lay-submit="" lay-filter="customerDataEdit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script>
    var id = '${ki}',
        type;
    layui.use(['form', 'layer', 'upload'], function () {
        var form = layui.form,
            layer = layui.layer,
            upload = layui.upload;

        var uploadInst = upload.render({
            elem: '#file' //绑定元素
            , url: 'system/project/api/updateCustomDataFile' //上传接口
            , accept: 'file'
            , auto: false
            , data: {id: id, customDataType: type}
            , choose: function (obj) {
                //将每次选择的文件追加到文件队列
                var files = obj.pushFile();

                //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
                obj.preview(function (index, file, result) {
                    var filess = file.name;
                    var index = filess.indexOf('.');
                    filess = filess.substring(index);
                    if (filess == ".bmp" || filess === ".png" || filess == ".gif" || filess == ".jpg" || filess == ".jpeg") {
                        type = 0;
                        $("#pic").attr("checked", "checked");
                    } else if (filess == '.mp4' || filess == '.rmvb' || filess == '.avi' || filess == '.ts') {
                        type = 1;
                        $("#vid").attr("checked", "checked");
                    } else if (filess == '.txt' || filess == '.doc' || filess == '.docx' || filess == '.hlp' || filess == '.wps' || filess == '.rtf' || filess == '.html' || filess == '.pdf' || filess == '.rar' || filess == '.zip' || filess == '.exe') {
                        type = 2;
                        $("#word").attr("checked", "checked");
                    } else if (filess == '.wav' || filess == '.aif' || filess == '.au' || filess == '.mp3') {
                        type = 3;
                        $("#hello").attr("checked", "checked");
                    } else {
                        layer.msg('上传文件格式错误！');
                    }
                    uploadInst.config.data.customDataType=type;
                    console.log(uploadInst.config.data.customDataType);
                    obj.upload(index,file);
                    form.render();
                });
            }
            , done: function (res) {
                layer.msg('更新文件成功！');
            }
            , error: function () {
                layer.msg('更新文件失败！');
            }
        });

        $.get('system/project/api/getCustomDataById', {"id": id}, function (result) {
            if (result.code == 0) {
                $("input[name=customDataName]").val(result.data.customDataName);

                if (result.data.customDataType == 0) {
                    $("#pic").prop("checked", true);
                } else if (result.data.customDataType == 1) {
                    $("#vid").prop("checked", true);
                } else if (result.data.customDataType == 2) {
                    $("#word").prop("checked", true);
                } else if (result.data.customDataType == 3) {
                    $("#hello").prop("checked", true);
                }

                var ue = UE.getEditor("newsContent");
                ue.ready(function () {
                    ue.setContent(result.data.customDataInfo);
                });

                form.render();
            } else {
                console.log(result.msg);
            }
        });

        form.on('submit(customerDataEdit)', function (data) {
            var loadIndex = layer.load();
            var fromData = new FormData($("#customerDataEdit")[0]);
            fromData.append("id", id);

            $.ajax({
                type: "POST",
                dataType: "json",
                url: 'system/project/api/updateCustomData',
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
<script type="text/javascript">
    var ue = UE.getEditor("newsContent");
</script>
</body>
</html>