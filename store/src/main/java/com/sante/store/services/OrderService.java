package com.sante.store.services;

import com.sante.store.entities.Order;
import com.sante.store.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface OrderService {

    Order findById(Long id);

    Page<Order> findAll(Pageable pageable);

    Page<Order> findByUserEmail(String email, Pageable pageable);

    Page<Order> productsInCart(Long userId, Pageable pageable);

    Page<Order> orderIssued(Long userId, Pageable pageable);

    Page<Order> orderCompleted(Long userId, Pageable pageable);

    Order update(Order order);

    Order create(User user);

    Order issue(Long id);

    Order setPickupDate(Long id, LocalDate pickupDate);

    Order cancel(Long id);

    Order complete(Long id);

    void delete(Long id);

    void clear();

    Order getReference(Long id);
}
