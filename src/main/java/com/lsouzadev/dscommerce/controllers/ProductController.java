package com.lsouzadev.dscommerce.controllers;

import com.lsouzadev.dscommerce.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String test() {
        return "meu Deus, meu Deus. Eu sou um hacker!!";
    }
}
