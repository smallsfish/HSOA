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
<div class='humanEdit mt'>

    <form class="layui-form" id="cachetEdit">

        <div class="layui-form-item">
            <label class="layui-form-label">审批人:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入审批人" name="approver" class="layui-input" required lay-verify="required"
                       autocomplete="off">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">报销金额（元）:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入报销金额" name="amount" class="layui-input" required lay-verify="required"
                       autocomplete="off">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">报销类别:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入报销类别" name="category" class="layui-input" required lay-verify="required"
                       autocomplete="off">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">费用明细:</label>
            <div class="layui-input-inline">
                <textarea id="newsContent"  name="details" style="width: 500px"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">抄送人:</label>
            <div class="layui-input-inline">
                <input type="text" placeholder="请输入抄送人111" name="ccperson" class="layui-input" required lay-verify="required"
                       autocomplete="off">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-inline sub" style="margin-left: 150px;">
                <button class="layui-btn" lay-submit="" lay-filter="cachetEdit">立即提交</button>
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

        $.post('system/reimbursement/api/reimbursementEditById',{ "id" : window.parent.cachetId },function (result) {
            if(result.code == 0){
                $("input[name=approver]").val(result.data.approver);
                $("input[name=amount]").val(result.data.amount);
                $("input[name=category]").val(result.data.category);
                $("input[name=ccperson]").val(result.data.ccperson);

                var ue = UE.getEditor("newsContent");
                ue.ready(function() {
                    ue.setContent(result.data.details);
                });

                form.render();

            }else {
                console.log(result.msg);
            }
        });

        form.on('submit(cachetEdit)', function (data) {
            var loadIndex = layer.load();
            var fromData = new FormData($("#cachetEdit")[0]);
            fromData.append("id",window.parent.cachetId);
            $.ajax({
                type: "POST",
                dataType: "json",
                url: 'system/reimbursement/api/reimbursementSave',
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