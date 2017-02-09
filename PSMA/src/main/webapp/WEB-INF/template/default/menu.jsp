<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div id='cssmenu'>
<ul dir='rtl'>
   <sec:authorize access="hasRole('ROLE_ADMIN')">
   		<li><a href="<c:url value="/getUsers"/>"><span><spring:message code="menu.adminArea" /></span></a></li>
   </sec:authorize>
   <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_USER', 'ROLE_USHER')">
   		<li><a href="<c:url value="/ushers" />"><span><spring:message code="menu.ushers" /></span></a></li>
   		<li><a href="<c:url value="/supervisorUshers"/>"><span><spring:message code="menu.ushers.supervisor" /></span></a></li>
   		<li><a href="<c:url value="/showReportForm" />"><span><spring:message code="menu.reports" /></span></a></li>
   </sec:authorize>
   <li class='active has-sub'><a href='#'><span><spring:message code="menu.timesheet" /></span></a>
   		<ul>
   		    <li class='last'><a href="<c:url value="/usherTimeSheets"/>"><span><spring:message code="menu.ushers.timesheetEntry" /></span></a></li>
        	<li class='last'><a href="<c:url value="/showUsherTimeSheetCriteria"/>"><span><spring:message code="menu.ushers.timesheetReport" /></span></a></li>
        	<li class='last'><a href="<c:url value="/projects"/>"><span><spring:message code="menu.superUshers.timesheetEntry" /></span></a></li>
        	<li class='last'><a href="<c:url value="/projects"/>"><span><spring:message code="menu.superUshers.timesheetReport" /></span></a></li>
        </ul>
   </li>
   <li class='active has-sub'><a href='#'><span><spring:message code="menu.projectDate" /></span></a>
   		<ul>
        	<li class='last'><a href="<c:url value="/projects"/>"><span><spring:message code="menu.projects" /></span></a></li>
        	<li class='last'><a href="<c:url value="/clients"/>"><span><spring:message code="menu.clients" /></span></a></li>
        	<li class='last'><a href="<c:url value="/products"/>"><span><spring:message code="menu.products" /></span></a></li>
        	<li class='last'><a href="<c:url value="/projectTypes"/>"><span><spring:message code="menu.projectTypes" /></span></a></li>
        	<li class='last'><a href="<c:url value="projectLocations"/>"><span><spring:message code="menu.projectLocations" /></span></a></li>
        </ul>
   </li>
   <li class='active has-sub'><a href='#'><span><spring:message code="menu.ushersData" /></span></a>
   		<ul>
        	<li class='last'><a href="<c:url value="/governorates"/>"><span><spring:message code="menu.governorate" /></span></a></li>
        	<li class='last'><a href="<c:url value="/areas"/>"><span><spring:message code="menu.area" /></span></a></li>
        	<li class='last'><a href="<c:url value="/preferredLocations"/>"><span><spring:message code="menu.preferredLocations" /></span></a></li>
        </ul>
   </li>
</ul>
</div>