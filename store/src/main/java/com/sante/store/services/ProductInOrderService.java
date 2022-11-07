package com.sante.store.services;

import com.sante.store.entities.ProductInOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductInOrderService {
    ProductInOrder findById(Long id);

    Page<ProductInOrder> findAll(Pageable pageable);

    ProductInOrder create(Long id);

    ProductInOrder updateCount(Long id, Integer count);
}
