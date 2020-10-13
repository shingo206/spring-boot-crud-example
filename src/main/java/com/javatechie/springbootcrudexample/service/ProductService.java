package com.javatechie.springbootcrudexample.service;

import com.javatechie.springbootcrudexample.entity.Product;
import com.javatechie.springbootcrudexample.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProductById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<Product> getProductByName(String name) {
        return repository.findByName(name);
    }

    public Product insert(Product product) {
        return repository.save(product);
    }

    public List<Product> saveProducts(List<Product> products) {
        return repository.saveAll(products);
    }

    public void update(Product product) {
        repository.save(product);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}