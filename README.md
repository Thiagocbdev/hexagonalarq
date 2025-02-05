# hexagonalarq
Desafio Back-end  

 

Thiago Cavalcanti Batista 

 

1 Escopo: 

Microserviços distribudos no intuito de mostrar o fluxo e comunicação entre as Api´s. 

Os microserviços comunican-se via mensageria, Kafka, possuindo  persistência com Mongo Db, Postgres e Cache com Redis. 

Tecnologias Utilizadas 

Java 17 

Spring Boot 

Spring Cache (para caching de dados) 

Spring Scheduler (para execução periódica da tarefa) 

Spring Data JPA (para persistência de dados) 

MapStruct (para mapeamento de DTOs) 

Lombok (para redução de boilerplate no código) 

Banco de Dados: PostgreSQL e MongoDB 

Apache Kafka (para comunicação assíncrona entre microserviços) 

Spring Cloud OpenFeign (para comunicação entre serviços REST) 

Arquitetura 

O fluxo de dados ocorre da seguinte forma: 

O microserviço BFF recebe um objeto e o salva no MongoDB. 

Em seguida, ele envia o dado via Kafka para o microserviço MongoDB. 

O microserviço MongoDB salva o dado no banco MongoDB e expõe um endpoint REST. 

O microserviço Postgres consome esse dado via OpenFeign, processa e armazena no PostgreSQL. 

O dado salvo no PostgreSQL é armazenado em cache para otimizar futuras consultas. 

Considerações Finais 

O cache impede requisições desnecessárias e melhora a performance. 

O agendamento (a cada 30 segundos) garante que o banco de dados esteja sempre atualizado. 

A comunicação assíncrona via Kafka melhora a escalabilidade e desacoplamento entre microserviços. 

O uso de logs permite acompanhar o processamento e detectar possíveis erros rapidamente. 


```mermaid
graph LR
    A[Cliente] --> B(BFF)
    B -- Objeto --> C{MongoDB}
    C -- Kafka --> D[Microserviço MongoDB]
    D -- Salva no MongoDB --> E[MongoDB]
    E -- REST Endpoint --> F[Microserviço Postgres]
    F -- OpenFeign --> E
    F -- Processa e salva no Postgres --> G[PostgreSQL]
    G -- Dados --> H[Cache (Redis)]
    H -- Consultas --> I[Cliente]
    G -- Agendamento (30s) --> G
    subgraph "Tecnologias"
        J[Java 17]
        K[Spring Boot]
        L[Spring Cache]
        M[Spring Scheduler]
        N[Spring Data JPA]
        O[MapStruct]
        P[Lombok]
        Q[PostgreSQL]
        R[MongoDB]
        S[Kafka]
        T[OpenFeign]
        J --> K
        K --> L
        K --> M
        K --> N
        K --> O
        K --> P
        K --> T
    end
    style C fill:#ccf,stroke:#888,stroke-width:2px
    style D fill:#ccf,stroke:#888,stroke-width:2px
    style E fill:#ccf,stroke:#888,stroke-width:2px
    style F fill:#ccf,stroke:#888,stroke-width:2px
    style G fill:#ccf,stroke:#888,stroke-width:2px
    style H fill:#ccf,stroke:#888,stroke-width:2px