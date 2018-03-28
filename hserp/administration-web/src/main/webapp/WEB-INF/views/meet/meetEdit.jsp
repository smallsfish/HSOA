<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="Keywords" content="IO"/>
    <meta name="description" content="海思数据中心IO管理系统"/>
    <title>会议管理修改</title>

    <link rel="stylesheet" href="/static/layui/css/layui.css"/>
    <link rel='stylesheet' type="text/css" href='/static/css/credit.css'>
    <jsp:include page="../base.jsp"/>

    <!-- 配置文件 -->
    <script type="text/javascript" src="/static/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="/static/ueditor/ueditor.all.js"></script>

</head>
<body>
<div class='humanEdit mt'>

    <form class="layui-form" id="meetEdit">

        <div class="layui-form-item">
            <label class="layui-form-label">参与人数:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入参与人数" name="participateNumber" class="layui-input" required lay-verify="required"
                       autocomplete="off">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">应参与人数:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入应参与人数" name="shouldparticipateNumber" class="layui-input" required lay-verify="required"
                       autocomplete="off">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">实参与人数:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入实参与人数" name="realparticipateNumber" class="layui-input" required lay-verify="required"
                       autocomplete="off">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">会议内容:</label>
            <div class="layui-input-inline">
                <textarea id="newsContent"  name="content" style="width: 500px"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-inline sub" style="margin-left: 150px;">
                <button class="layui-btn" lay-submit="" lay-filter="meetEdit">立即提交</button>
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

        $.post('system/meeting/api/meetingEditById',{ "id" : window.parent.meetId },function (result) {
            if(result.code == 0){
                $("input[name=participateNumber]").val(result.data.participateNumber);
                $("input[name=shouldparticipateNumber]").val(result.data.shouldparticipateNumber);
                $("input[name=realparticipateNumber]").val(result.data.realparticipateNumber);

                var ue = UE.getEditor("newsContent");
                ue.ready(function() {
                    ue.setContent(result.data.content);
                });

                form.render();

            }else {
                console.log(result.msg);
            }
        });

        form.on('submit(meetEdit)', function (data) {
            var loadIndex = layer.load();
            var fromData = new FormData($("#meetEdit")[0]);
            fromData.append("id",window.parent.meetId);
            $.ajax({
                type: "POST",
                dataType: "json",
                url: 'system/meeting/api/meetingSave',
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

    //不可以输入
    $("input[name='amount']").keyup(function(){
        var tmptxt=$(this).val();
        $(this).val(tmptxt.replace(/[^0-9.]/g,''));
    }).bind("paste",function(){
        var tmptxt=$(this).val();
        $(this).val(tmptxt.replace(/[^0-9.]/g,''));
    }).css("ime-mode", "disabled");

</script>

<script type="text/javascript">
    var ue = UE.getEditor("newsContent");
</script>
</body>
</html>