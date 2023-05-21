package com.ordermanagement.OM.repo;

import com.ordermanagement.OM.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    List<Customer> getAllByCustomerActiveStatus(boolean b);
    Customer getByCustomerGeneratedIdAndCustomerActiveStatus(String generatedCustomerId, boolean b);
    boolean existsByCustomerGeneratedIdAndCustomerActiveStatus(String generatedCustomerId, boolean b);
    Customer getByCustomerContactNumberAndCustomerActiveStatus(String customerRegPhoneNo, boolean b);
    boolean existsByCustomerContactNumberAndCustomerActiveStatus(String customerRegPhoneNo, boolean b);
}
