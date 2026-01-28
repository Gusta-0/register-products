package com.gustavo.cadastro_produtos.exceptions;

public class ProductNotFoundException extends BusinessException {
    public ProductNotFoundException(Long id) {
        super("Produto não encontrado para o id: " + id);
    }

    public ProductNotFoundException(String name) {
        super("Produto não encontrado com nome: " + name);
    }
}

