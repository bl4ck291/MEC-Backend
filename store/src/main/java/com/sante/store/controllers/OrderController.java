package com.sante.store.controllers;

import com.sante.store.dtos.OrderDto;
import com.sante.store.dtos.ProductDto;
import com.sante.store.dtos.ProductInOrderDto;
import com.sante.store.entities.Order;
import com.sante.store.entities.Product;
import com.sante.store.entities.ProductInOrder;
import com.sante.store.services.OrderService;
import com.sante.store.services.ProductInOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final ProductInOrderService productInOrderService;

    @GetMapping("/orders")
    public ResponseEntity<Page<OrderDto>> findAll(Pageable request) {
        Page<OrderDto> gottenPage = orderService.findAll(request).map(this::EntityToDto);
        return new ResponseEntity<>(gottenPage, HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDto> showOne(@PathVariable("id") Long id) {
        Order orderToReturn = orderService.findById(id);
        return new ResponseEntity<>(EntityToDto(orderToReturn), HttpStatus.OK);
    }

    @GetMapping("/orders/search/{email}")
    public ResponseEntity<Page<OrderDto>> findByEmail(@PathVariable("email") String email, Pageable request) {
        Page<OrderDto> gottenPage = orderService.findByUserEmail(email, request).map(this::EntityToDto);
        return new ResponseEntity<>(gottenPage, HttpStatus.OK);
    }

    @PostMapping("/orders/create")
    public ResponseEntity<OrderDto> create() {
        return new ResponseEntity<>(EntityToDto(orderService.create()), HttpStatus.OK);
    }

    @PutMapping("/orders/{id}/addProduct/{productId}")
    public ResponseEntity<OrderDto> addProduct(@PathVariable("id") Long id, @PathVariable("productId") Long productId) {
        ProductInOrder productInOrder = productInOrderService.create(productId);
        Order order = orderService.findById(id);
        order.addProductInOrder(productInOrder);
        return new ResponseEntity<>(EntityToDto(orderService.update(order)), HttpStatus.OK);
    }

    @DeleteMapping("/orders/{id}/deleteProductInOrder/{productInOrderId}")
    public ResponseEntity<OrderDto> deleteProduct(@PathVariable("id") Long id, @PathVariable("productInOrderId") Long productInOrderId) {
        ProductInOrder productInOrder = productInOrderService.findById(productInOrderId);
        Order order = orderService.findById(id);
        order.removeProductInOrder(productInOrder);
        return new ResponseEntity<>(EntityToDto(orderService.update(order)), HttpStatus.OK);
    }

    @DeleteMapping("/orders/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/orders/clear")
    public ResponseEntity<Void> clear() {
        orderService.clear();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/orders/{id}/productInOrder/{productInOrderId}/count/{count}")
    public ResponseEntity<OrderDto> updateProductInOrderCount(@PathVariable("id") Long id, @PathVariable("productInOrderId") Long productInOrderId, @PathVariable("count") Integer count) {
        productInOrderService.updateCount(productInOrderId, count);
        Order order = orderService.update(orderService.findById(id));
        return new ResponseEntity<>(EntityToDto(order), HttpStatus.OK);
    }

    @PutMapping("/orders/{id}/issue")
    public ResponseEntity<OrderDto> issue(@PathVariable("id") Long id) {
        return new ResponseEntity<>(EntityToDto(orderService.issue(id)), HttpStatus.OK);
    }

    @PutMapping("/orders/{id}/setPickupDate/{pickupDate}")
    public ResponseEntity<OrderDto> setPickupDate(@PathVariable("id") Long id, @PathVariable("pickupDate") String pickupDate) {
        LocalDate pickupDateToSet = LocalDate.parse(pickupDate);
        return new ResponseEntity<>(EntityToDto(orderService.setPickupDate(id, pickupDateToSet)), HttpStatus.OK);
    }


    @PutMapping("/orders/{id}/cancel")
    public ResponseEntity<OrderDto> cancel(@PathVariable("id") Long id) {
        return new ResponseEntity<>(EntityToDto(orderService.cancel(id)), HttpStatus.OK);
    }

    @PutMapping("/orders/{id}/complete")
    public ResponseEntity<OrderDto> complete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(EntityToDto(orderService.complete(id)), HttpStatus.OK);
    }

    private Order DtoToEntity(OrderDto orderDto) {
        Order order = new Order();
        if (orderDto.getId() != null) {
            order = orderService.findById(orderDto.getId());
        }
        order.setId(orderDto.getId());
        order.setStatus(orderDto.getStatus());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setPickupDate(orderDto.getPickupDate());
        return order;
    }

    private OrderDto EntityToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        Optional.ofNullable(order.getProductInOrderSet())
                .map(productInOrders -> productInOrders.stream()
                        .map(this::PIOEntityToDto)
                        .collect(Collectors.toSet()))
                .ifPresent(orderDto::setProductInOrderSet);
        orderDto.setStatus(order.getStatus());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setPickupDate(order.getPickupDate());
        return orderDto;
    }

    private ProductInOrder PIODtoToEntity(ProductInOrderDto productInOrderDto) {
        ProductInOrder productInOrder = new ProductInOrder();
        if (productInOrderDto.getId() != null) {
            productInOrder = productInOrderService.findById(productInOrderDto.getId());
        }
        productInOrder.setId(productInOrderDto.getId());
        productInOrder.setCount(productInOrderDto.getCount());
        productInOrder.setTotalPrice(productInOrderDto.getTotalPrice());
        return productInOrder;
    }

    private ProductInOrderDto PIOEntityToDto(ProductInOrder productInOrder) {
        ProductInOrderDto productInOrderDto = new ProductInOrderDto();
        productInOrderDto.setId(productInOrder.getId());
        productInOrderDto.setProductDto(EntityToDto(productInOrder.getProduct()));
        productInOrderDto.setCount(productInOrder.getCount());
        productInOrderDto.setTotalPrice(productInOrder.getTotalPrice());
        productInOrderDto.setOrderId(productInOrder.getOrder().getId());
        return productInOrderDto;
    }

    private ProductDto EntityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setManufacturer(product.getManufacturer());
        productDto.setDescription(product.getDescription());
        productDto.setInstructions(product.getInstructions());
        productDto.setBrand(product.getBrand());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setStock(product.getStock());
        productDto.setCategoryId(product.getCategory().getId());
        return productDto;
    }

    private Set<ProductInOrderDto> PIOEntitySetToDtoSet(Set<ProductInOrder> productInOrderSet) {
        if (productInOrderSet.size() == 0) {
            return null;
        }
        Set<ProductInOrderDto> set = new HashSet<>();
        for (ProductInOrder productInOrder : productInOrderSet) {
            ProductInOrderDto productInOrderDto = PIOEntityToDto(productInOrder);
            set.add(productInOrderDto);
        }

        return set;
    }
}
