package com.gustavo.cadastro_produtos.core.service;

import com.gustavo.cadastro_produtos.core.entity.Product;

import java.math.BigDecimal;

public class NormalPriceCalculator implements PriceCalculator{
    @Override
    public BigDecimal calculate(Product product) {
        return BigDecimal.valueOf(product.getPrice());
    }
}
