package com.kai.ecommercebackendsystem.repository;

import com.kai.ecommercebackendsystem.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findByCategoryId(Integer categoryId, Pageable pageable);
}
