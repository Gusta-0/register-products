package com.gustavo.cadastro_produtos.core.service;

import com.gustavo.cadastro_produtos.core.entity.Product;
import com.gustavo.cadastro_produtos.core.repository.ProductRepository;
import com.gustavo.cadastro_produtos.dto.request.ProductRequest;
import com.gustavo.cadastro_produtos.dto.response.ProductResponse;
import com.gustavo.cadastro_produtos.exceptions.BusinessException;
import com.gustavo.cadastro_produtos.exceptions.ProductNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponse saveProduct(ProductRequest request) {
        if (request == null) {
            throw new BusinessException("O Produto não pode ser nulo");
        }

        Product product = request.toProduct();
        Product saved = productRepository.save(product);
        return new ProductResponse(saved);
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponse::new)
                .toList();
    }

    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(id));
        return new ProductResponse(product);
    }

    public ProductResponse findByName(String name) {
        Product product = productRepository.findByName(name)
                .orElseThrow(() ->
                        new ProductNotFoundException(name));
        return new ProductResponse(product);
    }

    public ProductResponse update(Long id, ProductRequest request) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(id));

        Product updated = Product.builder()
                .id(existing.getId())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .quantity(request.quantity())
                .build();

        return new ProductResponse(productRepository.save(updated));
    }

    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }
}
