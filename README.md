# 🧱 BackMural – Backend do Mural Interativo

Este repositório contém o backend do projeto **Mural Interativo**, desenvolvido com **Java** e **Spring Boot**. Ele fornece uma API REST para gerenciar fotos, usuários e outras funcionalidades relacionadas ao mural.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security**
- **Docker**
- **Maven**

## 📁 Estrutura do Projeto

- `src/main/java`: Código-fonte principal da aplicação.
- `src/main/resources`: Arquivos de configuração e recursos.
- `Dockerfile`: Configuração para containerização da aplicação.
- `pom.xml`: Gerenciamento de dependências com Maven.

## 📡 Rotas da API

### 🔐 Autenticação
- `POST /auth` – Autentica um usuário e retorna o token JWT.

### 🖼️ Murais
- `GET /murais` – Lista todos os murais disponíveis.
- `GET /murais/{id}` – Retorna um mural específico.
- `POST /murais` – Cria um novo mural. 🔐
- `PUT /murais/{id}` – Atualiza as informações de um mural. 🔐
- `DELETE /murais/{id}` – Remove um mural existente. 🔐

### 📷 Imagens
- `GET /imagens/{muralId}` – Lista todas as imagens de um mural.
- `POST /imagens/{muralId}` – Envia uma nova imagem (Base64) para o mural selecionado. 🔐
- `DELETE /imagens/{muralId}/{id}` – Remove uma imagem do mural. 🔐
- `PUT /imagens/{imagemId}` - Atualiza a descrição da imagem. 🔐
