package com.gustavo.cadastro_produtos.core.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gustavo.cadastro_produtos.core.entity.Product;
import com.gustavo.cadastro_produtos.core.service.ProductService;
import com.gustavo.cadastro_produtos.dto.request.ProductUpdateRequest;
import com.gustavo.cadastro_produtos.dto.response.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductController.class)
class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private ProductService productService;

  private final ObjectMapper objectMapper = new ObjectMapper();

  Product product;
  ProductResponse productResponse;

  @BeforeEach
  void setUp() {
    product = Product.builder()
      .id(1L)
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

    mockMvc.perform(get("/products/{id}", 1L))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").value(1L))
      .andExpect(jsonPath("$.name").value("Produto Teste"));

    Mockito.verify(productService, times(1)).findById(1L);
  }

  @Test
  void shouldUpdateProductSuccessfully() throws Exception {
    // JSON de update (parcial ou completo, conforme seu DTO)
    String updateJson = """
            {
              "name": "Produto Atualizado",
              "description": "Descrição Atualizada",
              "price": 150.0,
              "quantity": 10
            }
            """;

    Mockito.when(productService.update(eq(1L), any(ProductUpdateRequest.class)))
      .thenReturn(productResponse);

    mockMvc.perform(put("/products/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .content(updateJson))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").value(1L))
      .andExpect(jsonPath("$.name").value("Produto Teste"));

    Mockito.verify(productService, times(1))
      .update(eq(1L), any(ProductUpdateRequest.class));
  }

  @Test
  void shouldDeleteProductSuccessfully() throws Exception {
    Mockito.doNothing().when(productService).delete(1L);

    mockMvc.perform(delete("/products/{id}", 1L))
      .andExpect(status().isNoContent());

    Mockito.verify(productService, times(1)).delete(1L);
  }
}
