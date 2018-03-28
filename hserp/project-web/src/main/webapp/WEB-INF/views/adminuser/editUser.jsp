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
    <style>
        .upload-headimg{
            width: 120px;
            height: 120px;
            margin-left: 25px;
            margin-top: 10px;
            margin-bottom: 15px;
            background-color: #33CDE5;
            border-radius:120px;
            overflow: hidden;
            position: relative;
        }
        .upload-headimg img{
            position: absolute;
            width: 100%;
            height: 100%;
            top:0;
            left:0;
        }
        .upload-headimg button{
            position: absolute;
            background-color:rgba(0,0,0,0);
            width: 100%;
            height: 100%;
            z-index: 2;
        }
    </style>

</head>
<body>
<div class='mt'>

    <form class="layui-form" id="addAdmin">

        <div class="upload-headimg" id="headimg">
            <img src="uploadimage/${adminUser.headimage}" alt="个人头像">
            <button type="button" class="layui-btn" id="uploadheadimg">
                <i class="layui-icon">&#xe67c;</i>上传头像
            </button>
        </div>

        <div class="layui-form-item" >
            <label class="layui-form-label">账号:</label>
            <div class="layui-input-inline">
                <input type="text" name="account" lay-verify="required" placeholder="请输入账号" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">Email:</label>
            <div class="layui-input-inline">
                <input type="email" name="email" placeholder="请输入Email" lay-verify="required|email" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label"><span style="color: #f00;">*</span>角色：</label>
            <div class="layui-input-block">
                <select name="roles" id="roles" multiple lay-ignore style="width: 500px;">
                </select>
            </div>

        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">编号:</label>
            <div class="layui-input-inline">
                <input type="text" name="identifier" placeholder="请输入编号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">备注:</label>
            <div class="layui-input-inline">
                <textarea id="remarks" name="remarks" placeholder="请输入内容" class="layui-textarea layui-input"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-inline sub" style="margin-left: 150px;">
                <button class="layui-btn" lay-submit="" lay-filter="addAdmin">立即提交</button>
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
            elem: '#uploadheadimg' //绑定元素
            , url: 'system/updateAdminHeadImage' //上传接口
            , accept: 'file'
            , auto: true
            , data: {id: id, customDataType: type}
            , done: function (res) {
                layer.msg('更新文件成功！');
                $("#headimg img").attr("src", res.data);
                window.parent.refreach();
            }
            , error: function () {
                layer.msg('更新文件失败！');
            }
        });
        form.on('submit(addAdmin)', function (data) {
            var loadIndex = layer.load();
            var fromData = new FormData($("#addAdmin")[0]);
            fromData.append("id",id);
            $.ajax({
                type: "POST",
                dataType: "json",
                url: 'system/updateAdminUser',
                data: fromData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (result) {
                    layer.close(loadIndex);
                    if (result.status == 0) {
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
        $.get('system/getEditorAdminUserRole', {id:id}, function (result) {
            console.log(JSON.stringify(result));
            if (result.code == 0) {
                var html = '';
                $(result.data).each(function (i, item) {
                        if(item.available){
                            html += '<option value="' + item.id + '" ';
                            if(item.checked){
                                html+='selected';
                            }
                            html+=' >' + item.description + '</option>';
                        }
                 });
                $("#roles").html(html);
                form.render();
            } else {
                console.log(result.msg);
            }
        });
        $.get('system/getAdminUserById', {"id": id}, function (result) {

            if (result.status == 0) {
                $("#headimg img").attr("src", result.data.headimage);
                $(":input[name=account]").val(result.data.account);
                $(":input[name=email]").val(result.data.email);
                $(":input[name=identifier]").val(result.data.identifier);
                $("#remarks").val(result.data.remarks);

                form.render();
            } else {
                console.log(result.msg);

            }
        });
    });
</script>
</body>
</html>