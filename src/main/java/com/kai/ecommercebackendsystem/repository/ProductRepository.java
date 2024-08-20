package com.kai.ecommercebackendsystem.repository;

import com.kai.ecommercebackendsystem.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findByCategoryId(Integer categoryId, Pageable pageable);

    @Modifying
    @Query("UPDATE Product p " +
            "SET p.productName = :#{#product.getProductName()} , p.description = :#{#product.getDescription()} , p.price = :#{#product.getPrice()} , " +
            "p.stockQuantity = :#{#product.getStockQuantity()} , p.categoryId = :#{#product.getCategoryId()} , p.productImg = :#{#product.getProductImg()} , " +
            "p.remark = :#{#product.getRemark()} , p.updatedTime = CURRENT_TIMESTAMP " +
            "WHERE p.id = :id")
    void update(Integer id, Product product);
}
