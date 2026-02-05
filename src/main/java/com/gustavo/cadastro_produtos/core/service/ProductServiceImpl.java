package com.gustavo.cadastro_produtos.core.service;

import com.gustavo.cadastro_produtos.core.entity.Product;
import com.gustavo.cadastro_produtos.core.repository.ProductRepository;
import com.gustavo.cadastro_produtos.dto.request.ProductRequest;
import com.gustavo.cadastro_produtos.dto.request.ProductUpdateRequest;
import com.gustavo.cadastro_produtos.dto.response.ProductResponse;
import com.gustavo.cadastro_produtos.exceptions.InvalidProductException;
import com.gustavo.cadastro_produtos.exceptions.ProductNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Slf4j
@Service
@Primary
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Transactional
  public ProductResponse saveProduct(ProductRequest request) {
    if (request == null) {
      throw new InvalidProductException("O produto não pode ser nulo");
    }
    Product product = request.toProduct();
    Product saved = productRepository.save(product);

    return new ProductResponse(saved);
  }

  @Override
  public Page<ProductResponse> findAll(Pageable pageable) {
    return productRepository.findAll(pageable)
            .map(ProductResponse::new);
  }

  @Override
  public ProductResponse findById(Long id) {
    Product product = findProductById(id);
    return new ProductResponse(product);
  }

  @Transactional
  public ProductResponse update(Long id, ProductUpdateRequest request) {
    Product product = findProductById(id);
    request.applyUpdates(product);
    Product updated = productRepository.save(product);

    return new ProductResponse(updated);
  }

  @Transactional
  public void delete(Long id) {
    log.warn("Removendo produto id: {}", id);

    Product product = findProductById(id);
    productRepository.delete(product);

    log.info("Produto removido com sucesso. ID: {}", id);
  }

  private Product findProductById(Long id) {
    return productRepository.findById(id)
            .orElseThrow(() ->
                    new ProductNotFoundException("Produto não encontrado com id: " + id)
            );
  }
}

