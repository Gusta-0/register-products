package com.gustavo.cadastro_produtos.core.service;

import com.gustavo.cadastro_produtos.core.entity.Product;
import com.gustavo.cadastro_produtos.core.repository.ProductRepository;
import com.gustavo.cadastro_produtos.dto.request.ProductRequest;
import com.gustavo.cadastro_produtos.dto.response.ProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponse saveProduct(ProductRequest productRequest) {
        if (productRequest == null) {
            throw new IllegalArgumentException("productRequest não pode ser nulo");
        }

        var product = productRequest.toProduct();
        var savedProduct = productRepository.save(product);
        return new ProductResponse(savedProduct);
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponse::new)
                .toList();
    }

    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return new ProductResponse(product);
    }

    public ProductResponse update(Long id, ProductRequest request) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Product updated = Product.builder()
                .id(existing.getId())
                .idProduct(request.idProduct())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .quantity(request.quantity())
                .build();

        productRepository.save(updated);
        return new ProductResponse(updated);
    }

    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado");
        }
        productRepository.deleteById(id);
    }
}
