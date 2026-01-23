package com.gustavo.cadastro_produtos.core.service;

import com.gustavo.cadastro_produtos.core.entity.Product;
import com.gustavo.cadastro_produtos.core.repository.ProductRepository;
import com.gustavo.cadastro_produtos.dto.request.ProductRequest;
import com.gustavo.cadastro_produtos.dto.response.ProductResponse;
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
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    Product product;
    Product product2;

    @BeforeEach
    public void setUp(){
        product = Product.builder()
                .id(1885858L)
                .idProduct(85448001L)
                .name("Produto Teste 1")
                .description("Descrição do Produto Teste")
                .price(100.0)
                .build();

        product2 = Product.builder()
                .id(1852582L)
                .idProduct(1284282L)
                .name("Produto Teste 2")
                .description("Descrição do Produto Teste")
                .price(200.0)
                .build();
    }

    @Test
    void shouldSaveProductWithSuccessfully() {
        ProductRequest productRequest = Mockito.mock(ProductRequest.class);

        Mockito.when(productRequest.toProduct())
                .thenReturn(product);

        Mockito.when(productRepository.save(product))
                .thenReturn(product);

        ProductResponse response = productService.saveProduct(productRequest);

        assertNotNull(response);
        assertEquals(product.getId(), response.id());
        assertEquals(product.getName(), response.name());
        assertEquals(product.getPrice(), response.price());

        Mockito.verify(productRepository, Mockito.times(1))
                .save(product);
    }

    @Test
    void shouldThrowExceptionWhenProductRequestIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> productService.saveProduct(null)
        );

        assertEquals("O Produto não pode ser nulo", exception.getMessage());

        Mockito.verifyNoInteractions(productRepository);
    }

    @Test
    void shouldReturnAllProducts() {

        List<Product> products = List.of(product, product2);

        Mockito.when(productRepository.findAll())
                .thenReturn(products);

        List<ProductResponse> response = productService.findAll();

        assertNotNull(response);
        assertEquals(2, response.size());

        assertEquals(product.getId(), response.get(0).id());
        assertEquals(product2.getId(), response.get(1).id());

        Mockito.verify(productRepository, Mockito.times(1))
                .findAll();
    }

    @Test
    void shouldFindProductByIdSuccessfully() {
        Long id = product.getId();

        Mockito.when(productRepository.findById(id))
                .thenReturn(Optional.of(product));

        ProductResponse response = productService.findById(id);

        assertNotNull(response);
        assertEquals(product.getId(), response.id());
        assertEquals(product.getName(), response.name());

        Mockito.verify(productRepository, Mockito.times(1))
                .findById(id);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFoundById() {
        Long id = 999L;

        Mockito.when(productRepository.findById(id))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> productService.findById(id)
        );

        assertEquals("Produto não encontrado", exception.getMessage());

        Mockito.verify(productRepository, Mockito.times(1))
                .findById(id);
    }

    @Test
    void shouldFindProductByNameSuccessfully() {
        String name = product.getName();

        Mockito.when(productRepository.findByName(name))
                .thenReturn(Optional.of(product));

        ProductResponse response = productService.findByName(name);

        assertNotNull(response);
        assertEquals(product.getName(), response.name());
        assertEquals(product.getPrice(), response.price());

        Mockito.verify(productRepository, Mockito.times(1))
                .findByName(name);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFoundByName() {
        String name = "Produto Inexistente";

        Mockito.when(productRepository.findByName(name))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> productService.findByName(name)
        );

        assertEquals("Produto não encontrado", exception.getMessage());

        Mockito.verify(productRepository, Mockito.times(1))
                .findByName(name);
    }

    @Test
    void shouldUpdateProductSuccessfully() {

        Long id = product.getId();

        ProductRequest request = Mockito.mock(ProductRequest.class);

        Mockito.when(productRepository.findById(id))
                .thenReturn(Optional.of(product));

        Mockito.when(request.idProduct()).thenReturn(999L);
        Mockito.when(request.name()).thenReturn("Produto Atualizado");
        Mockito.when(request.description()).thenReturn("Descrição Atualizada");
        Mockito.when(request.price()).thenReturn(150.0);
        Mockito.when(request.quantity()).thenReturn(10);

        Mockito.when(productRepository.save(Mockito.any(Product.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ProductResponse response = productService.update(id, request);

        assertNotNull(response);
        assertEquals(id, response.id());
        assertEquals("Produto Atualizado", response.name());
        assertEquals(150.0, response.price());

        Mockito.verify(productRepository, Mockito.times(1))
                .findById(id);

        Mockito.verify(productRepository, Mockito.times(1))
                .save(Mockito.any(Product.class));
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingProduct() {
        Long id = 999L;
        ProductRequest request = Mockito.mock(ProductRequest.class);

        Mockito.when(productRepository.findById(id))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> productService.update(id, request)
        );

        assertEquals("Produto não encontrado", exception.getMessage());

        Mockito.verify(productRepository, Mockito.times(1))
                .findById(id);

        Mockito.verify(productRepository, Mockito.never())
                .save(Mockito.any());
    }

    @Test
    void shouldDeleteProductSuccessfully() {
        Long id = product.getId();

        Mockito.when(productRepository.existsById(id))
                .thenReturn(true);

        productService.delete(id);

        Mockito.verify(productRepository, Mockito.times(1))
                .existsById(id);

        Mockito.verify(productRepository, Mockito.times(1))
                .deleteById(id);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingProduct() {
        Long id = 999L;

        Mockito.when(productRepository.existsById(id))
                .thenReturn(false);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> productService.delete(id)
        );

        assertEquals("Produto não encontrado", exception.getMessage());

        Mockito.verify(productRepository, Mockito.times(1))
                .existsById(id);

        Mockito.verify(productRepository, Mockito.never())
                .deleteById(Mockito.anyLong());
    }
}