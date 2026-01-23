package com.gustavo.cadastro_produtos.core.repository;

import com.gustavo.cadastro_produtos.core.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> , JpaSpecificationExecutor<Product> {

    Optional<Product> findByName(String name);
}
