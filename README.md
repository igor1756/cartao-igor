# Projeto Cartão Igor

Um sistema de gerenciamento de clientes e cartões de crédito desenvolvido em Java com Spring Boot para o Tech Chalenge realizado na fase 2 do curso de pós-gradução DEV FOUNDATIONS da FIAP.

## 📋 Descrição

Este projeto é uma API para gerenciamento de clientes e cartões de crédito, permitindo operações CRUD (Create, Read, Update, Delete) com persistência de dados utilizando JPA/Hibernate.

## 🚀 Tecnologias Utilizadas

- **Java** - Linguagem de programação
- **Spring Boot** - Framework principal
- **Spring Data JPA** - Para persistência de dados
- **Hibernate** - ORM (Object-Relational Mapping)
- **Maven** - Gerenciamento de dependências
- **PostgreSQL** - Banco de dados produção
- **H2** - Banco de dados de desenvolvimento
- **Swagger** - Documentação da API

## 📁 Estrutura do Projeto

```

```

## 🔧 Funcionalidades

- ✅ Cadastro de clientes
- ✅ Consulta de clientes por ID
- ✅ Busca por CPF
- ✅ Busca por email
- ✅ Listagem de todos os clientes
- ✅ Atualização de dados do cliente
- ✅ Exclusão de clientes
- ✅ Validação de dados

## 📊 Modelo de Dados

### Cliente
- `id` (Long) - Identificador único
- `nome` (String) - Nome do cliente
- `cpf` (String) - CPF do cliente (único)
- `email` (String) - Email do cliente (único)
- `dataNascimento` (LocalDate) - Data de nascimento do cliente

### Cartão de Crédito
- `id` (Long) - Identificador único
- `nome` (String) - Nome??
- `tipo` (String) - Crédito ou débito
- `anuidade` (float) - Valor da anuidade do cartão
- `bandeira` (String) - Elo, Visa, Mastercard, etc.

### Contrato
- `id` (Long) - Identificador único
- `Status` (String) - Ativo ou cancelado
- `dataInicio` (LocalDate) - Data de início do contrato
- `dataFim` (LocalDate) - Data de fim do contrato
- `cliente` (Cliente) - Cliente associado ao contrato
- `cartaoCredito` (Cartão de Crédito) - Cartão de crédito associado ao contrato


## 🛠️ Instalação e Configuração

### Pré-requisitos
- Java 21
- Maven 3.6+
- Banco de dados: H2 (desenvolvimento) e PostgreSQL (produção)

### Passos para execução

1. **Clone o repositório**
```bash
git clone <url-do-repositorio>
cd cartaoIgor
```

2. **Configure o banco de dados**

O projeto utiliza perfis do Spring Boot para gerenciar configurações específicas de ambiente. 

Para desenvolvimento, o projeto já vem configurado para utilizar o H2, sem a necessidade de configuração adicional.

Para produção, edite o arquivo `src/main/resources/application.properties`, alterando o 'spring.profiles.active=dev' para 'spring.profiles.active=prod' e adicione as configurações do banco de dados desejado.

3. **Execute a aplicação**
```bash
mvn spring-boot:run
```

4. **Acesse a aplicação**
- URL: `http://localhost:8080/swagger-ui.html`
- Console H2 (se habilitado): `http://localhost:8080/h2-console`

<img width="467" height="378" alt="image" src="https://github.com/user-attachments/assets/6f070315-bb6c-4594-8c61-f7f360f2f26d" />

Confira o nome da base de dados e clique em "Connect".
Obs: Não é necessário preencher senha e o usuário é "sa".



## 📚 API Endpoints

### Clientes

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/clientes` | Lista todos os clientes |
| GET | `/api/clientes/{id}` | Busca cliente por ID |
| GET | `/api/clientes/cpf/{cpf}` | Busca cliente por CPF |
| GET | `/api/clientes/email/{email}` | Busca cliente por email |
| POST | `/api/clientes` | Cadastra novo cliente |
| PUT | `/api/clientes/{id}` | Atualiza cliente existente |
| DELETE | `/api/clientes/{id}` | Remove cliente |

### Exemplo de uso

**Cadastrar cliente:**
```bash
curl -X 'POST' \
  'http://localhost:8080/api/clientes' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "nome": "João da Silva",
  "cpf": "12345678901",
  "email": "joao@example.com",
  "dataNascimento": "1990-01-01"
}'
```

**Buscar cliente por CPF:**
```bash
curl http://localhost:8080/api/clientes/cpf/12345678901
```

## 🧪 Testes

Execute os testes unitários:
```bash
mvn test
```

Execute os testes de integração:
```bash
mvn verify
```

## 📝 Validações

Cliente:
- CPF deve ser único e válido
- Email deve ser único e ter formato válido
- Nome é obrigatório
- Data de nascimento deve seguir o padrão YYYY-MM-DD

Cartão:


## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

**Igor** - Desenvolvedor

---

⭐ Se este projeto foi útil para você, considere dar uma estrela!

---

## EXTRA:

## Configuração do Banco de Dados em Produção

Este projeto está configurado para utilizar um banco de dados em memória (H2) para **desenvolvimento**, simplificando o setup inicial para novos desenvolvedores. No entanto, para o ambiente de **produção**, é crucial utilizar uma solução de banco de dados persistente e robusta, como o **PostgreSQL**, que é o banco de dados pretendido para o projeto final.

Para configurar o banco de dados em produção, siga as orientações abaixo.

### 1\. Instalação e Configuração do PostgreSQL

Certifique-se de que o servidor PostgreSQL esteja instalado e em execução no seu ambiente de produção.

  * **Criação do Banco de Dados e Usuário Dedicado:**
    É fortemente recomendado criar um banco de dados e um usuário específico para esta aplicação, com as permissões mínimas necessárias. Substitua `your_database_name`, `your_username` e `your_password` pelos valores desejados:

    ```sql
    CREATE DATABASE your_database_name;
    CREATE USER your_username WITH PASSWORD 'your_password';
    GRANT ALL PRIVILEGES ON DATABASE your_database_name TO your_username;
    ```

  * **Configuração de Rede:**
    Verifique os arquivos de configuração do PostgreSQL (`pg_hba.conf` e `postgresql.conf`) para garantir que o servidor aceite conexões da máquina onde sua aplicação estará rodando.

### 2\. Configuração do Projeto para Produção

O projeto utiliza perfis do Spring Boot para gerenciar configurações específicas de ambiente. Para produção, usaremos o perfil `prod`.

  * **Arquivo de Configuração:**
    As configurações do PostgreSQL para produção estão no arquivo `src/main/resources/application-prod.properties`. Você precisará ajustar os valores de conexão conforme seu ambiente:

    ```properties
    spring.datasource.url=jdbc:postgresql://<SEU_HOST_POSTGRES>:5432/your_database_name
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    # Use "validate" ou "none" para o ddl-auto em produção,
    # e gerencie as migrações de esquema com ferramentas como Flyway ou Liquibase.
    spring.jpa.hibernate.ddl-auto=none
    spring.jpa.show-sql=false
    # Exemplo de configuração de pool de conexões (HikariCP é o padrão do Spring Boot)
    spring.datasource.hikari.maximum-pool-size=10
    spring.datasource.hikari.connection-timeout=30000
    ```

    **Lembre-se de substituir `<SEU_HOST_POSTGRES>` pelo endereço IP ou nome do host do seu servidor PostgreSQL.**

  * **Gerenciamento de Esquema (Recomendado):**
    Para produção, **NÃO use `spring.jpa.hibernate.ddl-auto=update`**. Isso pode causar perda de dados ou inconsistências. A abordagem recomendada é usar ferramentas de migração de esquema de banco de dados como **Flyway** ou **Liquibase**. Essas ferramentas permitem versionar suas alterações no esquema e aplicá-las de forma controlada.

    Se você decidir integrar Flyway (ou Liquibase), o projeto precisará da dependência apropriada e os scripts de migração deverão ser colocados em `src/main/resources/db/migration` (para Flyway).

  * **Segurança das Credenciais:**
    Nunca inclua credenciais de banco de dados diretamente em arquivos de configuração públicos em produção. Utilize **variáveis de ambiente** ou um serviço de gerenciamento de segredos (como HashiCorp Vault, AWS Secrets Manager, Azure Key Vault, etc.) para armazenar e acessar informações sensíveis de forma segura.

### 3\. Ativando o Perfil de Produção

Ao iniciar sua aplicação em produção, você deve ativar explicitamente o perfil `prod`.

  * **Ao Executar o JAR:**

    ```bash
    java -jar seu-aplicativo.jar --spring.profiles.active=prod
    ```

  * **Ao Implantar em um Contêiner (Docker, Kubernetes):**
    Defina a variável de ambiente `SPRING_PROFILES_ACTIVE` para `prod`.

    ```bash
    export SPRING_PROFILES_ACTIVE=prod
    java -jar seu-aplicativo.jar
    ```

-----
