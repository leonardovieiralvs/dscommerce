package com.lsouzadev.dscommerce.services;

import com.lsouzadev.dscommerce.dto.CategoryDto;
import com.lsouzadev.dscommerce.dto.ProductDto;
import com.lsouzadev.dscommerce.dto.ProductMinDto;
import com.lsouzadev.dscommerce.entities.Category;
import com.lsouzadev.dscommerce.entities.Product;
import com.lsouzadev.dscommerce.exceptions.DatabaseViolationException;
import com.lsouzadev.dscommerce.exceptions.ResourceNotFoundException;
import com.lsouzadev.dscommerce.mapper.ProductMapper;
import com.lsouzadev.dscommerce.repositories.CategoryRepository;
import com.lsouzadev.dscommerce.repositories.ProductRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found."));
        return productMapper.toDto(product);
    }

    public Page<ProductMinDto> findAll(String name, Pageable pageable) {
        Page<Product> products = productRepository.searchByName(name, pageable);
        return products.map(productMapper::toMinDto);
    }

    public ProductDto insert(ProductDto productDto) {

        Product entity = productMapper.toEntity(productDto);

        entity.getCategories().clear();

        for (CategoryDto dto : productDto.categories()) {

            Category category = categoryRepository
                    .getReferenceById(dto.id());

            entity.getCategories().add(category);
        }

        entity = productRepository.save(entity);

        return productMapper.toDto(entity);
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
