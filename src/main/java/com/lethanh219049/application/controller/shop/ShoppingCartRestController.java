package com.lethanh219049.application.controller.shop;

import com.lethanh219049.application.entity.CartItems;
import com.lethanh219049.application.entity.User;
import com.lethanh219049.application.service.UserService;
import com.lethanh219049.application.service.impl.ShoppingCartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShoppingCartRestController {
    @Autowired
    private ShoppingCartServices cartServices;
    @Autowired
    private UserService customerService;

    @PostMapping("/cart/add/{pid}/{qty}/{size}")
    public String addProductToCart(@PathVariable("pid") String productId,
                                   @PathVariable("qty") Integer quantity,
                                   @PathVariable("size") int size,
                                   Authentication authentication){
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken){
            throw new RuntimeException("Bản cần phải đăng nhập để thêm sản phẩm vào giỏ hàng");
        }

        User customer = customerService.getCurrentlyLoggedInCustomer(authentication);
        if (customer == null){
            throw new RuntimeException("Bản cần phải đăng nhập để thêm sản phẩm vào giỏ hàng");
        }

        Integer addedQuantity = cartServices.addProduct(productId,quantity, customer, size);
        return "";
    }

    @PostMapping("/cart/update/{pid}/{qty}/{size}")
    public String updateQuantity(@PathVariable("pid") String productId,
                                 @PathVariable("qty") Integer quantity,
                                 @PathVariable("size") int size,
                                   Authentication authentication){
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken){
            throw new RuntimeException("Bản cần phải đăng nhập để thêm sản phẩm vào giỏ hàng");
        }

        User customer = customerService.getCurrentlyLoggedInCustomer(authentication);
        if (customer == null){
            throw new RuntimeException("Bản cần phải đăng nhập để thêm sản phẩm vào giỏ hàng");
        }

        float subtotal = cartServices.updateQuantity(quantity,productId,customer, size);
        return String.valueOf(subtotal);
    }

    @PostMapping("/cart/remove/{id}")
    public String removeProduct(@PathVariable("id") Long cartItemId,

                                 Authentication authentication){
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken){
            throw new RuntimeException("Bạn cần phải đăng nhập để xóa sản phẩm");
        }

        User customer = customerService.getCurrentlyLoggedInCustomer(authentication);
        if (customer == null){
            throw new RuntimeException("Bạn cần phải đăng nhập để xóa sản phẩm");
        }

        cartServices.removeProduct(cartItemId);
        return "Success";
    }

}
