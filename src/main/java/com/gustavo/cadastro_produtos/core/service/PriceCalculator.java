package com.gustavo.cadastro_produtos.core.service;

import com.gustavo.cadastro_produtos.core.entity.Product;
import java.math.BigDecimal;

//Aberto para extensão, fechado para modificação (princípio do Open/Closed do SOLID)
public interface PriceCalculator {
    BigDecimal calculate(Product product);
}
