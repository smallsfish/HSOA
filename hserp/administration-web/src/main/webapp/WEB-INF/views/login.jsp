<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="Keywords" content="OA"/>
    <meta name="description" content="海思数据中心OA管理系统"/>
    <title>登录</title>

    <link rel="stylesheet" href="http://at.alicdn.com/t/font_562689_bvfsvay973edbo6r.css">
    <link rel="stylesheet" href="/static/css/public.css">
    <link rel="stylesheet" href="/static/layui/css/layui.css"/>

    <link rel='stylesheet' type="text/css" href='/static/css/login.css'>

    <script src="/static/js/jquery-3.2.1.min.js"></script>
    <script src="/static/layui/layui.js"></script>
</head>
<body>
<div class="login">
    <%--开始头部--%>
    <div class="header">
        <div class="h-box">
            <div class="h-logo">
                <img src="/static/images/logo.jpg" alt="">
            </div>
            <div class="h-right">
                <a href="">使用说明</a>
            </div>
        </div>
    </div>

    <%--开始身体--%>
    <div class="content">
        <div class="login-box">
            <div class="c-title">
                <h3>登录</h3>
            </div>
            <form class="layui-form" id="login">
                <div class="layui-form-item">
                    <label class="layui-form-label">用户名 :</label>
                    <div class="layui-input-inline">
                        <input type="text" placeholder="请输入用户名" name="account" class="layui-input" required
                               lay-verify="required"
                               autocomplete="off">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">密码 :</label>
                    <div class="layui-input-inline">
                        <input type="password" placeholder="请输入密码" name="password" class="layui-input" required
                               lay-verify="pass"
                               autocomplete="off">
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-input-inline">
                        <button class="layui-btn layui-btn-color" lay-submit="" lay-filter="login">登 录</button>
                    </div>
                </div>
            </form>

        </div>
    </div>
    <%--开始底部--%>
    <div class="footer">
        <ul class="clearfix">
            <li class="fl">关于海思</li>
            <li class="fl">About Hassdata</li>
            <li class="fl">服务协议</li>
            <li class="fl">隐私政策</li>
            <li class="fl">开放平台</li>
            <li class="fl">广告服务</li>
            <li class="fl">海思招聘</li>
            <li class="fl">海思公益</li>
            <li class="fl">客服中心</li>
            <li class="fl">网站导航</li>
        </ul>
        <p>Copyright © 2017 - 2018 Hassdata. All Rights Reserved</p>
        <div class="nav">
            <span>海思公司</span>
            <span>版权所有</span>
        </div>
    </div>
</div>

<script>
    var layer = null;
    layui.use(['form', 'layer'], function () {
        var form = layui.form;
        layer = layui.layer;

        form.on('submit(login)', function (data) {
            var loadIndex = layer.load();
            var fromData = new FormData($("#login")[0]);
            $.ajax({
                type: "POST",
                dataType: "json",
                url: '/system/login',
                data: fromData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (result) {
                    layer.close(loadIndex);
                    if (result.code == 0) {
                        setTimeout(function () {
                            window.location.href = "/system/index";
                        }, 1000);
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
</body>
</html>
