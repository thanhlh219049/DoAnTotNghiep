package com.lethanh219049.application.model.request;

import com.lethanh219049.application.entity.Product;
import lombok.*;

import javax.validation.constraints.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class CreateOrderRes {

    private List<Long> orderIds;
    @NotBlank(message = "Họ tên trống")
    private String receiverName;

    @Pattern(regexp="(09|03|07|08|05)+([0-9]{8})\\b", message = "Điện thoại không hợp lệ")
    private String receiverPhone;

    @NotNull(message = "Địa chỉ trống")
    @NotEmpty(message = "Địa chỉ trống")
    private String receiverAddress;



    private String note;

}
