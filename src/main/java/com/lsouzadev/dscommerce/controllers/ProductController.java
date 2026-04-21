package com.lsouzadev.dscommerce.controllers;

import com.lsouzadev.dscommerce.entities.Product;
import com.lsouzadev.dscommerce.services.ProductService;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(Pageable pageable,
                                                 @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(name = "size", defaultValue = "10") Integer size) {

        return ResponseEntity.ok(productService.listAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity ProductDto(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }
}
