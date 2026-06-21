# Testes Sequenciais

Este documento mostra uma forma prática de testar a aplicação em sequência, usando o Postman.

## Ordem recomendada

1. Criar usuário em `POST /api/users`
2. Fazer login em `POST /api/auth/login`
3. Usar o token JWT nas rotas protegidas
4. Criar conta em `POST /api/accounts` ou `POST /api/accounts/create`
5. Listar usuários, contas e transferências

## 1. Criar usuário

Envie uma requisição `POST` para:

```text
/api/users
```

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

## 2. Fazer login

Envie uma requisição `POST` para:

```text
/api/auth/login
```

Exemplo de corpo:

```json
{
  "email": "joao.silva@email.com",
  "password": "123456"
}
```

Copie o valor de `data.token` da resposta.

## 3. Configurar o token

No Postman, selecione `Authorization` e use:

- Type: `Bearer Token`
- Token: cole o JWT retornado no login

## 4. Criar conta

Envie uma requisição `POST` para:

```text
/api/accounts
```

Exemplo de corpo:

```json
{
  "balance": 1000.0,
  "creditLimit": 500.0,
  "accountType": "CORRENTE"
}
```

Se quiser criar usuário e conta juntos, use:

```text
/api/accounts/create
```

## 5. Listar dados

Com o mesmo token, teste:

- `GET /api/users`
- `GET /api/accounts`
- `GET /api/transfers`

## 6. Criar transferência

Envie uma requisição `POST` para:

```text
/api/transfers
```

Exemplo de corpo:

```json
{
  "sourceAccountNumber": "1234567890",
  "destinationAccountNumber": "9876543210",
  "description": "Pagamento de serviço",
  "transferDate": "2026-06-21T18:30:00",
  "amount": 150.0
}
```

## Erros comuns

- Se receber `403`, verifique se o token foi enviado e se o usuário tem permissão.
- Se receber `401`, o token pode estar ausente ou expirado.
- Se receber `400`, revise os campos obrigatórios do corpo da requisição.