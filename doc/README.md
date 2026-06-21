# Banco Digital

## Visão geral

Aplicação Java com Spring Boot para cadastro, autenticação JWT e consulta de usuários e contas de um banco digital.

## Stack

- Java 21
- Spring Boot 3.5.x
- Spring Web
- Spring Data JPA
- Spring Security
- JWT
- Validation
- PostgreSQL
- Lombok

## Como executar

```bash
./mvnw spring-boot:run
```

O projeto roda por padrão na porta 8080.

## Segurança

O projeto usa autenticação JWT.

### Rotas públicas

- POST /api/users
- POST /api/auth/login

### Rotas protegidas

- GET /api/users
- GET /api/accounts
- POST /api/accounts
- POST /api/accounts/create

Depois do login, envie o token no header:

```http
Authorization: Bearer <token>
```

## Usuários

### POST /api/users

Cria um novo usuário.

Exemplo de corpo:

```json
{
  "firstName": "João",
  "lastName": "Silva",
  "cpf": "12345678901",
  "age": 25,
  "email": "joao.silva@email.com",
  "password": "123456",
  "phone": "11999999999",
  "occupation": "STUDENT",
  "income": 4500.0,
  "address": {
    "cep": "01001000",
    "logradouro": "Praça da Sé",
    "complemento": "Apto 12",
    "bairro": "Sé",
    "localidade": "São Paulo",
    "uf": "SP"
  }
}
```

Valores aceitos para occupation:

- STUDENT
- AUTONOMOUS
- MEI
- EMPRESARIO
- PUBLIC_SERVANT

Regras principais:

- cpf deve conter 11 dígitos
- age deve ser maior ou igual a 18
- email deve ser válido
- password, firstName, lastName, phone, occupation, income e address são obrigatórios

### GET /api/users

Lista os usuários cadastrados. Esta rota exige autenticação.

## Login

### POST /api/auth/login

Autentica o usuário e retorna um token JWT.

Exemplo de corpo:

```json
{
  "email": "joao.silva@email.com",
  "password": "123456"
}
```

Exemplo de resposta:

```json
{
  "success": true,
  "message": "Login realizado com sucesso",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "type": "Bearer"
  }
}
```

## Contas

### POST /api/accounts

Cria uma conta simples.

Exemplo de corpo:

```json
{
  "balance": 1000.0,
  "creditLimit": 500.0,
  "accountType": "CORRENTE"
}
```

### POST /api/accounts/create

Cria usuário e conta no mesmo fluxo.

Exemplo de corpo:

```json
{
  "user": {
    "firstName": "João",
    "lastName": "Silva",
    "cpf": "12345678901",
    "age": 25,
    "email": "joao.silva@email.com",
    "password": "123456",
    "phone": "11999999999",
    "occupation": "STUDENT",
    "income": 4500.0,
    "address": {
      "cep": "01001000",
      "logradouro": "Praça da Sé",
      "complemento": "Apto 12",
      "bairro": "Sé",
      "localidade": "São Paulo",
      "uf": "SP"
    }
  },
  "account": {
    "balance": 1000.0,
    "creditLimit": 500.0,
    "accountType": "CORRENTE"
  }
}
```

### GET /api/accounts

Lista as contas cadastradas. Esta rota exige autenticação.

## Respostas

Em caso de sucesso, o cadastro de usuário responde com 201 Created.

## Validação

- A senha é armazenada com hash automaticamente.
- O token JWT expira conforme `JWT_EXPIRATION_MS`.
- Os dados sensíveis são mascarados nas respostas de saída.
- O projeto usa PostgreSQL como banco de dados.