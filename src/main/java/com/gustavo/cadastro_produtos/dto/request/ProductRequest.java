package com.gustavo.cadastro_produtos.dto.request;

import com.gustavo.cadastro_produtos.core.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductRequest(
        @NotNull(message = "O ID não pode ser nulo") Long idProduct,

        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 8, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
        String name,

        String description,

        @NotNull(message = "O preço não pode ser nulo") Double price,
        @NotNull(message = "A quantidade não pode ser nulo") Integer quantity)
{
    public ProductRequest(Product product) {
        this(
                product.getIdProduct(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity()
        );
    }

    public Product toProduct() {
        return Product.builder()
                .idProduct(this.idProduct)
                .name(this.name)
                .description(this.description)
                .price(this.price)
                .quantity(this.quantity)
                .build();
    }
}
