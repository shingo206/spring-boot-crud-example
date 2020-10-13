package com.javatechie.springbootcrudexample.controller;

import com.javatechie.springbootcrudexample.entity.Product;
import com.javatechie.springbootcrudexample.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping("/addProduct")
    public Product addProduct(@Valid @RequestBody Product product) {
        return service.insert(product);
    }

    @PostMapping("/addProducts")
    public List<Product> addProducts(@Valid @RequestBody List<Product> products) {
        return service.saveProducts(products);
    }

    @GetMapping("/products")
    public List<Product> findAllProducts() {
        return service.getProducts();
    }

    @GetMapping("/productById/{id}")
    public Product findProductById(@PathVariable int id) {
        return service.getProductById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found"));
    }

    @GetMapping("/product/{name}")
    public List<Product> findProductByName(@PathVariable String name) {
        return service.getProductByName(name);
    }

    @PutMapping("/update")
    public void updateProduct(@RequestBody Product product) {
        service.update(product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable int id) {
        service.delete(id);
    }
}
