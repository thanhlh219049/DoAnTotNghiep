$(document).ready(function() {

 $(".minusButton").on("click", function(evt) {
        evt.preventDefault();
        decreaseQuantity($(this));
    });

    $(".plusButton").on("click", function(evt) {
        evt.preventDefault();
        increaseQuantity($(this));
    });

    $(".link-remove").on("click", function(evt){
        evt.preventDefault();
        removeFromCart($(this));
    });

    updateTotal();
    });
function removeFromCart(link){
    url = link.attr("href");
     $.ajax({
                type: "POST",
                url: url,
                dataType: "text",
                success: function(response) {
                alert(response);
                 showModal("Thành công.", "Sản phẩm đã được xóa khỏi giỏ hàng."); // Hiển thị thông báo lỗi
                },
                error: function() {
                    showModal("Lỗi.", "Phát sinh lỗi khi xóa khỏi giỏ hàng, thử lại sau 10p."); // Hiển thị thông báo lỗi
                }
            });
}
function removeProduct(rowNumber){
    rowId = $("#row" + rowNumber);
    $("#" + rowId).remove();
}
function decreaseQuantity(link){

        var productId = link.attr("pid");
        var size = link.attr("size");
        var qtyInput = $("#quantity" + productId + 1)
        var newQty = parseInt(qtyInput.val()) - 1;
        if(newQty > 0) {
            qtyInput.val(newQty);
            updateQuantity(productId, newQty, size);
        }
}

function increaseQuantity(link){
        var productId = link.attr("pid");
        var size = link.attr("size");
        var qtyInput = $("#quantity" + productId + 1)
        var newQty = parseInt(qtyInput.val()) + 1;
        if(newQty < 10) {
            qtyInput.val(newQty);
            updateQuantity(productId, newQty, size);
        }
}
function updateQuantity(productId, quantity, size){
    var url = "/cart/update/" + productId + "/" + quantity  + "/" + size;

     $.ajax({
            type: "POST",
            url: url,
            dataType: "text",
            success: function(newSubtotal) {
               updateSubtotal(newSubtotal, productId);
               updateTotal();
            },
            error: function() {
                showModal("Lỗi.", "Bản cần phải đăng nhập để thêm sản phẩm vào giỏ hàng."); // Hiển thị thông báo lỗi
            }
        });
}
function formatCurrency(amount) {
    return amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}
function updateSubtotal(newSubtotal, productId){
    $("#subtotal" + productId).text(newSubtotal);
}
function updateTotal(){
 total = 0.0;

 $(".productSubtotal").each(function(index, element){
    total = total + parseFloat(element.innerHTML);
    });

    $("#totalAmount").text(formatCurrency(total) + "đ");
}

 function showModal(title, body) {
        $("#modalTitle").text(title);
        $("#modalBody").text(body);
        $("#myModal").modal("show");
    }
