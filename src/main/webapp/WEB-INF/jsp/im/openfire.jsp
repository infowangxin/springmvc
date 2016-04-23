<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>-IM聊天</title>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="application" />
<link href="<c:url value='/static/jquery/themes/base/jquery-ui.min.css?${version_css}'/>" rel="stylesheet" type="text/css" />
</head>

<body>
	<form:form class="form-horizontal">
		<div class="form-group">
			<div class="col-sm-2">
				<button class="btn btn-default" type="button" onclick="window.location.href='${ctx}/im/simpleclient'">simpleclient.html</button>
			</div>
			<div class="col-sm-2">
				<button class="btn btn-default" type="button" onclick="window.location.href='${ctx}/im/simpleCrossDomainClient'">simpleCrossDomainClient.html</button>
			</div>
		</div>
	</form:form>
</body>
</html>
