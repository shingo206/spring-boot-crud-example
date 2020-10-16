package com.javatechie.springbootcrudexample.controller;

import com.javatechie.springbootcrudexample.entity.Product;
import com.javatechie.springbootcrudexample.exception.ResourceNotFoundException;
import com.javatechie.springbootcrudexample.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Validated
public class ProductController {
    private final ProductService service;

    @GetMapping("/products")
    public List<Product> findAllProducts() {
        return service.getProducts();
    }

    @GetMapping("/productById/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable("id") @Min(1) int id) {
        return ResponseEntity.ok().body(service.getProductById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID: " + id + " Not Found!")));
    }

    @GetMapping("/product/{name}")
    public List<Product> findProductByName(@PathVariable String name) {
        return service.getProductByName(name);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        return ResponseEntity.ok().body(service.insert(product));
    }

    @PostMapping("/addProducts")
    public ResponseEntity<List<Product>> addProducts(@Valid @RequestBody List<Product> products) {
        return ResponseEntity.ok().body(service.saveProducts(products));
    }


    @PutMapping("/update")
    public void updateProduct(@RequestBody Product product) {
        service.getProductById(product.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID: " + product.getId() + " Not Found!"));
        service.update(product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable @Min(1) int id) {
        service.getProductById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID: " + id + " Not Found!"));
        service.delete(id);
    }
}
