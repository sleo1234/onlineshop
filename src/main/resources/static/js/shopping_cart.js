
$(document).ready(
		
		
		
		function() {
			
			
			$(".linkMinus").on("click", function(evt) {
			
				 let productId=$(this).attr("pid");
		       evt.preventDefault($(this));
		       decreaseQuantity($(this));
		       function deleteProd(productId){
		   	}
			});
			
		$(".linkPlus").on("click", function(evt) {
			
		      evt.preventDefault($(this));
		      
		      increaseQuantity($(this));
		
			});
		})

function decreaseQuantity (link){
		productId=link.attr("pid");
	    quantityInput=$("#quantity" +productId);
	    newQuantity=parseInt(quantityInput.val())-1;
	    
	  if(newQuantity > 0){
		
	 	 quantityInput.val(newQuantity);
	 	  updateQuantity(productId, newQuantity);
	  }
	
	  else {
	 	 showWarningModal("Minimum quantity is 1")
	  }
	}

	function increaseQuantity(link){
		productId=link.attr("pid");
	    quantityInput=$("#quantity" +productId);
	    newQuantity=parseInt(quantityInput.val())+1;
	    
	  if(newQuantity <= 5){
	 	 quantityInput.val(newQuantity);
	 	updateQuantity(productId, newQuantity );
	  }
	
	  else {
	 	 showWarningModal("Maximum quantity is 5")
	  }
	  
 }
	function updateSubtotal(updatedSubtotal, productId){
		
		$("#subtotal" + productId).text(updatedSubtotal);
	}
	
	   function updateTotal (){
		   total=0.0;
		   
		   $(".subtotal").each(function (index, element){
			   total += parseFloat(element.innerHTML);
			   
		   });
		   $("#total").text(total);
	   }
		
	function updateQuantity (productId, quantity){
		url=contextPath+"cart/update/" +productId + "/"+quantity;
		
	
	$.ajax( {
		type: "POST",
		url: url,
		beforeSend: function(xhr){
			xhr.setRequestHeader(csrfHeaderName, csrfValue)
		}
	}).done(function (updatedSubtotal) {
		updateSubtotal(updatedSubtotal, productId)
		updateTotal ();
	
	}).fail( function (response){
		showErrorModal("Error while updating quantity.");
	})

	}

function deleteProduct(productId){
		
		
		url=contextPath+"cart/delete/"+productId;
		$.ajax( {
			type: 'DELETE',
			url: url,
			beforeSend: function(xhr){
				xhr.setRequestHeader(csrfHeaderName, csrfValue)
			}
		}).done(function () {
		
			showModalDialog ("Delete confirmation", "Are you sure you want to delete this product?.")
		
		$("#div"+productId).remove()
	
		}).fail( function (response){
			showErrorModal("Error while deleting.");
		})
	}
	


	$("#deleteProduct").click(function (){
		 productId=$(this).attr("pid");
		deleteProduct(productId)
	})
	
	function emptyCart(){
		
		url=contextPath+"cart/empty_cart";
		$.ajax( {
			type: 'DELETE',
			url: url,
			beforeSend: function(xhr){
				xhr.setRequestHeader(csrfHeaderName, csrfValue)
			}
		}).done(function () {
		console.log("here")
		
		
		}).fail( function (response){
			showErrorModal("Error while deleting.");
			
		})
			}
	
	$("#emptyCart").click(function (){
		showDeleteConfirmModal("Are you sure you want to empty your cart?.")
		
	})
	
	function showDeleteConfirmModal(message) {
	
	$("#confirmText").text(message);
	$("#yesButton").click(function (){
		//emptyCart()
		alert(isCartEmpty())
	})
	$("#confirmModal").modal();	
}
	
	function isCartEmpty() {
		url = "cart/items";
	   $.get(url,function (response){
			console.log(response)
		});
		
		if (response == "not_empty") {
			return false;
		}
		return true
	}
	
	
	
	
	