<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
				<div style="padding: 30px;">
					<form:form method="post" action="addUser" modelAttribute="user">
						<table>
							<tr>
								<td><spring:message code="register.username" /></td>
								<td><form:input path="userName" /></td>
								<td><form:errors path="userName" cssClass="validationError" /></td>
							</tr>
							<tr>
								<td><spring:message code="register.password" /></td>
								<td><form:password path="password" /></td>
								<td><form:errors path="password" cssClass="validationError" /></td>
							</tr>
							<tr>
								<td><spring:message code="register.role" /></td>	 
								<td>
								<form:radiobutton path="role" value="ROLE_SUPER_USER" /><spring:message code="register.dataEntryRole" /> 
								<form:radiobutton path="role" value="ROLE_DATAENTRY" /><spring:message code="register.ushersCoordinatorRole" />
								<form:radiobutton path="role" value="ROLE_MERCHANT" /><spring:message code="register.adminRole" /></td>
							</tr>
							<tr>
								<td><input type="submit" class="btn"
									value=<spring:message code="register.addUser" /> /></td>
							</tr>
						</table>
					</form:form>
				</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>