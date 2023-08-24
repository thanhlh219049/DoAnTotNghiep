package com.lethanh219049.application.repository;

import com.lethanh219049.application.entity.CartItems;
import com.lethanh219049.application.entity.Product;
import com.lethanh219049.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import scala.concurrent.impl.Promise;

import javax.transaction.Transactional;
import java.util.List;

@Repository


public interface CartItemRepository extends JpaRepository<CartItems, Long> {

    public List<CartItems> findByUser(User user);

    public CartItems findByUserAndProductAndSize(User customer, Product product, int size);
    @Transactional
    @Modifying
    @Query(value = "UPDATE cart_items c SET c.quantity = ?1 WHERE c.product_id = ?2 AND c.user_id = ?3 AND c.size = ?4 AND c.id = ?5", nativeQuery = true)
    void updateQuantity(Integer quantity, String productId, Long customerId, int size, Long id);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM cart_items WHERE id = ?1", nativeQuery = true)
    public void deleteByCustomerAndProduct(Long cartItemId);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM cart_items WHERE user_id = ?1", nativeQuery = true)
    public List<CartItems> getListCartItems(Long customerId);
}
