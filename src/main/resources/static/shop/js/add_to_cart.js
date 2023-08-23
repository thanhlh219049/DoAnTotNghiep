$(document).ready(function() {
    $("#buttonAdd2Cart").on("click", function() {
        addToCart();
    });
});

function addToCart() {
  var size = $(".size-details").text();
     var regex = /([\d.]+)VN/;

     var match = size.match(regex); // Sử dụng match để tìm kiếm phần khớp với regex
     var sizeUsOnly = "";

     if (match) {
         sizeUsOnly = match[1]; // Lấy phần khớp với regex
         console.log(sizeUsOnly); // In ra kích thước US duy nhất
     } else {
         console.log("Không tìm thấy kích thước US");
     }

     var newQty = parseInt($("#quantity" + productId + 1).val());

     if (isNaN(newQty) || newQty <= 0) {
         console.log("Số lượng không hợp lệ");
         return;
     }
    var url = "/cart/add/" + productId + "/" + newQty + "/" + sizeUsOnly;
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