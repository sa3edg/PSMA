<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<tiles:insertDefinition name="loginTemplate">
	<tiles:putAttribute name="body">
		<div class="body-login" onload='document.loginForm.username.focus();'>
			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>
			<form name='loginForm'
				action="<c:url value='/j_spring_security_check' />" method='POST'>
				<table>
				    <tr>
						<td style="font-size: 20px;"><spring:message code="login.title" /></td>
					</tr>
					</br>
					</br>
					<tr>
						<td><spring:message code="login.username" /></td>
						<td><input type='text' name='username'></td>
					</tr>
					<tr>
						<td><spring:message code="login.password" /></td>
						<td><input type='password' name='password' /></td>
					</tr>
					<tr>
						<td colspan='2'><input name="submit" class="btn" type="submit"
							value=<spring:message code="login.submit" /> /></td>
					</tr>
				</table>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>