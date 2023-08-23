package com.lethanh219049.application.controller.shop;

import com.lethanh219049.application.entity.CartItems;
import com.lethanh219049.application.entity.Order;
import com.lethanh219049.application.entity.User;
import com.lethanh219049.application.service.OrderService;
import com.lethanh219049.application.service.UserService;
import com.lethanh219049.application.service.impl.ShoppingCartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShoppingCartController {
    @Autowired
    private ShoppingCartServices cartServices;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/cart")
    public String showShoppingCart(Model model, Authentication authentication){
        User customer = userService.getCurrentlyLoggedInCustomer(authentication);
        List<CartItems> cartItems = cartServices.ListCartItems(customer);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("pageTitle", "Shopping Cart");
        return "shop/shopping_cart";
    }


    @GetMapping("/cart/payment")
    public String getListCartItem(Model model, Authentication authentication){
        User customer = userService.getCurrentlyLoggedInCustomer(authentication);
        List<CartItems> cartItems = cartServices.ListCartItems(customer);
        List<Order> orders = new ArrayList<>();
        Long totalPrice = 0L;
        Long sizeProduct = 0L;
        for (CartItems cartItem: cartItems) {
            Order order = new Order();
            order.setSize(cartItem.getSize());
            order.setProduct(cartItem.getProduct());
            order.setModifiedBy(cartItem.getUser());
            order.setPrice(cartItem.getProduct().getPrice());
            order.setTotalPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
            totalPrice = totalPrice + cartItem.getProduct().getPrice() * cartItem.getQuantity();
            sizeProduct =sizeProduct+1L;
            order.setReceiverAddress(cartItem.getUser().getAddress());
            order.setReceiverPhone(cartItem.getUser().getPhone());
            order.setReceiverName(cartItem.getUser().getFullName());
            order.setQuantity(cartItem.getQuantity());
            order.setModifiedAt(Timestamp.valueOf(LocalDateTime.now()));
            order.setCreatedBy(cartItem.getUser());
            orders.add(order);
        }
        orderService.saveAll(orders);

        model.addAttribute("orders", orders);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("sizeProduct", sizeProduct);
        model.addAttribute("cartItems", cartItems);
        return "shop/thanh_toan";
    }

}
