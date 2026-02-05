
# 📦 Cadastro de Produtos – CRUD

Projeto de **CRUD de Cadastro de Produtos** desenvolvido com **Spring Boot**, seguindo boas práticas de arquitetura, separação de responsabilidades, DTOs e documentação com **Swagger/OpenAPI**.

---

## 🚀 Tecnologias Utilizadas

- ☕ Java 21+
- 🌱 Spring Boot
- 📦 Spring Data JPA
- 🐘 Hibernate
- 🗄️ Banco de dados (H2)
- 📄 Swagger (Springdoc OpenAPI)
- 🧪 Maven
- 🔧 Git & GitHub

---

## 📌 Funcionalidades

- ✅ Cadastrar produto
- ✅ Listar todos os produtos
- ✅ Buscar produto por ID
- ✅ Atualizar produto
- ✅ Remover produto
- ✅ Documentação automática com Swagger

## 🧱 Estrutura do Projeto

---

## 🔗 Endpoints da API

| Método | Endpoint | Descrição |
|------|--------|----------|
| POST | `/products` | Cadastrar produto |
| GET | `/products` | Listar todos os produtos |
| GET | `/products/{id}` | Buscar produto por ID |
| PUT | `/products/{id}` | Atualizar produto |
| DELETE | `/products/{id}` | Remover produto |

---

## 📄 Exemplo de Requisição (POST)

```json
{
  "idProduct": "PROD-001",
  "name": "Notebook",
  "description": "Notebook para uso profissional",
  "price": 3500.00,
  "quantity": 10
}
````

---

## 📄 Exemplo de Resposta

```json
{
  "id": 1,
  "idProduct": "PROD-001",
  "name": "Notebook",
  "description": "Notebook para uso profissional",
  "price": 3500.00,
  "quantity": 10
}
```

---

## 📘 Documentação Swagger

Após iniciar a aplicação, acesse:

```
http://localhost:8080/swagger-ui.html
```

ou

```
http://localhost:8080/swagger-ui/index.html
```

---

## ⚙️ Como Executar o Projeto

### 1️⃣ Clonar o repositório

```bash
git clone https://github.com/SEU_USUARIO/register-products.git
```

### 2️⃣ Acessar a pasta do projeto

```bash
cd register-products
```

### 3️⃣ Executar a aplicação

```bash
mvn spring-boot:run
```

---

## 🗄️ Banco de Dados

Por padrão, o projeto pode ser configurado para:

* **H2 (memória)** – ideal para testes

Configure em `application.yml` ou `application.properties`.

---

## 🧪 Testes

> (Opcional) Pode ser expandido com testes unitários usando JUnit e Mockito.

---

## 📚 Boas Práticas Aplicadas

* DTOs para entrada e saída de dados
* Camada de serviço isolando regras de negócio
* Controller enxuto
* Uso de `ResponseEntity`
* Documentação centralizada via interface (`ProductAPI`)
* Padrão REST

---

## 👨‍💻 Autor

Desenvolvido por **Gustavo Alves**
🔗 GitHub: [https://github.com/Gusta-o](https://github.com/Gusta-o)

---

## 📄 Licença

Este projeto é apenas para fins educacionais.



