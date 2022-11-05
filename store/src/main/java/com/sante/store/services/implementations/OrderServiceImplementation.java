package com.sante.store.services.implementations;

import com.sante.store.entities.Order;
import com.sante.store.entities.OrderStatus;
import com.sante.store.repositories.OrderRepository;
import com.sante.store.services.OrderService;
import com.sante.store.services.ProductInOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class OrderServiceImplementation implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Page<Order> findByUserEmail(String email, Pageable pageable) {
        return orderRepository.findByUserEmail(email, pageable);
    }

    @Override
    public Order update(Order order) {
        Order orderToUpdate = findById(order.getId());
        orderToUpdate.setStatus(order.getStatus());
        orderToUpdate.calculateTotalPrice();
        orderToUpdate.setPickupDate(order.getPickupDate());
        return orderRepository.save(orderToUpdate);
    }

    @Override
    public Order create() {
        Order order = new Order();
        order.setStatus(OrderStatus.ORDERING);
        return orderRepository.save(order);
    }

    @Override
    public Order issue(Long id) {
        Order order = findById(id);
        order.setStatus(OrderStatus.ISSUED);
        order.setTotalPrice(BigDecimal.ZERO);
        return orderRepository.save(order);
    }

    @Override
    public Order setPickupDate(Long id, LocalDate pickupDate) {
        Order order = findById(id);
        order.setPickupDate(pickupDate);
        return orderRepository.save(order);
    }

    @Override
    public Order cancel(Long id) {
        Order order = findById(id);
        order.setStatus(OrderStatus.CANCELED);
        return orderRepository.save(order);
    }

    @Override
    public Order complete(Long id) {
        Order order = findById(id);
        order.setStatus(OrderStatus.COMPLETED);
        return orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        Order order = findById(id);
        orderRepository.delete(order);
    }

    @Override
    public Order getReference(Long id) {
        return orderRepository.getReferenceById(id);
    }

}
