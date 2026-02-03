package com.gustavo.cadastro_produtos.core.service;



import com.gustavo.cadastro_produtos.dto.request.ProductRequest;
import com.gustavo.cadastro_produtos.dto.request.ProductUpdateRequest;
import com.gustavo.cadastro_produtos.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse saveProduct(ProductRequest request);
    List<ProductResponse> findAll();
    ProductResponse findById(Long id);
    ProductResponse update(Long id, ProductUpdateRequest request);
    void delete(Long id);
}
