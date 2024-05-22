package com.accolite.ecommercebackend.Repository;

import com.accolite.ecommercebackend.Entity.Cart;
import com.accolite.ecommercebackend.Entity.Product;
import com.accolite.ecommercebackend.Entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
    Cart findByUserAndProduct(User user, Product product);

    List<Cart> findByUser(User user);


    @Query("SELECT SUM(c.quantity) FROM Cart c WHERE c.user = :user")
    Integer sumQuantityByUser(User user);


    @Query("DELETE FROM Cart c WHERE c.cartId = :cartId")
    @Transactional
    @Modifying
    void deleteItemById(UUID cartId);
}
