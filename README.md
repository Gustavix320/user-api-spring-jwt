# User API com Spring Boot + JWT + Swagger + Deploy

![Java](https://img.shields.io/badge/Java-17-blue?logo=java)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.5.0-brightgreen?logo=spring)
![JWT](https://img.shields.io/badge/Auth-JWT-blueviolet?logo=jsonwebtokens)
![Swagger](https://img.shields.io/badge/Docs-Swagger-orange?logo=swagger)
![Render](https://img.shields.io/badge/Deploy-Render-black?logo=render)
![Status](https://img.shields.io/badge/API-Online-success)

---

### 🚀 Link da API em Produção:

**🔗 [https://user-api-spring-jwt.onrender.com](https://user-api-spring-jwt.onrender.com)**
**🔐 Swagger: [https://user-api-spring-jwt.onrender.com/swagger-ui/index.html](https://user-api-spring-jwt.onrender.com/swagger-ui/index.html)**

---

## 📌 Descrição do Projeto

Esta é uma API REST desenvolvida com **Java + Spring Boot**, com autenticação segura usando **JWT**, persistência com **JPA + H2 Database**, documentação via **Swagger OpenAPI**, e deploy gratuito em nuvem pela **Render**.
O projeto tem foco em segurança, organização em camadas e boas práticas REST, ideal para compor portfólio de um desenvolvedor back-end Java.

---

## 🧠 Funcionalidades

* ✅ **Cadastro de usuários (POST /users)**
* ✅ **Autenticação de usuários com JWT (POST /auth/login)**
* ✅ **Listagem, busca, atualização e exclusão de usuários protegidos por token**
* ✅ **Documentação Swagger com botão "Authorize" para testar com JWT**
* ✅ **Validação e criptografia de senhas com BCrypt**
* ✅ **Organização por camadas: `controller`, `service`, `repository`, `model`, `config`**
* ✅ **Deploy funcional no Render com auto deploy a partir do GitHub**

---

## 🔧 Tecnologias Utilizadas

| Ferramenta              | Função                                |
| ----------------------- | ------------------------------------- |
| **Java 17**             | Linguagem principal                   |
| **Spring Boot 3.5.0**   | Framework principal                   |
| **Spring Web**          | API REST                              |
| **Spring Security**     | Autenticação e proteção com JWT       |
| **JWT (jjwt)**          | Geração e validação de tokens         |
| **Spring Data JPA**     | Persistência de dados                 |
| **H2 Database**         | Banco de dados em memória para testes |
| **Swagger (SpringDoc)** | Documentação e testes da API          |
| **BCrypt**              | Criptografia de senhas                |
| **Dockerfile + Render** | Deploy automatizado em nuvem          |

---

## 🔐 Autenticação

* O login é feito via `POST /auth/login` com e-mail e senha
* Se autenticado, o servidor retorna um token JWT
* Este token deve ser enviado no cabeçalho `Authorize` para acessar rotas protegidas

---

## 🥮 Exemplos de uso

### ▶️ Criar usuário (POST /users)

```json
{
  "name": "João da Silva",
  "email": "joao@email.com.br",
  "password": "12345",
  "role": "USER"
}
```

### ▶️ Login (POST /auth/login)

```json
{
  "email": "joao@email.com.br",
  "password": "12345"
}
```

Resposta:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6..."
}
```

### ▶️ Exemplo de uso do token

No Swagger, clique em **Authorize** e cole assim:

```
eyJhbGciOiJIUzI1NiIsInR5cCI6...
```

---

## 📁 Estrutura do Projeto

```
src/
├── config/
│   ├── SecurityConfig.java
│   ├── JwtAuthenticationFilter.java
│   └── JwtUtil.java
│
├── controller/
│   ├── UserController.java
│   ├── AuthController.java
│   └── HomeController.java
│
├── model/
│   ├── User.java
│   ├── UserRequestDTO.java
│   └── UserResponseDTO.java
│
├── repository/
│   └── UserRepository.java
│
└── service/
    └── UserDetailServiceImpl.java
```

---

## ☁️ Deploy com Render

* ✅ Dockerfile configurado
* ✅ Deploy automático via GitHub Push
* ✅ Aplicação disponível 24h (com spin-down automático na versão gratuita)

---

## 📈 O que ainda pode ser implementado

* 🔄 Refresh Token com tempo de expiração
* 🦖 Testes automatizados com JUnit + Mockito
* 🚩 Integração com PostgreSQL (Railway ou RDS)
* 🔎 Filtro por nome ou e-mail
* 📄 Exportação de usuários para CSV ou Excel
* 🔑 Diferenciação de permissões por `role` (ex: ADMIN vs USER)

---

## 📌 Como rodar localmente

```bash
git clone https://github.com/Gustavix320/user-api-spring-jwt
cd user-api-spring-jwt
./mvnw spring-boot:run
```

Acesse em: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

👋 Projeto ideal para portfólio de desenvolvedor Java. Abra issues ou mande uma mensagem se quiser colaborar!
