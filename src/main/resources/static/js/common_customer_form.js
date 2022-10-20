var dropDownCountry ;
	var dataListStates;
	
	$(document).ready( function (){
		contextPath = "[[@{/}]]";
		dropDownCountry = $("#country");
		dataListStates = $("#listStates");
		dropDownCountry.on("change" , function (){
			loadStatesForCountry();
			$("#state").val("").focus()
			fieldState = $("#state");
			
		})
		function checkPasswordMatch (confirmPassword){
			
			if (confirmPassword.value != $("#password").val()){
				confirmPassword.setCustomValidity("Password do no match");
		} else {
			confirmPassword.setCustomValidity("");

		}
		}
		 function loadStatesForCountry (){
			
			selectedCountry = $("#country option:selected");
			countryId = selectedCountry.val();
			url = contextPath + "states/listById/" + countryId;
			
			$.get(url, function(responseJson){
				
				dataListStates.empty();
				$.each(responseJson, function (index, state){
					$("<option>").val(state.name).text(state.name).appendTo(dataListStates)
				})
				
			})
			
		}
		 
			
		
	});