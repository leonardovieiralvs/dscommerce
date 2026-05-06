package com.lsouzadev.dscommerce.controllers;


import com.lsouzadev.dscommerce.dto.ProductDto;
import com.lsouzadev.dscommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok((productService.findById(id)));
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> findByPage(Pageable pageable) {

        Page<ProductDto> byPage = productService.findByPage(pageable);
        return ResponseEntity.ok(byPage);
    }

    @PostMapping
    public ResponseEntity<ProductDto> insert(@Valid @RequestBody ProductDto productDto) {
        ProductDto insert = productService.insert(productDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(insert);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {
        ProductDto update = productService.update(id, productDto);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
