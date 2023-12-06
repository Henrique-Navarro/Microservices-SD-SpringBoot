# Projeto de Sistema Distribuído com Microsserviços

Este é um projeto prático da disciplina de Sistemas Distribuídos, onde foi desenvolvido um sistema distribuído utilizando microsserviços baseados na arquitetura Spring Boot, RabbitMQ, Docker e Postgres.

## Tecnologias Utilizadas

- **Spring Boot**: Framework utilizado para desenvolvimento dos microsserviços.
- **RabbitMQ**: Message broker para comunicação entre os microsserviços, seguindo o padrão publish-subscribe.
- **Docker**: Utilizado para facilitar a execução e gerenciamento dos microsserviços e do banco de dados.
- **PostgreSQL**: Sistema de gerenciamento de banco de dados utilizado para cada microsserviço.

## Sistema de Mensageria Assíncrona

O sistema de mensageria assíncrona foi implementado utilizando o RabbitMQ como message broker. Ele permite a troca de mensagens entre os microsserviços de forma eficiente e assíncrona, seguindo o padrão publish-subscribe. Isso aumenta a tolerância a falhas e permite uma comunicação mais escalável entre os componentes do sistema.

## Rodando o Projeto em Outro Computador

Para executar o projeto em outro computador, siga os passos abaixo:

### Pré-requisitos

- Certifique-se de ter o Docker instalado no seu computador.

### Passos

1. Clone este repositório para o seu computador.
2. Abra um terminal e navegue até o diretório raiz do projeto.
3. Para cada um dos microsserviços, execute os seguintes comandos:

   ```bash
   cd nome_do_microservico
   docker-compose up -d
