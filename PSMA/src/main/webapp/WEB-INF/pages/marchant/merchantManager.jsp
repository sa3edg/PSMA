<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<tiles:insertDefinition name="merchantTemplate">
	<tiles:putAttribute name="body">
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
	    <div id="dialog">
        	<div id="myDialogText"></div>
        </div>
	    <script type="text/javascript">
	    
	    //addCard form
	    $(function() { 
	        $('#addCard').submit(function() {
	            var form = $( this ),
	                url = form.attr('action'),
	                name = form.find('input[name="name"]').val(),
	                gender = form.find('select[name="gender"]').val(),
	                age = form.find('input[name="age"]').val(),
	                address = form.find('input[name="address"]').val(),
	                mobileNumber = form.find('input[name="mobileNumber"]').val(),
	                facebookAccount = form.find('input[name="facebookAccount"]').val(),
	                emailAddress = form.find('input[name="emailAddress"]').val(),
	                nationalIdNo = form.find('input[name="nationalIdNo"]').val(),
	                cardNumber = form.find('input[name="cardNumber"]').val(),
	                dat = JSON.stringify({ "name" : name, "gender" : gender, "age" : age, "address" : address , 
	                	"mobileNumber" : mobileNumber , "facebookAccount" : facebookAccount , "emailAddress" : emailAddress , "nationalIdNo" : nationalIdNo , "cardNumber" : cardNumber });

	            $.ajax({
	                url : url,
	                type : "POST",
	                traditional : true,
	                contentType : "application/json",
	                data : dat,
	                error : function (body) {
	                	alert(body);
	                },
	                success : function (body) {
	                	//alert(body);
	                	var text = $('<i/>').html('&#1605;&#1604;&#1581;&#1608;&#1592;&#1607;').text();
	                	$("#myDialogText").text(body);
	                	$( "#dialog" ).dialog({
	                	      modal: true,
	                	      title: text,
	                	      closeOnEscape: false,
	                	      open: function(event, ui) { $(".ui-dialog-titlebar-close", ui.dialog | ui).hide(); },
	                	      buttons: {
	                	        Ok: function() {
	                	          $( this ).dialog( "close" );
	                	        }
	                	      }
	                	    });
	              
	                },
	               
	            });

	            return false;
	        });
	    });
	    
	    function resetAddCardForm() {
	    	$('#addCard')[0].reset();
	    	return false;
	    }
	    //addPoints form
	    $(function() { 
	        $('#addPointsForm').submit(function() {
	            var form = $( this ),
	                url = form.attr('action'),
	                mobileNumber = form.find('input[name="mobileNumber"]').val(),
	                cardNumber = form.find('input[name="cardNumber"]').val(),
	                resetNumber = form.find('input[name="resetNumber"]').val(),
	                resetCount = form.find('input[name="resetCount"]').val(),
	                dat = JSON.stringify({ "mobileNumber" : mobileNumber, "cardNumber" : cardNumber, "resetNumber" : resetNumber, "resetCount" : resetCount });

	            $.ajax({
	                url : url,
	                type : "POST",
	                traditional : true,
	                contentType : "application/json",
	                data : dat,
	                error : function (body) {
	                	alert(body);
	                },
	                success : function (body) {
	                	//alert(body);
	                	var text = $('<i/>').html('&#1605;&#1604;&#1581;&#1608;&#1592;&#1607;').text();
	                	$("#myDialogText").text(body);
	                	$( "#dialog" ).dialog({
	                	      modal: true,
	                	      title: text,
	                	      closeOnEscape: false,
	                	      open: function(event, ui) { $(".ui-dialog-titlebar-close", ui.dialog | ui).hide(); },
	                	      buttons: {
	                	        Ok: function() {
	                	          $( this ).dialog( "close" );
	                	        }
	                	      }
	                	    });
	              
	                },
	               
	            });

	            return false;
	        });
	    });
	    
	    function resetAddPointsForm() {
	    	$('#addPointsForm')[0].reset();
	    	return false;
	    }
	    
	    //redeemPoints form
	    function callShowPoints() {
	    	var url = 'redeemPoints?${_csrf.parameterName}=${_csrf.token}';
	    	var mobileNumber = form.find('input[name="mobileNumber"]').val();
            var pointsCount = $('input[name="pointsCount"]').val();
            var cardNumber = $('input[name="cardNumber"]').val();
            var showPoint = 'true';
            var dat = JSON.stringify({ "mobileNumber" : mobileNumber, "pointsCount" : pointsCount, "cardNumber" : cardNumber, "showPoint" : showPoint });

            $.ajax({
                url : url,
                type : "POST",
                traditional : true,
                contentType : "application/json",
                data : dat,
                error : function (body) {
                	alert(body);
                },
                success : function (body) {
                	var text = $('<i/>').html('&#1605;&#1604;&#1581;&#1608;&#1592;&#1607;').text();
                	$("#myDialogText").text(body);
                	$( "#dialog" ).dialog({
                	      modal: true,
                	      title: text,
                	      closeOnEscape: false,
                	      open: function(event, ui) { $(".ui-dialog-titlebar-close", ui.dialog | ui).hide(); },
                	      buttons: {
                	        Ok: function() {
                	          $( this ).dialog( "close" );
                	        }
                	      }
                	    });
              
                },
               
            });

            return false;
	    }
	   
	    function callRedeemPoints() {
	    	var url = 'redeemPoints?${_csrf.parameterName}=${_csrf.token}';
	    	var mobileNumber = form.find('input[name="mobileNumber"]').val();
            var pointsCount = $('input[name="pointsCount"]').val();
            var cardNumber = $('input[name="cardNumber"]').val();
            var showPoint = 'false';
            var reddemAllPoints = $('#redeemPointForm :checkbox').is(":checked") ? 'yes' : 'no';
            var dat = JSON.stringify({ "mobileNumber" : mobileNumber, "pointsCount" : pointsCount, "cardNumber" : cardNumber, "showPoint" : showPoint, "reddemAllPoints" : reddemAllPoints });

            $.ajax({
                url : url,
                type : "POST",
                traditional : true,
                contentType : "application/json",
                data : dat,
                error : function (body) {
                	alert(body);
                },
                success : function (body) {
                	var text = $('<i/>').html('&#1605;&#1604;&#1581;&#1608;&#1592;&#1607;').text();
                	$("#myDialogText").text(body);
                	$( "#dialog" ).dialog({
                	      modal: true,
                	      title: text,
                	      closeOnEscape: false,
                	      open: function(event, ui) { $(".ui-dialog-titlebar-close", ui.dialog | ui).hide(); },
                	      buttons: {
                	        Ok: function() {
                	          $( this ).dialog( "close" );
                	        }
                	      }
                	    });
              
                },
               
            });

            return false;
	    }
	    
	    function redeemAllPointsChange() {
	    	if ($('#redeemPointForm :checkbox').is(":checked")){
	    		$("input[name='pointsCount']").prop('disabled', true);
	    	}else{
	    		$("input[name='pointsCount']").prop('disabled', false);
	    	}
	    }
	    
	    function resetRedeemForm() {
	    	$('#redeemPointForm')[0].reset();
	    	return false;
	    }
	    </script>
	    
		<div class="body-login" onload='document.loginForm.username.focus();'>
		    
		    <div class="right-tabs clearfix">
		    <ul class="nav nav-tabs">
		        <li >
		        <c:if test="${pageContext.request.userPrincipal.name != null}">
					<a href="javascript:formSubmit()">
					<img border="0" alt="close" src="<c:url value='/resources/images/close-icon.png'/>"  width="16" height="16"></a>
				</c:if></li>
		        <li><a href="#showPoints" data-toggle="tab"><spring:message code="psma.merchant.showPointsTab" /></a></li>
    			<li><a href="#addPoints" data-toggle="tab"><spring:message code="psma.merchant.addPointsTab" /></a></li>
    			<li class="active"><a href="#addCardTab" data-toggle="tab"><spring:message code="psma.merchant.addCardTab" /></a></li>
  			</ul>
  			
  			<div class="tab-content">
  			    
  			    <!--  add card tab-->
  				<div class="tab-pane active" id="addCardTab">
				<form id="addCard" method="post" action="addSubscriber?${_csrf.parameterName}=${_csrf.token}" >
					
					<table>
					    
						<tr style="height: 10px;"></tr>
						<tr>
							<td><form:label class="required" ><spring:message code="psma.subscriber.name"/></form:label></td>
							<td><input type='text' styleId="name" name=name /></td>
						</tr>
						<tr>
							<td><form:label ><spring:message code="psma.subscriber.gender"/></form:label></td>
							<td><form:select name='gender' items="${gender}" /></td>
						</tr>
						<tr>
							<td><form:label  ><spring:message code="psma.subscriber.age"/></form:label></td>
							<td><input type='number' styleId="age" name='age' /></td>
						</tr>
						<tr>
							<td><form:label ><spring:message code="psma.subscriber.address"/></form:label></td>
							<td><input type='text' styleId="address" name='address' /></td>
						</tr>
						<tr>
							<td><form:label class="required" ><spring:message code="psma.subscriber.mobileNumber"/></form:label></td>
							<td><input type='tel' styleId="mobileNumber" name='mobileNumber' /></td>
						</tr>
						<tr>
							<td><form:label ><spring:message code="psma.subscriber.facebookAccount"/></form:label></td>
							<td><input type='url' styleId="facebookAccount" name='facebookAccount' /></td>
						</tr>
						<tr>
							<td><form:label class="required" ><spring:message code="psma.subscriber.emailAddress"/></form:label></td>
							<td><input type='email' styleId="emailAddress" name='emailAddress' /></td>
						</tr>
						<tr>
							<td><form:label class="required" ><spring:message code="psma.subscriber.nationalIdNo"/></form:label></td>
							<td><input type='number' styleId="nationalIdNumber" name='nationalIdNumber' /></td>
						</tr>
						<tr>
							<td><form:label class="required" ><spring:message code="psma.subscriber.cardNumber"/></form:label></td>
							<td><input type='number' styleId="cardNumber" name='cardNumber' /></td>
						</tr>
						
						<tr >
						<td></td>
							<td >
							<input style="float: left;" type="reset" class="btn" onClick="resetAddCardForm();"
								value=<spring:message code="psma.merchant.clearForm" /> />
							<input style="float: left;margin-left:30px;" name="submit"  class="btn" type="submit"
								value=<spring:message code="psma.merchant.addCard" /> />
							</td>
						    
						</tr>
						
					</table>
				</form>
			</div>
			<!--  add points tab-->
			<div class="tab-pane" id="addPoints">
			    <form id="addPointsForm" method="post" action="addPoints?${_csrf.parameterName}=${_csrf.token}" >
					
					<table>
					    
						<tr style="height: 10px;"></tr>
						<tr>
							<td><form:label class="required" ><spring:message code="psma.subscriber.mobileNumber" /></form:label></td>
							<td><input type='tel' name='mobileNumber' /></td>
						</tr>
						<tr>
							<td><form:label class="required" ><spring:message code="psma.subscriber.cardNumber" /></form:label></td>
							<td><input  type='number' name='cardNumber' /></td>
						</tr>
						<tr>
							<td><form:label class="required" ><spring:message code="psma.subscriber.resetNumber" /></form:label></td>
							<td><input type='number' name='resetNumber' /></td>
						</tr>
						<tr>
							<td><form:label class="required" ><spring:message code="psma.subscriber.resetCount" /></form:label></td>
							<td><input type='number' name='resetCount' /></td>
						</tr>
						
						<tr >
						<td></td>
							<td >
							<input style="float: left;" type="reset" class="btn" onClick="resetAddPointsForm();"
								value=<spring:message code="psma.merchant.clearForm" /> />
							<input style="float: left;margin-left:30px;" name="submit"  class="btn" type="submit"
								value=<spring:message code="psma.merchant.addPoint" /> />
							</td>
						    
						</tr>
						
					</table>
				</form>
			</div>
			
			 <!--  redeem point tab-->
			<div class="tab-pane" id="showPoints">
			    <form id="redeemPointForm" method="post" action="" >
					
					<table>
						<tr style="height: 10px;"></tr>
						<tr>
							<td><form:label class="required" ><spring:message code="psma.subscriber.mobileNumber" /></form:label></td>
							<td><input type='tel' name='mobileNumber' /></td>
						</tr>
						<tr>
							<td><form:label class="required" ><spring:message code="psma.subscriber.cardNumber" /></form:label></td>
							<td><input type='number' name='cardNumber' /></td>
						</tr>
						<tr>
							<td><form:label class="required" ><spring:message code="psma.subscriber.pointsCount" /></form:label></td>
							<td><input  type='number' name='pointsCount' /></td>
						</tr>
						<tr>
						    <td><label for="reddemAllPoints"><spring:message code="psma.subscriber.redeemAllPoints"/></label></td>
							<td><input type="checkbox" name="reddemAllPoints" id="reddemAllPoints" value="value" onChange="redeemAllPointsChange();"></td>
						</tr>
						<tr >
						<td></td>
							<td >
							<input style="float: left;" type="reset" class="btn" onClick="resetRedeemForm();"
								value=<spring:message code="psma.merchant.clearForm" /> />
							<input style="float: left;margin-left:30px;" name="showPointBtn"  class="btn" type="reset" onClick="callShowPoints();"
								value=<spring:message code="psma.merchant.showPoint" /> />
							<input style="float: left;margin-left:30px;" name="redeemPointBtn"  class="btn" type="reset" onClick="callRedeemPoints();"
								value=<spring:message code="psma.merchant.redeemPoint" /> />
							</td>
						    
						</tr>
						
					</table>
				</form>
			</div>
			
			</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>