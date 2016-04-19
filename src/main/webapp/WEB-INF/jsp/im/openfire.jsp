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
	<div class="row">
		<div class="jumbotron">
			<ul>
				<li>openfire聊天</li>
			</ul>
		</div>
	</div>
</body>
</html>
