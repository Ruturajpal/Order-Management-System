package com.ordermanagement.OM.repo;

import com.ordermanagement.OM.entity.Items;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface ItemRepo extends JpaRepository<Items,Integer> {
    Items getByItemGeneratedCodeAndItemActiveStatus(String itemGeneratedCode, boolean b);
    boolean existsByItemGeneratedCodeAndItemActiveStatus(String itemGeneratedCode, boolean b);
    List<Items> getAllByItemActiveStatus(boolean b);
    Page<Items> findAllByItemActiveStatusEquals(boolean b, Pageable of);
    int countAllByItemActiveStatus(boolean b);
}
