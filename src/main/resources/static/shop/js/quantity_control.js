$(document).ready(function() {
    $(".minusButton").on("click", function(evt) {
        evt.preventDefault();
        var productId = $(this).attr("pid");
        var qtyInput = $("#quantity" + productId); // Sử dụng dấu $ để chọn phần tử bằng jQuery
        var newQty = parseInt(qtyInput.val()) - 1;
        if(newQty > 0) {
            qtyInput.val(newQty);
        }
    });

    $(".plusButton").on("click", function(evt) {
        evt.preventDefault();
        var productId = $(this).attr("pid");
        var qtyInput = $("#quantity" + productId); // Sử dụng dấu $ để chọn phần tử bằng jQuery
        var newQty = parseInt(qtyInput.val()) + 1;
        if(newQty < 10) {
            qtyInput.val(newQty);
        }
    });
});
