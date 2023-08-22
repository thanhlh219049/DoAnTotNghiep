$(document).ready(function() {

 $(".minusButton").on("click", function(evt) {
        evt.preventDefault();
        decreaseQuantity($(this));
    });

    $(".plusButton").on("click", function(evt) {
        evt.preventDefault();
        increaseQuantity($(this));
    });

    updateTotal();
    });
function decreaseQuantity(link){

        var productId = link.attr("pid");
        var qtyInput = $("#quantity" + productId); // Sử dụng dấu $ để chọn phần tử bằng jQuery
        var newQty = parseInt(qtyInput.val()) - 1;
        if(newQty > 0) {
            qtyInput.val(newQty);
            updateQuantity();
        }
}

function increaseQuantity(link){
        var productId = link.attr("pid");
        var qtyInput = $("#quantity" + productId); // Sử dụng dấu $ để chọn phần tử bằng jQuery
        var newQty = parseInt(qtyInput.val()) + 1;
        if(newQty < 10) {
            qtyInput.val(newQty);
            updateQuantity();
        }
}
function updateQuantity(){

}
function formatCurrency(amount) {
    return amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}

function updateTotal(){
 total = 0.0;

 $(".productSubtotal").each(function(index, element){
    total = total + parseFloat(element.innerHTML);
    });

    $("#totalAmount").text(formatCurrency(total) + "đ");
}
