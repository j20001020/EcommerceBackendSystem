package com.kai.ecommercebackendsystem.repository;


import com.kai.ecommercebackendsystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Modifying
    @Query("UPDATE Category c SET c.categoryName = :#{#category.getCategoryName()} , c.updatedTime = CURRENT_TIMESTAMP WHERE c.id = :id")
    void update(Integer id, Category category);
}
