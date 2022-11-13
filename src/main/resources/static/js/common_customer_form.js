var dropDownCountry ;
	var dataListStates;
	
	$(document).ready( function (){
		contextPath = "[[@{/}]]";
		
		function checkPasswordMatch (confirmPassword){
			
			if (confirmPassword.value != $("#password").val()){
				confirmPassword.setCustomValidity("Password do no match");
		} else {
			confirmPassword.setCustomValidity("");

		}
		}
		
		 
			
		
	});