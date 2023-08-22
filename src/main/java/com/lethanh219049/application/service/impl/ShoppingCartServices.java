package com.lethanh219049.application.service.impl;

import com.lethanh219049.application.entity.CartItems;
import com.lethanh219049.application.entity.Product;
import com.lethanh219049.application.entity.User;
import com.lethanh219049.application.repository.CartItemRepository;
import com.lethanh219049.application.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServices {
    @Autowired
    private CartItemRepository cartRepo;
    @Autowired
    private ProductRepository productRepo;

    public List<CartItems> ListCartItems(User user) {
        return cartRepo.findByUser(user);
    }

    public Integer addProduct(String productId, Integer quantity, User customer) {
        Integer addedQuantity = quantity;

        Product product = productRepo.findByIdProduct(productId);

        CartItems cartItem = cartRepo.findByUserAndProduct(customer, product);

        if (cartItem != null) {
            addedQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(addedQuantity);
        } else {
            cartItem = new CartItems();
            cartItem.setQuantity(quantity);
            cartItem.setUser(customer);
            cartItem.setProduct(product);
        }
        cartRepo.save(cartItem);

        return addedQuantity;
    }
}
