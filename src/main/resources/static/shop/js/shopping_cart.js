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
    $('#btn-buy-now').click(function() {
        let receiverName = $('#receiver_name').val();
        let receiverPhone = $('#receiver_phone').val();
        let receiverAddress = $('#receiver_address').val();
        let note = $('#note').val();

        let isValid = true;

        if (receiverName.length == 0) {
            fullNameInvalid = $('#receiver_name').next();
            fullNameInvalid.css('display', 'block');
            fullNameInvalid.html('Vui lòng nhập họ tên người nhận hàng');
            isValid = false;
        }
        if (receiverPhone.length == 0) {
            phoneInvalid = $('#receiver_phone').next();
            phoneInvalid.css('display', 'block');
            phoneInvalid.html('Vui lòng nhập số điện thoại người nhận hàng');
            isValid = false;
        }
        let phoneFormat = new RegExp(/((09|03|07|08|05)+([0-9]{8})\b)/g);
        if (!phoneFormat.test(receiverPhone)) {
            phoneInvalid = $('#in-user-phone').next();
            phoneInvalid.css('display', 'block');
            phoneInvalid.html('Số điện thoại không hợp lệ');
            isValid = false;
        }
        if (receiverAddress.length == 0) {
            addressInvalid = $('#receiver_address').next();
            addressInvalid.css('display', 'block');
            addressInvalid.html('Vui chọn địa chỉ nhận hàng ở bên dưới');
            isValid = false;
        }

       if (isValid) {
          // Khai báo một mảng để lưu danh sách các item.id
          let orderIds = [];

          // Duyệt qua các phần tử có class "product-row-10"
          $(".product-row-10").each(function() {
              let orderId = $(this).data('order-id'); // Lấy giá trị từ thuộc tính dữ liệu
              orderIds.push(orderId); // Thêm vào mảng
          });

            let productReq = {
                orderIds : orderIds,
                receiverName: receiverName,
                receiverPhone: receiverPhone,
                receiverAddress: receiverAddress,
                note: note

            };

            $.ajax({
                url: '/cart/payment/order',
                type: 'POST',
                data: JSON.stringify(productReq),
                contentType: "application/json; charset=utf-8",
                success: function(data) {
                    toastr.success("Đặt hàng thành công");
                    setTimeout(function() {
                        location.href = "/tai-khoan/lich-su-giao-dich/" + data;
                    }, 1000);
                },
                error: function(xhr, textStatus, errorThrown) {
                    toastr.warning(xhr.responseJSON.message);
                }
            });
        }
    });

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
        var id = link.attr("id");
        var qtyInput = $("#quantity" + productId + size + id)
        var newQty = parseInt(qtyInput.val()) - 1;
        if(newQty > 0) {
            qtyInput.val(newQty);
            updateQuantity(productId, newQty, size , id);
        }
}

function increaseQuantity(link){
        var productId = link.attr("pid");
        var size = link.attr("size");
        var id = link.attr("id");
        var qtyInput = $("#quantity" + productId + size + id)
        var newQty = parseInt(qtyInput.val()) + 1;
        if(newQty < 10) {
            qtyInput.val(newQty);
            updateQuantity(productId, newQty, size , id);
        }
}
function updateQuantity(productId, quantity, size, id){
    var url = "/cart/update/" + productId + "/" + quantity  + "/" + size+ "/" + id;

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
