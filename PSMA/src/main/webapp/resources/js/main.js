function confirmDelete(msg){
	var agree=confirm(msg);
	if (agree)
	     return true ;
	else
	     return false ;
}

function validateEvent(requiredFields, validDates)
{
	if(validateRequiredFields(requiredFields) && validateRequiredDate(requiredFields, validDates))
	{
		return true;
	}
	return false;
}

//validate offer
function validateOffer(requiredFields, validDates)
{
	if(validateRequiredFields(requiredFields) && validateRequiredDate(requiredFields, validDates))
	{
		return true;
	}
	return false;
}

//validate subscriber
function validateSubscriber(firstNameMsg, nationalIdNumberMsg, mobileMsg, emailMsg, cardNumberMsg)
{
	if(validateName(firstNameMsg) && validateMobile(mobileMsg) && validateNationalId(nationalIdNumberMsg) &&  
			validateEmail(emailMsg) && validateCardNumber(cardNumberMsg))
	{
		return true;
	}
	return false;
}

function validateRequiredFields(msg){
	  var elems = document.getElementsByTagName( 'required' );
	  var allgood = true;

	  //Loop through all elements with this name
	  for( var i = 0; i < elems.length; i++ ) {
	    if( !elems[i].value || !elems[i].value.length ) {
	      elems[i].focus();
	      allgood = false;
	      break;
	    }
	  }

	  //If any element did not meet the requirements, prevent it from being submitted and display an alert
	  if( !allgood ) {
		alert(msg);
	    return false;
	  }

	  //Otherwise submit the form
	  return true;
}

function validateRequiredDate(requiredFieldMsg, validDateMsg)
{
	var elems = document.getElementsByTagName('requiredDate');
	var requiredFields = true;
	var validDates = true;
	for( var i = 0; i < elems.length; i++ ) {
		if(isBlank(elems[i].value)){
			elems[i].focus();
			requiredFields = false;
			break;
		}
	}
	
	if( !requiredFields ) {
		alert(requiredFieldMsg);
	    return false;
	}
	var dateReg = /^(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[/](19|20)\d\d$/ ;
	
	for( var i = 0; i < elems.length; i++ ) {
		if(!elems[i].value.match(dateReg)){
			elems[i].focus();
			validDates= false;
			break;
		}
	}
	if( !validDates ) {
		alert(validDateMsg);
	    return false;
	}
	
	return true;
}

function validateUsher(languageMsg, firstNameMsg, middleNameMsg, lastNameMsg, universityMsg, ageMsg, kidsNumberMsg
		, nationalIdNumberMsg, birthDateMsg, insuranceDateMsg, exitDateMsg, phoneMsg, mobileMsg, emailMsg, yearMsg)
{
	if(validateFirstName(firstNameMsg) && validateMiddleName(middleNameMsg) &&
			validateLastName(lastNameMsg) && validateMobile(mobileMsg) && validateLanguages(languageMsg) && validateUnvirsity(universityMsg) 
			&& validateNationalId(nationalIdNumberMsg) && validateBirthDate(birthDateMsg) 
			&& validateInsuranceDate(insuranceDateMsg) && validateExitDate(exitDateMsg) && validatePhone(phoneMsg) &&  
			validateEmail(emailMsg) && validateGraduationYear(yearMsg))
	{
		return true;
	}
	return false;
}
//add object method
function validateLanguages(msg)
{
	var languages = document.getElementById('languages');
	if(languages.selectedIndex == -1)
	{
		alert(msg);
	    return false;
	}
	return true;
}

//validate cardNumber
function validateCardNumber(msg)
{
	var name = document.getElementById('cardNumber').value;
	if(name.length == 0 ){
		alert(msg);
	    return false;
	}
	return true;
}

//validate name
function validateName(msg)
{
	var name = document.getElementById('name').value;
	if(name.length == 0 ){
		alert(msg);
	    return false;
	}
	return true;
}

//validate first name
function validateFirstName(msg)
{
	var name = document.getElementById('firstName').value;
	if(name.length == 0 ){
		alert(msg);
	    return false;
	}
	return true;
}
//validate name
function validateMiddleName(msg)
{
	var name = document.getElementById('middleName').value;
	if(name.length == 0 ){
		alert(msg);
	    return false;
	}
	return true;
}
//validate name
function validateLastName(msg)
{
	var name = document.getElementById('lastName').value;
	if(name.length == 0 ){
		alert(msg);
	    return false;
	}
	return true;
}

//validate name
function validateUnvirsity(msg)
{
	var name = document.getElementById('university').value;
	if(name.length == 0 ){
		alert(msg);
	    return false;
	}
	return true;
}
//validate name
function validateAge(msg)
{
	var age = document.getElementById('age').value;
	if (!isNaN(age)){
		true;
	}else{
		alert(msg);
	    return false;
	}
	return true;
}
//validate name
function validateKidsNumber(msg)
{
	var kidsNum = document.getElementById('kidsNumber').value;
	if (isNaN(kidsNum)){
		alert(msg);
	    return false;
	}
	return true;
}
//validate name
function validateNationalId(msg)
{
	var name = document.getElementById('nationalIdNumber').value;
	if(isBlank(name)){
		alert(msg);
	    return false;
	}
	return true;
}
//validate date
function validateBirthDate(msg)
{
	var startDate = document.getElementById('birthDate').value;
	if(isBlank(startDate))
	{
		return true;
	}
	var dateReg = /^(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[/](19|20)\d\d$/ ;
	
	if(!startDate.match(dateReg)){
		alert(msg);
	    return false;
	}
	return true;
}

//validate date
function validateInsuranceDate(msg)
{
	var insuranceDate = document.getElementById('socialInsuranceDate').value;
	if(isBlank(insuranceDate))
	{
		return true;
	}
	var dateReg = /^(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[/](19|20)\d\d$/ ;
	
	if(!insuranceDate.match(dateReg)){
		alert(msg);
	    return false;
	}
	return true;
}

//validate date
function validateExitDate(msg)
{
	var startDate = document.getElementById('socialInsuranceExitDate').value;
	if(isBlank(startDate))
	{
		return true;
	}
	var dateReg = /^(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[/](19|20)\d\d$/ ;
	if(startDate.length != 10){
		alert(msg);
	    return false;
	}
	if(!startDate.match(dateReg)){
		alert(msg);
	    return false;
	}
	return true;
}

// validate phone number
function validatePhone(msg)
{
   var phone = document.getElementById('landlineNumber').value;
   var phoneno = /^\d{10}$/;
   if(isBlank(phone))
   {
	   return true;
   }
   if(phone.match(phoneno))
   {  
      return true;  
   }  
   else  
   {  
	  alert(msg);
      return false;  
   }  
   return true;
}

// validate phone number
function validateMobile(msg)
{
   var phone = document.getElementById('mobileNumber').value;
   var phoneno = /^\d{11}$/;
   if(isBlank(phone)){
	   alert(msg);
	   return false;
   }
   if(phone.match(phoneno))
   {  
      return true;  
   }  
   else  
   {  
	  alert(msg);
      return false;  
   } 
   return true;
}

// validate email address
function validateEmail(msg) { 
	
	var x = document.getElementById('emailAddress').value;
	if(isBlank(x))
	{
		   return true;
	}
	var atpos = x.indexOf("@");
	var dotpos=x.lastIndexOf(".");
	if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
	{
	  alert(msg);
	  return false;
	}
	return true;
} 
//validate name
function validateGraduationYear(msg)
{
	var year = document.getElementById('graduationYear').value;
	if (isNaN(year) &&  year.lenght > 4){
		alert(msg);
	    return false;
	}
	return true;
}
//check if empty string
function isBlank(str) {
    return (!str || /^\s*$/.test(str));
}
