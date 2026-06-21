# Galeria de Imagens

As imagens abaixo documentam os fluxos principais da aplicação.

## Usuários

![Criação de usuário](user/createdUser.png)
![Listagem de usuários](user/listedUser.png)

## Login

![Login com sucesso](login/loginOk.png)
![Login com erro de validação](login/loginBadRequest.png)

## Contas

![Criação de conta com token](account/accountCreatedToken.png)
![Listagem de contas com token](account/accountListadToken.png)
![Acesso negado sem token](account/accountAccessDenied.png)

# Banco Digital

Aplicação Java com Spring Boot para cadastro, autenticação e consulta de usuários de um banco digital.

## Visão geral

O projeto expõe endpoints REST para criação de usuário, login com JWT e consulta de usuários. As entradas são validadas, a senha é armazenada com hash e as rotas protegidas exigem token Bearer.

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

O projeto roda por padrão na porta 8080.

```bash
./mvnw spring-boot:run
```

Se preferir, você também pode executar o projeto pelo botão de run da IDE.

## Estrutura principal

- controller: entrada HTTP da aplicação
- service: regras de negócio
- dto: objetos de entrada e saída
- entity: mapeamento das tabelas
- repository: acesso ao banco
- config: configurações de segurança e aplicação
- security: autenticação JWT e integração com Spring Security
- enums: tipos fixos do domínio
- util: utilitários de máscara e apoio

## Segurança

O projeto usa autenticação JWT.

### Rotas públicas

- POST /api/users
- POST /api/auth/login

### Rotas protegidas

- GET /api/users
- GET /api/accounts
- POST /api/accounts/create

Depois do login, envie o token no header:

```http
Authorization: Bearer <token>
```

## Endpoints

### POST /api/users

Cria um novo usuário.

Headers:

- Content-Type: application/json

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

### GET /api/users

Lista os usuários cadastrados. Esta rota exige autenticação.

### GET /api/accounts

Lista as contas cadastradas. Esta rota exige autenticação.

### POST /api/accounts/create

Cria uma conta. Esta rota exige autenticação.

## Teste rápido

### cURL - criar usuário

```bash
curl -X POST http://localhost:8080/api/users ^
  -H "Content-Type: application/json" ^
  -d "{\"firstName\":\"João\",\"lastName\":\"Silva\",\"cpf\":\"12345678901\",\"age\":25,\"email\":\"joao.silva@email.com\",\"password\":\"123456\",\"phone\":\"11999999999\",\"occupation\":\"STUDENT\",\"income\":4500.0,\"address\":{\"cep\":\"01001000\",\"logradouro\":\"Praça da Sé\",\"complemento\":\"Apto 12\",\"bairro\":\"Sé\",\"localidade\":\"São Paulo\",\"uf\":\"SP\"}}"
```

### cURL - login

```bash
curl -X POST http://localhost:8080/api/auth/login ^
  -H "Content-Type: application/json" ^
  -d "{\"email\":\"joao.silva@email.com\",\"password\":\"123456\"}"
```

### cURL - listar usuários com token

```bash
curl -X GET http://localhost:8080/api/users ^
  -H "Authorization: Bearer <token>"
```

## Postman

1. Crie uma request POST para http://localhost:8080/api/users.
2. Em Headers, adicione Content-Type: application/json.
3. Em Body, selecione raw e depois JSON.
4. Cole o JSON de exemplo.
5. Crie uma request POST para http://localhost:8080/api/auth/login e envie email e password.
6. Copie o token retornado e use o header Authorization: Bearer <token> nas rotas protegidas.

## Resposta esperada

Em caso de sucesso, o cadastro de usuário responde com 201 Created.

```json
{
  "success": true,
  "message": "Usuário criado com sucesso",
  "data": {
    "firstName": "João",
    "lastName": "Silva",
    "cpf": "123.456.789-01",
    "age": 25,
    "email": "j***@email.com",
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
}
```

## Observações

- A senha é armazenada com hash automaticamente.
- O token JWT expira conforme JWT_EXPIRATION_MS.
- Os dados sensíveis são mascarados nas respostas de saída.
- O projeto usa PostgreSQL como banco de dados.