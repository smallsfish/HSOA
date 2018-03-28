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

    <form class="layui-form" id="projectDataEdit">

        <div class="layui-form-item" >
            <label class="layui-form-label">项目输出资料名称:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入项目输出资料名称" name="projectOutDataName" class="layui-input" lay-verify="required" autocomplete="off" >
            </div>
        </div>
        <div class="layui-form-item" >
            <label class="layui-form-label">项目输出资料:</label>
            <div class="layui-input-inline">
                <button type="button" class="layui-btn" id="file" layui><i class="layui-icon"></i>上传文件</button>
            </div>
        </div>
        <div class="layui-form-item" >
            <label class="layui-form-label">项目输出资料类型:</label>
            <div class="layui-input-inline">
                <input type="radio" name="projectOutDataType" lay-verify="required" value="0" title="文本" id="text">
                <input type="radio" name="projectOutDataType" lay-verify="required" value="1" title="图片" id="pic">
                <input type="radio" name="projectOutDataType" lay-verify="required" value="2" title="视频" id="vedio">
                <input type="radio" name="projectOutDataType" lay-verify="required" value="3" title="链接" id="href">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">项目资料描述:</label>
            <div class="layui-input-block">
                <textarea id="textarea" name="projectOutDataInfo" placeholder="请输入内容" class="layui-textarea layui-input"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-inline sub" style="margin-left: 150px;">
                <button class="layui-btn" lay-submit="" lay-filter="projectDataEdit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<script>
    var layer = null;
    var id='${ki}',
    type;
    layui.use(['form', 'layer', 'laydate', 'upload'], function () {
        var form = layui.form,
            laydate = layui.laydate,
            layer = layui.layer,
            upload = layui.upload;

        laydate.render({
            elem: '#date'
        });

        var uploadInst = upload.render({
            elem: '#file' //绑定元素
            , url: 'system/project/api/updateProjectOutDataFile' //上传接口
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
                        $("#vedio").attr("checked", "checked");
                    } else if (filess == '.txt' || filess == '.doc' || filess == '.docx' || filess == '.hlp' || filess == '.wps' || filess == '.rtf' || filess == '.html' || filess == '.pdf' || filess == '.rar' || filess == '.zip' || filess == '.exe') {
                        type = 2;
                        $("#text").attr("checked", "checked");
                    } else if (filess == '.wav' || filess == '.aif' || filess == '.au' || filess == '.mp3') {
                        type = 3;
                        $("#href").attr("checked", "checked");
                    } else {
                        layer.msg('上传文件格式错误！');
                    }
                    uploadInst.config.data.projectOutDataType=type;
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

        form.on('submit(projectDataEdit)', function (data) {
            var loadIndex = layer.load();
            var fromData = new FormData($("#projectDataEdit")[0]);
            fromData.append("id",id);
            $.ajax({
                type: "POST",
                dataType: "json",
                url: 'system/project/api/updateProjectOutData',
                data: fromData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (result) {
                    layer.close(loadIndex);
                    if (result.status == 0) {
                        window.parent.refreachProjectOutData();
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
        $.get('system/project/api/getProjectOutDataById', {"id": id}, function (result) {
            console.log(result);
            if (result.code == 0) {
                $(":input[name='projectOutDataName']").val(result.data.projectOutDataName);
                $("#textarea").val(result.data.projectOutDataInfo);
                $(":input[name='projectOutDataType']:eq("+(result.data.projectOutDataType)+")").attr("checked",'checked');
                form.render();
            } else {
                console.log(result.msg);
            }
        });
    });
</script>
</body>
</html>