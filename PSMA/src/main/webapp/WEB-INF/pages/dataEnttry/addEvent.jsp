<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_DATAENTRY')">
				<div style="padding: 30px;">
					<form:form method="post" action="addEvent?${_csrf.parameterName}=${_csrf.token}" modelAttribute="event" enctype="multipart/form-data">
					    <c:set var="requiredFields"><spring:message code="psma.required.fields" /></c:set>
					    <c:set var="validDate"><spring:message code="psma.valid.date" /></c:set>
						<table class="reset_table mynew_style">
						     <tr>
						   	<td><form:hidden path="id" /></td>
						   </tr>
						<tr>
							    <td><form:label class="required" path="title"><spring:message code="psma.event.title"/></form:label></td>
								<td><form:input path="firstName" styleId="required" /></td>
							</tr>
							<tr>
							    <td><form:label class="required" path="details"><spring:message code="psma.event.details"/></form:label></td>
								<td><form:input path="details"  styleId="required" /></td>
							</tr>
							<tr>
							    <td><form:label class="required" path="startDate"><spring:message code="psma.event.startDate"/></form:label></td>
								<td><form:input path="startDate" styleId="date" /></td>
								<td><form:label path="startDate"><spring:message code="psma.dateFormat"/></form:label></td>
							</tr>
                            <tr>
							    <td><form:label path="endDate"><spring:message code="psma.event.endDate"/></form:label></td>
								<td><form:input path="endDate"  styleId="date" /></td>
								<td><form:label path="startDate"><spring:message code="psma.dateFormat"/></form:label></td>
							</tr>
							
                             <tr>
							    <td><form:label path="image"><spring:message code="psma.event.image"/></form:label></td>
								<td><input type="file" name="file"></td>
							</tr>
							
							<tr>
								<td><input type="submit" class="btn" onClick="return validateEvent'${requiredFields}', '${validDate}');"
									value=<spring:message code="psma.add.btn" /> /></td>
							</tr>
						</table>
					</form:form>
				</div>
			</sec:authorize>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>