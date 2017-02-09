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
					<form:form method="post" action="addOffer?${_csrf.parameterName}=${_csrf.token}" modelAttribute="offer" enctype="multipart/form-data">
					    <c:set var="requiredFields"><spring:message code="psma.required.fields" /></c:set>
					    <c:set var="validDate"><spring:message code="psma.valid.date" /></c:set>
						<table class="reset_table mynew_style">
						     <tr>
						   	<td><form:hidden path="id" /></td>
						   </tr>
						<tr>
							    <td><form:label class="required" path="title"><spring:message code="psma.event.title"/></form:label></td>
								<td><form:input path="title" name="required" /></td>
							</tr>
							<tr>
							    <td><form:label class="required" path="details"><spring:message code="psma.event.details"/></form:label></td>
								<td><form:input path="details"  name="required" /></td>
							</tr>
							<tr>
							    <td><form:label class="required" path="item"><spring:message code="psma.event.details"/></form:label></td>
								<td><form:input path="item"  name="required" /></td>
							</tr>
							<tr>
							    <td><form:label path="oldPrice"><spring:message code="psma.offer.oldPrice"/></form:label></td>
								<td><form:input path="oldPrice"  style="width: 150px;" type="number" min="0.0" max="9999999" step="0.5" /></td>
							</tr>
							<tr>
							    <td><form:label path="newPrice"><spring:message code="psma.offer.newPrice"/></form:label></td>
								<td><form:input path="newPrice"  style="width: 150px;" type="number" min="0.0" max="9999999" step="0.5" /></td>
							</tr>
							<tr>
							    <td><form:label path="sale"><spring:message code="psma.offer.sale"/></form:label></td>
								<td><form:select path="sale">
									<form:option value="true" label="Yes"/>
                            	    <form:option value="false" label="No"/>
                            	</form:select></td>
                            </tr>
							<tr>
							    <td><form:label class="required" path="startDate"><spring:message code="psma.event.startDate"/></form:label></td>
								<td><form:input path="startDate" name="requiredDate" /></td>
								<td><form:label path="startDate"><spring:message code="psma.dateFormat"/></form:label></td>
							</tr>
                            <tr>
							    <td><form:label path="endDate"><spring:message code="psma.event.endDate"/></form:label></td>
								<td><form:input path="endDate"  name="requiredDate" /></td>
								<td><form:label path="startDate"><spring:message code="psma.dateFormat"/></form:label></td>
							</tr>
							
                             <tr>
							    <td><form:label path="image"><spring:message code="psma.event.image"/></form:label></td>
								<td><input type="file" name="file"></td>
							</tr>
							
							<tr>
								<td><input type="submit" class="btn" onClick="return validateOffer'${requiredFields}', '${validDate}');"
									value=<spring:message code="psma.add.btn" /> /></td>
							</tr>
						</table>
					</form:form>
				</div>
			</sec:authorize>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>