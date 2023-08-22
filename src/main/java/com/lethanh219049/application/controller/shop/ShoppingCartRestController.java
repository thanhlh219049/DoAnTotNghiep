package com.lethanh219049.application.controller.shop;

import com.lethanh219049.application.entity.User;
import com.lethanh219049.application.service.UserService;
import com.lethanh219049.application.service.impl.ShoppingCartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartRestController {
    @Autowired
    private ShoppingCartServices cartServices;
    @Autowired
    private UserService customerService;

    @PostMapping("/cart/add/{pid}/{qty}")
    public String addProductToCart(@PathVariable("pid") String productId,
                                   @PathVariable("qty") Integer quantity,
                                   Authentication authentication){
        System.out.println("============" + productId+ "-" + quantity);
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken){
            throw new RuntimeException("Bản cần phải đăng nhập để thêm sản phẩm vào giỏ hàng");
        }

        User customer = customerService.getCurrentlyLoggedInCustomer(authentication);
        if (customer == null){
            throw new RuntimeException("Bản cần phải đăng nhập để thêm sản phẩm vào giỏ hàng");
        }

        Integer addedQuantity = cartServices.addProduct(productId,quantity,customer);
        return "";
    }
}
