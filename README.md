# Banco Digital

Portal de entrada da documentação do projeto.

## O que você encontra aqui

- visão geral da aplicação
- como executar o projeto
- links para a documentação detalhada por área
- imagens dos fluxos de usuário, login, conta e transferência
- verificação automatizada de integração contínua

## Comece por aqui

- [Documentação detalhada](doc/README.md)
- [Galeria de imagens](doc/imgs/README.md)
- [Testes sequenciais](doc/tests-sequenciais.md)

## Integração contínua

- O projeto possui workflow em [.github/workflows/ci.yml](.github/workflows/ci.yml) com validação, compilação e testes.
- Os testes locais usam profile `test` com H2 e a aplicação fora do Docker pode subir com H2 como fallback se não houver PostgreSQL configurado.

## Resumo rápido

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

Se a porta 8080 já estiver em uso no seu ambiente, rode com outra porta:

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```

## Mapa da documentação

- [Visão geral da aplicação](doc/README.md#visao-geral)
- [Segurança JWT](doc/README.md#seguranca)
- [Usuários](doc/README.md#usuarios)
- [Login](doc/README.md#login)
- [Contas](doc/README.md#contas)
- [Imagens do fluxo](doc/imgs/README.md)
- [Testes sequenciais](doc/tests-sequenciais.md)

## Observações

- A senha é armazenada com hash automaticamente.
- O token JWT expira conforme `JWT_EXPIRATION_MS`.
- Os dados sensíveis são mascarados nas respostas de saída.
- O projeto usa PostgreSQL no Docker e pode usar H2 localmente quando não houver banco configurado.
