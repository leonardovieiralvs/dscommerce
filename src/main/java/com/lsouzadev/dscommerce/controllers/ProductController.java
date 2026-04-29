package com.lsouzadev.dscommerce.controllers;


import com.lsouzadev.dscommerce.dto.ProductDto;
import com.lsouzadev.dscommerce.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping
//    public ResponseEntity<List<ProductDto>> findAll() {
//        return ResponseEntity.ok(productService.findAll());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok((productService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> findByPage(Pageable pageable) {

        Page<ProductDto> byPage = productService.findByPage(pageable);
        return ResponseEntity.ok(byPage);
    }
}
