package com.lethanh219049.application.repository;

import com.lethanh219049.application.entity.CartItems;
import com.lethanh219049.application.entity.Product;
import com.lethanh219049.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scala.concurrent.impl.Promise;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems, Long> {

    public List<CartItems> findByUser(User user);

    public CartItems findByUserAndProduct(User customer, Product product);
}
