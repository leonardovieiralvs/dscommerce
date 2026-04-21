package com.lsouzadev.dscommerce.services;

import com.lsouzadev.dscommerce.dto.ProductDto;
import com.lsouzadev.dscommerce.entities.Product;
import com.lsouzadev.dscommerce.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto findById(Long id) {
        Optional<Product> result = productRepository.findById(id);
        Product product = result.get();
        ProductDto productDto = new ProductDto(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImgUrl());

        return productDto;
    }

    public Page<Product> listAll(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        return productRepository.findAll(pageRequest);
    }
}
