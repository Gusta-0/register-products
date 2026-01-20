package com.gustavo.cadastro_produtos.dto.response;

import com.gustavo.cadastro_produtos.core.entity.Product;

public record ProductResponse(
    Long id,
    String name,
    String description,
    Double price
) {
    public ProductResponse (Product product) {
        this(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice()
        );
    }
}
