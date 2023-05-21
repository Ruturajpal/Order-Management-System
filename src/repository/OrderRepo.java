package com.ordermanagement.OM.repo;

import com.ordermanagement.OM.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrderRepo extends JpaRepository<Order,Integer> {
    Order getByOrderGeneratedCode(String orderCode);
}
