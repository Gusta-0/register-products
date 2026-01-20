package com.gustavo.cadastro_produtos.config;

import com.gustavo.cadastro_produtos.dto.request.ProductRequest;
import com.gustavo.cadastro_produtos.dto.response.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Products",
        description = "Endpoints para gerenciamento de produtos"
)
@RequestMapping("/products")
public interface ProductAPI {

    @Operation(
            summary = "Cadastrar um novo produto",
            description = "Cria um novo produto no sistema com base nos dados informados",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Produto criado com sucesso",
                            content = @Content(schema = @Schema(implementation = ProductResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Dados inválidos"
                    )
            }
    )
    @PostMapping
    ResponseEntity<ProductResponse> save(
            @RequestBody ProductRequest request
    );

    @Operation(
            summary = "Listar todos os produtos",
            description = "Retorna uma lista com todos os produtos cadastrados",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de produtos retornada com sucesso",
                            content = @Content(schema = @Schema(implementation = ProductResponse.class))
                    )
            }
    )
    @GetMapping
    ResponseEntity<List<ProductResponse>> findAll();

    @Operation(
            summary = "Buscar produto por ID",
            description = "Retorna os dados de um produto específico pelo seu ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Produto encontrado",
                            content = @Content(schema = @Schema(implementation = ProductResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Produto não encontrado"
                    )
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<ProductResponse> findById(
            @PathVariable Long id
    );

    @Operation(
            summary = "Atualizar produto",
            description = "Atualiza os dados de um produto existente",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Produto atualizado com sucesso",
                            content = @Content(schema = @Schema(implementation = ProductResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Produto não encontrado"
                    )
            }
    )
    @PutMapping("/{id}")
    ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @RequestBody ProductRequest request
    );

    @Operation(
            summary = "Remover produto",
            description = "Remove um produto do sistema pelo ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Produto removido com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Produto não encontrado"
                    )
            }
    )
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(
            @PathVariable Long id
    );
}