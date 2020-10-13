package com.javatechie.springbootcrudexample.repository;

import com.javatechie.springbootcrudexample.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByNameContainingIgnoreCaseOrderByName(@Param("name") String name);
}
