<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
	   
		<div class="body">
		<c:set var="deleteConfirmation"><spring:message code="psma.delecteConfirmation" /></c:set>
		         <script type="text/javascript">
        $(function () {
			$('table').footable();

					$('#change-page-size').change(function (e) {
						e.preventDefault();
						var pageSize = $(this).val();
						$('.footable').data('page-size', pageSize);
						$('.footable').trigger('footable_initialized');
					});

					$('#change-nav-size').change(function (e) {
						e.preventDefault();
						var navSize = $(this).val();
						$('.footable').data('limit-navigation', navSize);
						$('.footable').trigger('footable_initialized');
					});
        });
        
        $(function () {
            $('table').footable().bind('footable_filtering', function (e) {
              var selected = $('.filter-status').find(':selected').text();
              if (selected && selected.length > 0) {
                e.filter += (e.filter && e.filter.length > 0) ? ' ' + selected : selected;
                e.clear = !e.filter;
              }
            });

            $('.clear-filter').click(function (e) {
              e.preventDefault();
              $('.filter-status').val('');
              $('table.demo').trigger('footable_clear_filter');
            });

            $('.filter-status').change(function (e) {
              e.preventDefault();
              $('table.demo').trigger('footable_filter', {filter: $('#filter').val()});
            });

            $('.filter-api').click(function (e) {
              e.preventDefault();

              //get the footable filter object
              var footableFilter = $('table').data('footable-filter');

              alert('about to filter table by "tech"');
              //filter by 'tech'
              footableFilter.filter('tech');

              //clear the filter
              if (confirm('clear filter now?')) {
                footableFilter.clearFilter();
              }
            });
          });
        </script>
        <c:if test="${not empty offers}">
				<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_DATAENTRY')">
        <table>
	    	<tr>
            	<form:label><spring:message code="psma.search"/></form:label> <input id="filter" type="text"/></br>
            </tr>
            <tr>
                <td>
                <form:label><spring:message code="psma.pageSize"/></form:label>
				<select id="change-page-size">
					<option value="2">2</option>
					<option value="3">3</option>
                    <option value="5">5</option>
                    <option value="10">10</option>
                </select>
                </td>
            	<td>
            	<form:label><spring:message code="psma.navSize"/></form:label>
                <select id="change-nav-size">
					<option value="0">None</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
				</select>
				
				</td>
				
                </tr>
				</table>
				<table  class="table demo" data-page-size="5" data-filter="#filter" data-filter-text-only="true">
					<thead>
						<tr>
							<th data-toggle="true">
								<form:label><spring:message code="psma.event.title"/></form:label>
							</th>
							<th>
								<form:label><spring:message code="psma.event.startDate"/></form:label>
							</th>
							<th >
								<form:label><spring:message code="psma.event.endDate"/></form:label>
							</th>
							<th >
								<form:label><spring:message code="psma.edit"/></form:label>
							</th>
							<th >
								<form:label><spring:message code="psma.delete"/></form:label>
							</th>
						</tr>
					</thead>
					<tbody>
						 <c:forEach var="item" items="${offers}">
            				<tr>
                				<td>${item.title}</td>
                				<td>${item.startDate}</td>
                				<td>${item.endDate}</td>
				        		<td><a href="<c:url value="/editOffer?id=${item.id}" />">
				        			<img src="<c:url value='/resources/images/edit.png'/>" width="30" height="30"  border="0" alt="Edit"> </a></td>
				        		<sec:authorize access="hasRole('ROLE_ADMIN')">
				        		<td><a href="<c:url value="/deleteOffer?id=${item.id}" />"
				        			onClick="return confirmDelete('${deleteConfirmation}');"><img src="<c:url value='/resources/images/delete.png'/>" width="30" height="30"  border="0" alt="Delete"> </a></td>
				        		</sec:authorize>
            				</tr>
        				</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="pagination pagination-centered"></div>
							</td>
						</tr>
					</tfoot>
				</table>
				</sec:authorize>
		        </c:if>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_DATAENTRY')">
        	    <a href="<c:url value="addOfferForm" />"><spring:message
						code="psma.addOffer" /></a></br>
                </sec:authorize>
			</div>
			
		   
	</tiles:putAttribute>
</tiles:insertDefinition>