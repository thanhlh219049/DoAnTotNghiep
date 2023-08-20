package com.lethanh219049.application.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateOrderRequest {

    @NotBlank(message = "Sản phẩm trống")
    @JsonProperty("product_id")
    private String productId;

    @Min(value = 35)
    @Max(value = 42)
    private int size;

    @NotBlank(message = "Họ tên trống")
    @JsonProperty("receiver_name")
    private String receiverName;

    @Pattern(regexp="(09|03|07|08|05)+([0-9]{8})\\b", message = "Điện thoại không hợp lệ")
    @JsonProperty("receiver_phone")
    private String receiverPhone;

    @NotNull(message = "Địa chỉ trống")
    @NotEmpty(message = "Địa chỉ trống")
    @JsonProperty("receiver_address")
    private String receiverAddress;

    @NotNull(message = "Địa chỉ trống")
    @NotEmpty(message = "Địa chỉ trống")
    @JsonProperty("receiver_city")
    private String receiverCity;

    @NotNull(message = "Địa chỉ trống")
    @NotEmpty(message = "Địa chỉ trống")
    @JsonProperty("receiver_district")
    private String receiverDistrict;

    @NotNull(message = "Địa chỉ trống")
    @NotEmpty(message = "Địa chỉ trống")
    @JsonProperty("receiver_ward")
    private String receiverWard;

    @JsonProperty("coupon_code")
    private String couponCode;

    @JsonProperty("total_price")
    private long totalPrice;

    @JsonProperty("product_price")
    private long productPrice;

    private String note;

}
