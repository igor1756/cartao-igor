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

- ✅ Cadastrar clientes
- ✅ Consultar cliente por ID
- ✅ Consultar cliente por CPF
- ✅ Consultar cliente por email
- ✅ Listagem de todos os clientes
- ✅ Exclusão de clientes
- 💳 Cadastrar cartão de crédito
- 💳 Consultar cartão de crédito por ID
- 💳 Listagem de todos os cartões de crédito
- 💳 Exclusão de cartões de crédito
- 📄 Firmar contrato
- 📄 Consultar contrato por ID
- 📄 Listagem de todos os contratos
- 📄 Consultar contrato por ID do cliente
- 📄 Encerrar contrato
- 📄 Exclusão de contrato


## 📊 Modelo de Dados (atributos)

### Cliente
- `nome` (String) - Nome do cliente
- `cpf` (String) - CPF do cliente (único)
- `email` (String) - Email do cliente (único)
- `dataNascimento` (LocalDate) - Data de nascimento do cliente

### Cartão de Crédito
- `nome` (String) - Nome conforme escrito no cartão
- `tipo` (String) - Crédito ou débito
- `anuidade` (float) - Valor da anuidade do cartão
- `bandeira` (String) - Elo, Visa, Mastercard, etc.

### Contrato
- `Status` (String) - Ativo ou cancelado
- `dataInicio` (LocalDate) - Data de início do contrato
- `dataFim` (LocalDate) - Data de fim do contrato
- `cliente` (Cliente) - Cliente associado ao contrato
- `cartaoCredito` (Cartão de Crédito) - Cartão de crédito associado ao contrato


## 🔗 Relacionamentos

- Um cliente firma no mínimo nenhum e no máximo muitos contratos.
- Um contrato é firmado por no mínimo um e no máximo um cliente.
- Um contrato está relacionado a no mínimo um e no máximo um cartão.
- Um cartão possui no mínimo nenhum e no máximo muitos contratos.


## 🛠️ Instalação e Configuração

### Pré-requisitos
- Java 21
- Maven 3.6+
- Banco de dados: H2 (desenvolvimento) e PostgreSQL (produção)
- Git instalado e configurado

### Passos para execução

1. **Clone o repositório**
```bash
git clone <url-do-repositorio>
cd cartaoIgor
```
OBS: A url dependerá do tipo de conexão do seu ambiente local (SSH ou HTTPS).

2. **Configure o banco de dados**

O projeto utiliza perfis do Spring Boot para gerenciar configurações específicas de ambiente. 

Para desenvolvimento, o projeto já vem configurado para utilizar o H2, sem a necessidade de configuração adicional.

`Atenção! Os dados inseridos em ambiente de desenvolvimento são descartados assim que a aplicação é encerrada e servem apenas para desenvolvimento e testes. Para persistência perene, deve-se configurar o ambiente de produção, cuja configuração é sugerida em sessão própria nesta documentação.`

Para produção, edite o arquivo `src/main/resources/application.properties`, alterando o 'spring.profiles.active=dev' para 'spring.profiles.active=prod' e adicione as configurações do banco de dados desejado.

3. **Execute a aplicação**
```bash
mvn spring-boot:run
```

4. **Acesse a base de dados da aplicação**

- Console H2 (se habilitado): `http://localhost:8080/h2-console`

<img width="467" height="378" alt="image" src="https://github.com/user-attachments/assets/6f070315-bb6c-4594-8c61-f7f360f2f26d" />

Confira o nome da base de dados e clique em "Connect".
Obs: Não é necessário preencher senha e o usuário é "sa".

5. **Acesse a documentação da API**

- URL: `http://localhost:8080/swagger-ui.html`



## 📚 API Endpoints

### Clientes

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/clientes` | Lista todos os clientes |
| GET | `/api/clientes/{id}` | Busca cliente por ID |
| GET | `/api/clientes/cpf/{cpf}` | Busca cliente por CPF |
| GET | `/api/clientes/email/{email}` | Busca cliente por email |
| POST | `/api/clientes` | Cadastra novo cliente |
| DELETE | `/api/clientes/{id}` | Remove cliente |

### Cartões

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/cartoes` | Lista todos os cartões |
| GET | `/api/cartoes/{id}` | Busca cartão por ID |
| POST | `/api/cartoes` | Cadastra novo cartão |
| DELETE | `/api/cartoes/{id}` | Remove cartão |

### Contratos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/contratos` | Lista todos os contratos |
| GET | `/api/contratos/{id}` | Busca contrato por ID |
| GET | `/api/contratos/cliente/{clienteId}` | Busca contrato por ID do cliente |
| POST | `/api/contratos` | Firma novo contrato |
| PUT | `/api/contratos/{id}/encerrar` | Encerra contrato |
| DELETE | `/api/contratos/{id}` | Remove contrato |


### Exemplo de uso via terminal

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

**O uso das demais funcionalidades podem ser consultadas e executadas diretamente na documentação da API no Swagger.**

```
http://localhost:8080/swagger-ui.html
```

## 📝 Validações de criação

Cliente:
- CPF deve ser único e válido
- Email deve ser único e ter formato válido
- Nome é obrigatório
- Data de nascimento deve seguir o padrão YYYY-MM-DD

Cartão:
- Nome do cartão é obrigatório e tem tamanho máximo de 100 caracteres;
- Tipo do cartão deve ser 'Debito' ou 'Credito';
- Anuidade é um valor decimal, obrigatório e deve ser maior que zero;
- Bandeira é obrigatória e tem tamanho máximo de 50 caracteres.

Contrato:
- O id do cliente e do cartão são obrigatórios.


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
