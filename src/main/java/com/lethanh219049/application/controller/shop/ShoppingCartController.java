package com.lethanh219049.application.controller.shop;

import com.lethanh219049.application.entity.CartItems;
import com.lethanh219049.application.entity.User;
import com.lethanh219049.application.service.UserService;
import com.lethanh219049.application.service.impl.ShoppingCartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShoppingCartController {
    @Autowired
    private ShoppingCartServices cartServices;

    @Autowired
    private UserService userService;
    @GetMapping("/cart")
    public String showShoppingCart(Model model, Authentication authentication){
        User customer = userService.getCurrentlyLoggedInCustomer(authentication);
        List<CartItems> cartItems = cartServices.ListCartItems(customer);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("pageTitle", "Shopping Cart");
        return "shop/shopping-cart";
    }

}
