package com.sante.store.services.implementations;

import com.sante.store.entities.Product;
import com.sante.store.entities.ProductInOrder;
import com.sante.store.repositories.ProductInOrderRepository;
import com.sante.store.services.ProductInOrderService;
import com.sante.store.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductInOrderServiceImplementation implements ProductInOrderService {

    private final ProductInOrderRepository productInOrderRepository;

    private final ProductService productService;

    @Override
    public ProductInOrder findById(Long id) {
        return productInOrderRepository.findById(id).orElse(null);
    }

    @Override
    public Page<ProductInOrder> findAll(Pageable pageable) {
        return productInOrderRepository.findAll(pageable);
    }

    @Override
    public ProductInOrder create(Long id) {
        Product product = productService.findById(id);
        ProductInOrder productInOrder = new ProductInOrder();
        productInOrder.setProduct(product);
        productInOrder.setCount(1);
        productInOrder.calculateTotalPrice();
        return productInOrder;
    }

    @Override
    public ProductInOrder updateCount(Long id, Integer count) {
        ProductInOrder productInOrderToUpdate = findById(id);
        productInOrderToUpdate.setCount(count);
        productInOrderToUpdate.calculateTotalPrice();
        return productInOrderRepository.save(productInOrderToUpdate);
    }

}

