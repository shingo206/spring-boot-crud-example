package com.javatechie.springbootcrudexample.repository;

import com.javatechie.springbootcrudexample.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.name like concat(:name, '%') ORDER BY p.id")
    List<Product> findByName(@Param("name") String name);
}
