package com.gustavo.cadastro_produtos.core.service;

import com.gustavo.cadastro_produtos.core.entity.Product;
import java.math.BigDecimal;

public interface PriceCalculator {
    BigDecimal calculate(Product product);
}
