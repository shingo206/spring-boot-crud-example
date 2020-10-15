package com.javatechie.springbootcrudexample.service;

import com.javatechie.springbootcrudexample.entity.Product;
import com.javatechie.springbootcrudexample.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    @Transactional(readOnly = true)
    public List<Product> getProducts() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Product> getProductById(int id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Product> getProductByName(String name) {
        return repository.findByNameContainingIgnoreCaseOrderByName(name);
    }

    @Transactional
    public Product insert(Product product) {
        return repository.save(product);
    }

    @Transactional
    public List<Product> saveProducts(List<Product> products) {
        return repository.saveAll(products);
    }

    @Transactional
    public void update(Product product) {
        repository.save(product);
    }

    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }
}
