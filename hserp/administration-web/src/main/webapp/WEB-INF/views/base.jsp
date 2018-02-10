<%
	String baseUrl = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<base href="<%=baseUrl%>">
<%--引入js--%>
<script src="/static/js/jquery-3.2.1.min.js"></script>
<script src="/static/js/system.js"></script>
<script src="/static/layui/layui.js"></script>