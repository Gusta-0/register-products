package com.gustavo.cadastro_produtos.core.controller;

import com.gustavo.cadastro_produtos.config.ProductAPI;
import com.gustavo.cadastro_produtos.core.service.ProductService;
import com.gustavo.cadastro_produtos.dto.request.ProductRequest;
import com.gustavo.cadastro_produtos.dto.request.ProductUpdateRequest;
import com.gustavo.cadastro_produtos.dto.response.ProductResponse;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/products")
public class ProductController implements ProductAPI {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  public ResponseEntity<ProductResponse> save(@Valid @RequestBody ProductRequest request) {
    ProductResponse response = productService.saveProduct(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping
  public ResponseEntity<Page<ProductResponse>> findAll(@ParameterObject Pageable pageable) {
    return ResponseEntity.ok(productService.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
    return ResponseEntity.ok(productService.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductResponse> update(
          @PathVariable Long id,
          @Valid @RequestBody ProductUpdateRequest request
  ) {
    return ResponseEntity.ok(productService.update(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    productService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
