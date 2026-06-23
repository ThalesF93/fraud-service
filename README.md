# 🛡️ Fraud Service

Microsserviço de detecção e gerenciamento de fraudes desenvolvido em Java com Spring Boot, projetado para integração ao ecossistema **ByteBank**.

---

## 📌 Sobre o Projeto

O **Fraud Service** é responsável por analisar transações financeiras em busca de comportamentos suspeitos, gerando alertas de fraude com pontuação de risco e status de revisão. O serviço se comunica com outros microsserviços do ByteBank via mensageria (Kafka) e descoberta de serviços (Eureka), garantindo resiliência e escalabilidade.

> ⚠️ **Projeto em desenvolvimento inicial.** Novas funcionalidades serão adicionadas progressivamente.

---

## 🧰 Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 25 |
| Spring Boot | 4.1.0 |
| Spring Cloud | 2025.1.2 |
| Spring Data JPA | - |
| Spring Kafka | - |
| Spring Data Redis | - |
| Spring Web MVC | - |
| Resilience4j (Circuit Breaker) | - |
| Netflix Eureka Client | - |
| OpenFeign | - |
| PostgreSQL | - |
| Lombok | - |
| Gradle | - |

---

## 🏗️ Arquitetura

O serviço segue uma arquitetura baseada em **microsserviços**, com os seguintes componentes previstos:

- **REST API** — exposição de endpoints para consulta e gerenciamento de alertas
- **Kafka** — consumo de eventos de transações e publicação de alertas de fraude
- **Redis** — cache para regras de fraude e rate limiting
- **PostgreSQL** — persistência dos alertas gerados
- **Eureka Client** — registro e descoberta de serviços
- **Resilience4j** — Circuit Breaker para chamadas entre serviços
- **OpenFeign** — cliente HTTP declarativo para comunicação com outros microsserviços do ByteBank

---

## 📂 Estrutura do Projeto

```
fraud-service/
├── src/
│   ├── main/
│   │   ├── java/br/com/fraudservice/
│   │   │   ├── domain/
│   │   │   │   ├── entity/
│   │   │   │   │   └── FraudAlert.java       # Entidade principal de alerta de fraude
│   │   │   │   ├── enums/
│   │   │   │   │   ├── FraudScore.java       # Níveis de risco: LOW, MEDIUM, HIGH
│   │   │   │   │   └── FraudStatus.java      # Status do alerta
│   │   │   │   └── repository/
│   │   │   │       └── FraudRepository.java  # Interface de repositório
│   │   │   └── FraudServiceApplication.java  # Classe principal
│   │   └── resources/
│   │       └── application.yaml              # Configurações da aplicação
│   └── test/
│       └── java/br/com/fraudservice/
│           └── FraudServiceApplicationTests.java
├── build.gradle
├── settings.gradle
└── gradlew
```

---

## 🔎 Domínio

### FraudAlert

Entidade que representa um alerta de fraude gerado pelo serviço:

| Campo | Tipo | Descrição |
|---|---|---|
| `id` | UUID | Identificador único do alerta |
| `transactionId` | UUID | ID da transação analisada |
| `accountId` | UUID | ID da conta envolvida |
| `score` | FraudScore | Nível de risco detectado |
| `status` | FraudStatus | Status atual do alerta |
| `ruleTriggered` | String | Regra que disparou o alerta |
| `createdAt` | LocalDateTime | Data/hora de criação do alerta |

### FraudScore (Pontuação de Risco)

```java
LOW       // Risco baixo
MEDIUM    // Risco médio
HIGH      // Risco alto
```

### FraudStatus (Status do Alerta)

```java
CONFIRMED             // Fraude confirmada
ON_REVIEW             // Em análise
BLOCKED               // Conta/transação bloqueada
PENDING_AUTHORIZATION // Aguardando autorização do usuário
```

---

## ⚙️ Configuração e Execução

### Pré-requisitos

- Java 25+
- PostgreSQL rodando localmente ou via Docker
- Redis rodando localmente ou via Docker
- Kafka + Zookeeper rodando localmente ou via Docker
- Eureka Server (do ecossistema ByteBank) disponível

### Clonar o repositório

```bash
git clone https://github.com/ThalesF93/fraud-service.git
cd fraud-service
```

### Configurar variáveis de ambiente

Edite o arquivo `src/main/resources/application.yaml` com as configurações do seu ambiente (banco de dados, Kafka, Redis, Eureka).

### Executar a aplicação

```bash
./gradlew bootRun
```

### Executar os testes

```bash
./gradlew test
```

---

## 🔗 Integração com o ByteBank

O **Fraud Service** faz parte do ecossistema de microsserviços do **ByteBank** e se integra com:

- **Transaction Service** — recebe eventos de transações via Kafka para análise
- **Account Service** — consulta dados de contas via Feign Client
- **Eureka Server** — para registro e descoberta de serviços
- **API Gateway** — ponto de entrada unificado para os clientes

---

## 🚧 Roadmap

- [ ] Implementar consumers Kafka para receber eventos de transações
- [ ] Criar endpoints REST para consulta de alertas
- [ ] Implementar motor de regras de detecção de fraude
- [ ] Adicionar cache Redis para regras e listas de bloqueio
- [ ] Configurar Circuit Breaker com Resilience4j
- [ ] Configurar conexão com PostgreSQL e migrations
- [ ] Adicionar autenticação e autorização
- [ ] Implementar testes de integração
- [ ] Dockerizar o serviço

---

## 👤 Autor

**Thales Fernandes** — [@ThalesF93](https://github.com/ThalesF93)
