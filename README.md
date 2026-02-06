
# 📦 Cadastro de Produtos – CRUD

Projeto de **CRUD de Cadastro de Produtos** desenvolvido com **Spring Boot**, seguindo boas práticas de arquitetura, separação de responsabilidades, DTOs e documentação com **Swagger/OpenAPI**.

---

## 🚀 Tecnologias Utilizadas

* ☕ Java 21+
* 🌱 Spring Boot
* 📦 Spring Data JPA
* 🐘 Hibernate
* 🗄️ Banco de dados (H2 em memória, PostgreSQL no Azure)
* 📄 Swagger (Springdoc OpenAPI)
* 🧪 Gradle
* 🔧 Git & GitHub
* 🧪 JUnit 5 para testes unitários

---

## 📌 Funcionalidades

* ✅ Cadastrar produto
* ✅ Listar todos os produtos
* ✅ Buscar produto por ID
* ✅ Atualizar produto
* ✅ Remover produto
* ✅ Documentação automática com Swagger

---

## 🧱 Estrutura do Projeto

* **Controller** – Responsável pelos endpoints
* **Service** – Regras de negócio
* **Repository** – Acesso a dados via JPA
* **DTOs** – Objetos de transferência
* **Tests** – Testes unitários com JUnit 5

---

## 🔗 Endpoints da API

| Método | Endpoint         | Descrição                |
| ------ | ---------------- | ------------------------ |
| POST   | `/products`      | Cadastrar produto        |
| GET    | `/products`      | Listar todos os produtos |
| GET    | `/products/{id}` | Buscar produto por ID    |
| PUT    | `/products/{id}` | Atualizar produto        |
| DELETE | `/products/{id}` | Remover produto          |

---

## 📄 Exemplo de Requisição (POST)

```json
{
  "name": "Notebook",
  "description": "Notebook para uso profissional",
  "price": 3500.00,
  "quantity": 10
}
```

---

## 📄 Exemplo de Resposta

```json
{
  "id": 1,
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
git clone https://github.com/Gusta-0/register-products.git
```

### 2️⃣ Acessar a pasta do projeto

```bash
cd register-products
```

### 3️⃣ Executar a aplicação com Gradle

```bash
./gradlew bootRun
```

> No Windows PowerShell, use:

```powershell
gradlew.bat bootRun
```

---

## 🗄️ Banco de Dados

Por padrão, o projeto pode ser configurado para:

* **H2 (memória)** – ideal para testes
* **PostgreSQL (Azure)** – produção

> Configure o banco em `application.yml` ou `application.properties`.

---

## 🧪 Testes Unitários

O projeto possui testes unitários implementados com **JUnit 5**.

Para rodar os testes com Gradle:

```bash
./gradlew test
```

> No Windows PowerShell, use:

```powershell
gradlew.bat test
```

---

## 📚 Boas Práticas Aplicadas

* DTOs para entrada e saída de dados
* Camada de serviço isolando regras de negócio
* Controller enxuto
* Uso de `ResponseEntity`
* Documentação centralizada via interface (`ProductAPI`)
* Padrão REST
* Testes unitários para validação das regras de negócio

---

## 👨‍💻 Autor

Desenvolvido por **Gustavo Alves**
🔗 GitHub: [https://github.com/Gusta-o](https://github.com/Gusta-o)

---

## 📄 Licença

Este projeto é apenas para fins educacionais.


