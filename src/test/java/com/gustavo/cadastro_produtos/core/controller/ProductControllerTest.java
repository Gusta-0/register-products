package com.gustavo.cadastro_produtos.core.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gustavo.cadastro_produtos.core.entity.Product;
import com.gustavo.cadastro_produtos.core.service.ProductService;
import com.gustavo.cadastro_produtos.dto.request.ProductRequest;
import com.gustavo.cadastro_produtos.dto.response.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    Product product;
    ProductResponse productResponse;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .id(1L)
                .idProduct(100L)
                .name("Produto Teste")
                .description("Descrição")
                .price(100.0)
                .quantity(5)
                .build();

        productResponse = new ProductResponse(product);
    }

    @Test
    void shouldReturnAllProducts() throws Exception {
        Mockito.when(productService.findAll())
                .thenReturn(List.of(productResponse));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Produto Teste"));

        Mockito.verify(productService, times(1)).findAll();
    }

    @Test
    void shouldFindProductById() throws Exception {
        Mockito.when(productService.findById(1L))
                .thenReturn(productResponse);

        mockMvc.perform(get("/products/id/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Produto Teste"));

        Mockito.verify(productService, times(1)).findById(1L);
    }

    @Test
    void shouldFindProductByName() throws Exception {
        Mockito.when(productService.findByName("Produto Teste"))
                .thenReturn(productResponse);

        mockMvc.perform(get("/products/name/{name}", "Produto Teste"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Produto Teste"));

        Mockito.verify(productService, times(1))
                .findByName("Produto Teste");
    }

    @Test
    void shouldUpdateProductSuccessfully() throws Exception {
        ProductRequest request = Mockito.mock(ProductRequest.class);

        Mockito.when(productService.update(eq(1L), any()))
                .thenReturn(productResponse);

        mockMvc.perform(put("/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Produto Teste"));

        Mockito.verify(productService, times(1))
                .update(eq(1L), any());
    }

    @Test
    void shouldDeleteProductSuccessfully() throws Exception {
        Mockito.doNothing().when(productService).delete(1L);

        mockMvc.perform(delete("/products/{id}", 1L))
                .andExpect(status().isNoContent());

        Mockito.verify(productService, times(1))
                .delete(1L);
    }
}
