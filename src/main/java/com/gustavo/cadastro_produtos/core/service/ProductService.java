package com.gustavo.cadastro_produtos.core.service;



import com.gustavo.cadastro_produtos.dto.request.ProductRequest;
import com.gustavo.cadastro_produtos.dto.response.ProductResponse;

import java.util.List;

//Cada classe com sua responsabilidade única, seguindo o princípio da responsabilidade única (SRP) do SOLID.

public interface ProductService {

    ProductResponse saveProduct(ProductRequest request);
    List<ProductResponse> findAll();
    ProductResponse findById(Long id);
    ProductResponse findByName(String name);
    ProductResponse update(Long id, ProductRequest request);
    void delete(Long id);
}
