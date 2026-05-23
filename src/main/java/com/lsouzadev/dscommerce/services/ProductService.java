package com.lsouzadev.dscommerce.services;

import com.lsouzadev.dscommerce.dto.ProductDto;
import com.lsouzadev.dscommerce.entities.Product;
import com.lsouzadev.dscommerce.exceptions.DatabaseViolationException;
import com.lsouzadev.dscommerce.exceptions.ResourceNotFoundException;
import com.lsouzadev.dscommerce.mapper.ProductMapper;
import com.lsouzadev.dscommerce.repositories.ProductRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found."));
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
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found."));
        productMapper.updateEntity(product, productDto);

        Product save = productRepository.save(product);
        return productMapper.toDto(save);
    }

    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found."));
        try {
            productRepository.delete(product);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseViolationException("Falha de integridade referêncial.");
        }
    }
}
