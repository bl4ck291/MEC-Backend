package com.sante.store.services;

import com.sante.store.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface OrderService {

    Order findById(Long id);

    Page<Order> findAll(Pageable pageable);

    Page<Order> findByUserEmail(String email, Pageable pageable);

    Order update(Order order);

    Order create();

    Order issue(Long id);

    Order setPickupDate(Long id, LocalDate pickupDate);

    Order cancel(Long id);

    Order complete(Long id);

    void delete(Long id);

    void clear();

    Order getReference(Long id);
}
