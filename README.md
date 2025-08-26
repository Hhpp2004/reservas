# Sistema de Reservas

Este projeto é uma API desenvolvida em Java utilizando o framework Spring Boot, destinada ao gerenciamento de reservas de mesas em estabelecimentos. O sistema oferece funcionalidades para cadastro de usuários, autenticação, gerenciamento de mesas e reservas, além de controle de acesso por perfis.

## Funcionalidades

- Cadastro e autenticação de usuários
- Gerenciamento de mesas (criação, atualização, exclusão)
- Realização, consulta e cancelamento de reservas
- Controle de acesso por perfil de usuário (admin, usuário comum)
- Tratamento de exceções e respostas padronizadas

## Estrutura do Projeto

```
src/
	main/
		java/
			system/
				backend/
					reservas/
						Config/         # Configurações de segurança e administração
						Controller/     # Controllers REST
						DTO/            # Data Transfer Objects
						Exception/      # Exceções customizadas
						Model/          # Modelos de dados
						Repository/     # Repositórios JPA
						Service/        # Lógica de negócio
		resources/
			application.properties # Configurações da aplicação
			app.key, app.pub       # Chaves para autenticação JWT
			static/, templates/    # Recursos estáticos e templates
test/
	java/
		system/
			backend/
				reservas/
					ReservasApplicationTests.java # Testes automatizados
```

## Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Security
- JPA / Hibernate
- JWT para autenticação
- Maven

## Como Executar

1. Clone o repositório:
   ```bash
   git clone <url-do-repositorio>
   cd reservas
   ```
2. Configure o banco de dados e as variáveis no `application.properties`.
3. Execute a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Acesse a API via `http://localhost:8080`.

## Testes

Para rodar os testes automatizados:

```bash
./mvnw test
```

## Contribuição

Contribuições são bem-vindas! Siga as boas práticas de desenvolvimento e abra um Pull Request com sua sugestão.

## Licença

Este projeto está sob a licença MIT.
