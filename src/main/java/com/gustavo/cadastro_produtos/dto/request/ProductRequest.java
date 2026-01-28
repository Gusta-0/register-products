package com.gustavo.cadastro_produtos.dto.request;

import com.gustavo.cadastro_produtos.core.entity.Product;
import jakarta.validation.constraints.*;

public record ProductRequest(
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
        String name,

        String description,

        @NotNull(message = "O preço não pode ser nulo")
        @Positive(message = "Preço deve ser positivo")
        Double price,

        @PositiveOrZero(message = "Quantidade não pode ser negativa")
        @NotNull(message = "A quantidade não pode ser nulo")
        Integer quantity)
{
    public ProductRequest(Product product) {
        this(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity()
        );
    }

    public Product toProduct() {
        return Product.builder()
                .name(this.name)
                .description(this.description)
                .price(this.price)
                .quantity(this.quantity)
                .build();
    }
}
