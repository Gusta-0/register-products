package com.gustavo.cadastro_produtos.dto.request;

import com.gustavo.cadastro_produtos.core.entity.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record ProductUpdateRequest(
        @NotNull(message = "O ID do produto é obrigatório")
        Long id,

        @Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres")
        String name,

        String description,

        @Positive(message = "Preço deve ser positivo")
        Double price,

        @PositiveOrZero(message = "Quantidade não pode ser negativa")
        Integer quantity){

    public void applyUpdates(Product product) {

        if (name != null) {
            product.setName(name);
        }

        if (description != null) {
            product.setDescription(description);
        }

        if (price != null) {
            product.setPrice(price);
        }

        if (quantity != null) {
            product.setQuantity(quantity);
        }
    }
}
