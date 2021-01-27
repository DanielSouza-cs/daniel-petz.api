# petz.api

  Author: Daniel Souza de Oliveira
  Tech Stack: Java 8, Docker, Maven, H2-Databse  
  Tipo: API  
  Target: Docker-Container
  
# Instruções Gerais 
    Os comandos apresentados são necessários para compilar o projeto e preparar/executar a imagem Docker
    em ambiente de desenvolvimento
    Todos os comando devem ser executados via terminal diretamente no diretório raiz deste projeto.

# 1- build e criação da imagem docker
    mvn spring-boot:build-image
    
# 2- criar container da aplicação com base na imagem docker criada no passo 1
# e executar o container
    docker run -p 8080:8080 id_imagem_Docker(IMAGE ID gerado)
# 3- É possivel acessar a aplicação no PATH: http://localhost:8080/swagger-ui.html

# 4- A documentação da API, esta disponivel no seguinte PATH da aplicação: http://localhost:8080/swagger-ui.html
# a documentação foi desenvolvida inteira com o SWAGGER, nela esta o manual de como usar a API e fazer as requisições 

# 5- BANCO DE DADOS: o banco de dados utilizado foi o H2 DATABSE, um banco de dados relacional  
# em memória(parou a aplicação apaga os dados)que pode ser acessado pelo BROWSER através do PATH: http://localhost:8080/h2-console
    JDBC URL: jdbc:h2:mem:testdb
    User Name: sa
    Password : password
#Colocando os dados acima é possive acessar a interface do H2 e ver as tabelas com os devidos registros.
    


# Estrutura dos pacotes de fontes da aplicação
 
 Abaixo segue um resumo da estrutura dos fontes nesta aplicação.
├── main
│   ├── java
│   │   └── com
│   │       └── petz
│   │           └── api
│   │               ├── Application.java
│   │               ├── config
│   │               │   └── SwaggerConfig.java
│   │               ├── controller
│   │               │   ├── ClienteController.java
│   │               │   └── PetController.java
│   │               ├── dto
│   │               │   ├── ClienteEditDTO.java
│   │               │   └── PetSalvarDTO.java
│   │               ├── entity
│   │               │   ├── Cliente.java
│   │               │   └── Pet.java
│   │               └── repository
│   │                   ├── ClienteRepository.java
│   │                   └── PetRepository.java
│   └── resources
│       └── application.properties
└── test
    └── java
        └── com
            └── petz
                └── api
                    └── ApplicationTests.java
