package com.gustavo.cadastro_produtos.core.service;

import com.gustavo.cadastro_produtos.dto.request.ProductRequest;
import com.gustavo.cadastro_produtos.dto.response.ProductResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CachedProductServiceImpl implements ProductService {

    private final ProductService delegate;
    private final Map<Long, ProductResponse> cache = new HashMap<>();

    public CachedProductServiceImpl(
            @Qualifier("productServiceImpl") ProductService delegate
    ) {
        this.delegate = delegate;
    }

    @Override
    public ProductResponse findById(Long id) {
        return cache.computeIfAbsent(id, delegate::findById);
    }

    @Override
    public List<ProductResponse> findAll() {
        return delegate.findAll();
    }

    @Override
    public ProductResponse saveProduct(ProductRequest request) {
        ProductResponse saved = delegate.saveProduct(request);
        cache.put(saved.id(), saved);
        return saved;
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        ProductResponse updated = delegate.update(id, request);
        cache.put(id, updated);
        return updated;
    }

    @Override
    public void delete(Long id) {
        delegate.delete(id);
        cache.remove(id);
    }

    @Override
    public ProductResponse findByName(String name) {
        return delegate.findByName(name);
    }
}

