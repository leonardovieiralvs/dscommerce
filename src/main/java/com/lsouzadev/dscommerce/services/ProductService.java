package com.lsouzadev.dscommerce.services;

import com.lsouzadev.dscommerce.dto.ProductDto;
import com.lsouzadev.dscommerce.entities.Product;
import com.lsouzadev.dscommerce.mapper.ProductMapper;
import com.lsouzadev.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDto> findAll() {

        List<Product> result = productRepository.findAll();

        return result.stream().map(productMapper::toDto).toList();
    }

    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found."));
        return productMapper.toDto(product);
    }

    public Page<ProductDto> findByPage(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(productMapper::toDto);
    }

    public ProductDto insert(ProductDto productDto) {
        Product save = productMapper.toEntity(productDto);
        Product productSave = productRepository.save(save);
        return productMapper.toDto(productSave);
    }

    public ProductDto update(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found!"));
        productMapper.updateEntity(product, productDto);

        Product save = productRepository.save(product);
        return productMapper.toDto(save);
    }
}
