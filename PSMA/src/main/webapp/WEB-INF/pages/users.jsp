<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
		        <sec:authorize access="hasRole('ROLE_ADMIN')">
				<a href="<c:url value="/addUserForm" />"><spring:message
						code="users.addUser" /></a>
				<c:if test="${not empty users}">
				<c:set var="deleteConfirmation"><spring:message code="psma.delecteConfirmation" /></c:set>
					<div class="table-title">
						<h3>Users</h3>
					</div>
					<display:table id="usersTable" pagesize="10" requestURI="/getUsers" name="users" class="CSSTableGenerator" >
						<display:column property="userName" titleKey="users.username" ></display:column>
						<display:column property="role" titleKey="users.role" ></display:column>
						<display:column><a href="<c:url value="/deleteUser?id=${usersTable.userName}" />"
				        onClick="return confirmDelete('${deleteConfirmation}');"><img src="<c:url value='/resources/images/delete.png'/>" width="30" height="30"  border="0" alt="Link to this page"> </a></display:column>
					</display:table>
				</c:if>
				</sec:authorize>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>