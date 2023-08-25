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

    public void deleteAll(List<CartItems> cartItems){
        cartRepo.deleteAll(cartItems);
    }

    public Integer addProduct(String productId, Integer quantity, User customer, int size) {
        Integer addedQuantity = quantity;

        Product product = productRepo.findByIdProduct(productId);

        CartItems cartItem = cartRepo.findByUserAndProductAndSize(customer, product, size);

        if (cartItem != null) {
            addedQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(addedQuantity);
        } else {
            cartItem = new CartItems();
            cartItem.setQuantity(quantity);
            cartItem.setUser(customer);
            cartItem.setProduct(product);
            cartItem.setSize(size);
        }
        cartRepo.save(cartItem);

        return addedQuantity;
    }

    public float updateQuantity(Integer quantity, String productId, User customerId, int size, Long id){
        cartRepo.updateQuantity(quantity, productId, customerId.getId(), size, id);
        Product product = productRepo.findByIdProduct(productId);
        float subtotal = product.getPrice() * quantity;
        return subtotal;
    }

    public void removeProduct(Long cartItemId){
        cartRepo.deleteByCustomerAndProduct(cartItemId);
    }

    public List<CartItems> getListCartItem(Long customerId){
        List<CartItems> cartItems =
        cartRepo.getListCartItems(customerId);
        return cartItems;
    }

}
