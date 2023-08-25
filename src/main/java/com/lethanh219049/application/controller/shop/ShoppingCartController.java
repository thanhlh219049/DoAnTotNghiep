package com.lethanh219049.application.controller.shop;

import com.lethanh219049.application.entity.*;
import com.lethanh219049.application.exception.BadRequestException;
import com.lethanh219049.application.exception.NotFoundException;
import com.lethanh219049.application.model.request.CreateOrderRequest;
import com.lethanh219049.application.model.request.CreateOrderRes;
import com.lethanh219049.application.security.CustomUserDetails;
import com.lethanh219049.application.service.OrderService;
import com.lethanh219049.application.service.ProductService;
import com.lethanh219049.application.service.UserService;
import com.lethanh219049.application.service.impl.ShoppingCartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.lethanh219049.application.config.Constant.ORDER_STATUS;

@Controller
public class ShoppingCartController {
    @Autowired
    private ShoppingCartServices cartServices;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService customerService;


    //Thanh toan don hang
    @PostMapping("/cart/payment/order")
    public ResponseEntity<Object> createOrder(@Valid @RequestBody CreateOrderRes lstCreateOrderRes,
                                              Authentication authentication) {

        User user = customerService.getCurrentlyLoggedInCustomer(authentication);

        List<Order> orders = orderService.findOrderByUserId(lstCreateOrderRes.getOrderIds());

        orders.forEach(order -> {
            User userDto = new User();
            user.setId(user.getId());
            order.setCreatedBy(user);
            order.setBuyer(user);
            order.setReceiverName(lstCreateOrderRes.getReceiverName());
            order.setReceiverPhone(lstCreateOrderRes.getReceiverPhone());
            order.setReceiverAddress(lstCreateOrderRes.getReceiverAddress());
            order.setNote(lstCreateOrderRes.getNote());
            order.setStatus(ORDER_STATUS);
        });
        orderService.saveAll(orders);

        List<CartItems> cartItems = cartServices.ListCartItems(user);
        cartServices.deleteAll(cartItems);

        return ResponseEntity.ok("");
    }

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
        User user = customerService.getCurrentlyLoggedInCustomer(authentication);

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
