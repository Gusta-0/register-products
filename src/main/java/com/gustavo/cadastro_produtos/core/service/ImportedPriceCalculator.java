package com.gustavo.cadastro_produtos.core.service;

import com.gustavo.cadastro_produtos.core.entity.Product;

import java.math.BigDecimal;

public class ImportedPriceCalculator implements PriceCalculator{

    private static final BigDecimal IMPORT_DUTY_RATE = new BigDecimal("0.15");

    @Override
    public BigDecimal calculate(Product product) {
        BigDecimal basePrice = BigDecimal.valueOf(product.getPrice());
        BigDecimal importDuty = basePrice.multiply(IMPORT_DUTY_RATE);
        return basePrice.add(importDuty);
    }
}
