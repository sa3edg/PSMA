<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="ushers.title" /></title>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/jquery.lightbox-0.5.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/jquery.1.10.2.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.lightbox-0.5.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="/resources/js/main.js" />"></script>

<link href="<c:url value="/resources//css/footable/footable.core.css?v=2-0-1" />" rel="stylesheet">
<link href="<c:url value="/resources//css/footable/footable.standalone.css" />" rel="stylesheet">
<link href="<c:url value="/resources//css/footable/bootstrap.css" />" rel="stylesheet">
<link href="<c:url value="/resources//css/footable/footable-demos.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/footable/footable.js?v=2-0-1" />"></script>
<script src="<c:url value="/resources/js/footable/footable.paginate.js?v=2-0-1" />"></script>
<script src="<c:url value="/resources/js/footable/demos.js" />"></script>
<script src="<c:url value="/resources/js/footable/footable.filter.js?v=2-0-1" />"></script>

</head>
<body dir='rtl'>
	<div class="page">
		<tiles:insertAttribute name="header" />
		<div class="content">
			<tiles:insertAttribute name="menu" />
			<tiles:insertAttribute name="body" />
		</div>
			<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>