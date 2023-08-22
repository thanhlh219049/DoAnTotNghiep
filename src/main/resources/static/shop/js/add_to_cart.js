$(document).ready(function() {
    $("#buttonAdd2Cart").on("click", function() {
        addToCart();
    });
});

function addToCart() {
    var quantity = $("#quantity" + productId).val();
    var url = "/cart/add/" + productId + "/" + quantity;

     $.ajax({
            type: "POST",
            url: url,
            dataType: "text",
            success: function(response) {
                showModal("Thêm thành công.", "Sản phẩm đã được thêm vào giỏ hàng."); // Hiển thị thông báo thành công
            },
            error: function() {
                showModal("Lỗi.", "Bản cần phải đăng nhập để thêm sản phẩm vào giỏ hàng."); // Hiển thị thông báo lỗi
            }
        });
    }

    function showModal(title, body) {
        $("#modalTitle").text(title);
        $("#modalBody").text(body);
        $("#myModal").modal("show");
    }