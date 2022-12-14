package com.sante.store.repositories;

import com.sante.store.entities.Order;
import com.sante.store.entities.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
        @Query("select o from Order as o where lower(o.user.email) like concat('%', lower(:email), '%')")
        Page<Order> findByUserEmail(@Param("email") String email, Pageable pageable);

        @Query("select o from Order as o where o.user.id=:userId and o.status=:orderStatus")
        Page<Order> findOrderByStatus(@Param("userId") Long userId, @Param("orderStatus") OrderStatus orderStatus, Pageable pageable);

        @Query("select o from Order as o where o.user.id=:userId and o.status=:orderStatus")
        Order findOrderByStatus(@Param("userId") Long userId, @Param("orderStatus") OrderStatus orderStatus);

}

