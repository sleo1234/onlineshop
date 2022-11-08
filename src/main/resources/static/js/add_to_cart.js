$(document).ready(

function() {

	$("#buttonAdd2Cart").on("click", function(evt) {

		evt.preventDefault();
		addToCart()
	});
});

function addToCart() {
	quantity = $("#quantity" + productId).val();
	url = contextPath + "cart/add/" + productId + "/" + quantity;

	$
			.ajax({
				type : "POST",
				url : url,
				beforeSend : function(xhr) {

					xhr.setRequestHeader(csrfHeaderName, csrfValue)

				}
			})
			.done(
					function(response) {

						if (response == "You must login to add a product to the cart. Log in?") {
							showConfirmationModal(response)

						} else {
							showModalDialog("Shopping cart", response);
						}
					})
			.fail(
					function(response) {

						showErrorModal("Error while adding product to shopping cart. ");
					})

}

function showConfirmationModal(message) {

	$("#confirmText").text(message);
	$("#yesButton").click(function(event) {
		$("a").attr("href", "/shop/login")

	})
	$("#confirmModal").modal();
}

function redirectToLoginPage(message) {

	window.location.href = "/shop/login"

}
