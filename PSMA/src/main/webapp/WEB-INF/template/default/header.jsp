<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page session="true"%>
<div class="header">
	<img src="<c:url value='/resources/images/ben_02.jpg'/>" /> <img
		src="<c:url value='/resources/images/ben_05.jpg'/>" />

	<!-- <div style="float: right; padding-right:20;">
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				<c:out value="${pageContext.request.userPrincipal.name}  " />
				<spring:message code="header.welcome" />
			</h2>
		</c:if>
	</div> -->
	<div style="float: left; padding-left:20;">
	    <c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				<c:out value="${pageContext.request.userPrincipal.name}  " />
				<spring:message code="header.welcome" />
			</h2>
		</c:if>
		
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				<a href="javascript:formSubmit()"><spring:message
						code="header.logiut" /></a>
			</h2>
		</c:if>
	</div>

</div>