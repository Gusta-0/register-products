package com.gustavo.cadastro_produtos.core.service;

import com.gustavo.cadastro_produtos.core.entity.Product;
import com.gustavo.cadastro_produtos.core.repository.ProductRepository;
import com.gustavo.cadastro_produtos.dto.request.ProductRequest;
import com.gustavo.cadastro_produtos.dto.request.ProductUpdateRequest;
import com.gustavo.cadastro_produtos.dto.response.ProductResponse;
import com.gustavo.cadastro_produtos.exceptions.BusinessException;
import com.gustavo.cadastro_produtos.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @InjectMocks
  ProductServiceImpl productService;

  @Mock
  ProductRepository productRepository;

  @Mock
  ProductUpdateRequest productUpdateRequest;

  Product product;
  Product product2;

  @BeforeEach
  void setUp() {
    product = Product.builder()
      .id(1885858L)
      .name("Produto Teste 1")
      .description("Descrição do Produto Teste")
      .price(100.0)
      .quantity(5)
      .build();

    product2 = Product.builder()
      .id(1852582L)
      .name("Produto Teste 2")
      .description("Descrição do Produto Teste")
      .price(200.0)
      .quantity(10)
      .build();
  }

  @Test
  void shouldSaveProductWithSuccessfully() {
    ProductRequest productRequest = Mockito.mock(ProductRequest.class);

    Mockito.when(productRequest.toProduct()).thenReturn(product);
    Mockito.when(productRepository.save(product)).thenReturn(product);

    ProductResponse response = productService.saveProduct(productRequest);

    assertNotNull(response);
    assertEquals(product.getId(), response.id());
    assertEquals(product.getName(), response.name());
    assertEquals(product.getPrice(), response.price());

    Mockito.verify(productRepository, Mockito.times(1)).save(product);
  }

  @Test
  void shouldThrowExceptionWhenProductRequestIsNull() {
    BusinessException exception = assertThrows(
      BusinessException.class,
      () -> productService.saveProduct(null)
    );

    assertEquals("O Produto não pode ser nulo", exception.getMessage());
    Mockito.verifyNoInteractions(productRepository);
  }

  @Test
  void shouldReturnAllProducts() {
    List<Product> products = List.of(product, product2);

    Mockito.when(productRepository.findAll()).thenReturn(products);

    List<ProductResponse> response = productService.findAll();

    assertNotNull(response);
    assertEquals(2, response.size());
    assertEquals(product.getId(), response.get(0).id());
    assertEquals(product2.getId(), response.get(1).id());

    Mockito.verify(productRepository, Mockito.times(1)).findAll();
  }

  @Test
  void shouldFindProductByIdSuccessfully() {
    Long id = product.getId();

    Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product));

    ProductResponse response = productService.findById(id);

    assertNotNull(response);
    assertEquals(product.getId(), response.id());
    assertEquals(product.getName(), response.name());

    Mockito.verify(productRepository, Mockito.times(1)).findById(id);
  }

  @Test
  void shouldThrowExceptionWhenProductNotFoundById() {
    Long id = 999L;

    Mockito.when(productRepository.findById(id)).thenReturn(Optional.empty());

    ProductNotFoundException exception = assertThrows(
      ProductNotFoundException.class,
      () -> productService.findById(id)
    );

    assertTrue(exception.getMessage().contains(String.valueOf(id)));

    Mockito.verify(productRepository, Mockito.times(1)).findById(id);
  }

  @Test
  void shouldUpdateProductSuccessfully() {
    Long id = product.getId();

    Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product));
    Mockito.when(productRepository.save(Mockito.any(Product.class)))
      .thenAnswer(invocation -> invocation.getArgument(0));

    ProductResponse response = productService.update(id, productUpdateRequest);

    assertNotNull(response);
    assertEquals(id, response.id());

    Mockito.verify(productRepository, Mockito.times(1)).findById(id);
    Mockito.verify(productUpdateRequest, Mockito.times(1)).applyUpdates(product);
    Mockito.verify(productRepository, Mockito.times(1)).save(product);
  }

  @Test
  void shouldThrowExceptionWhenUpdatingNonExistingProduct() {
    Long id = 999L;

    Mockito.when(productRepository.findById(id)).thenReturn(Optional.empty());

    ProductNotFoundException exception = assertThrows(
      ProductNotFoundException.class,
      () -> productService.update(id, productUpdateRequest)
    );

    assertTrue(exception.getMessage().contains(String.valueOf(id)));

    Mockito.verify(productRepository, Mockito.times(1)).findById(id);
    Mockito.verify(productRepository, Mockito.never()).save(Mockito.any());
  }

  @Test
  void shouldDeleteProductSuccessfully() {
    Long id = product.getId();

    Mockito.when(productRepository.existsById(id)).thenReturn(true);

    productService.delete(id);

    Mockito.verify(productRepository, Mockito.times(1)).existsById(id);
    Mockito.verify(productRepository, Mockito.times(1)).deleteById(id);
  }

  @Test
  void shouldThrowExceptionWhenDeletingNonExistingProduct() {
    Long id = 999L;

    Mockito.when(productRepository.existsById(id)).thenReturn(false);

    ProductNotFoundException exception = assertThrows(
      ProductNotFoundException.class,
      () -> productService.delete(id)
    );

    assertTrue(exception.getMessage().contains(String.valueOf(id)));

    Mockito.verify(productRepository, Mockito.times(1)).existsById(id);
    Mockito.verify(productRepository, Mockito.never()).deleteById(Mockito.anyLong());
  }
}
