# Projeto de Plataforma de Vídeos (Estilo YouTube)

## 📑 Sobre o Projeto

O projeto foi inspirado no projeto da última aula do curso de POO do Gustavo Guanabara, onde junta tudo o que foi aprendido durante o curso.
Tendo o projeto como base, decidi ir um pouco mais além, e implementar interação via console, e ir um pouco mais além, e implementar uma CRUD completa e também um Banco de Dados.

O projeto a nével console, desenvolvido em Java que simula as funcionalidades básicas de uma plataforma de compartilhamento de vídeos. 
A aplicação permite o cadastro de usuários (chamados de "Gafanhotos") e vídeos, além de registrar as interações entre eles, como visualizações, avaliações de nota, likes e dislikes.

O principal objetivo foi aplicar e consolidar conhecimentos em Programação Orientada a Objetos, manipulação de banco de dados com JDBC e a utilização de padrões de projeto como o DAO (Data Access Object).

---

## ✨ Funcionalidades

- **Cadastro de Usuários:** Permite registrar novos usuários (Gafanhotos) no sistema.
- **Cadastro de Vídeos:** Permite adicionar novos vídeos à plataforma.
- **Consulta de Dados:** Visualização das informações de usuários e vídeos cadastrados.
- **Interação com Vídeos:**
    - Contabilização de visualizações.
    - Sistema de avaliação por nota (de 1 a 5), com cálculo de média.
    - Sistema de Like e Dislike, permitindo que o usuário mude sua opinião.
- **Persistência de Dados:** Todas as informações são salvas e gerenciadas em um banco de dados MySQL.

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 21
- **Banco de Dados:** MySQL
- **Conectividade com BD:** JDBC (Java Database Connectivity)
- **Pool de Conexões:** HikariCP para gerenciamento eficiente das conexões com o banco de dados.
- **Dependências:**
    - `mysql-connector-j`
    - `slf4j-api`

---

## 🚀 Como Executar o Projeto

Para executar este projeto em sua máquina local, siga os passos abaixo.

### Pré-requisitos

- **Java (JDK 21)** ou superior instalado.
- **MySQL Server** instalado e em execução.
- Uma **IDE Java**, como Eclipse ou IntelliJ IDEA.

### 1. Configuração do Banco de Dados

Antes de rodar a aplicação, é necessário criar o banco de dados e as tabelas.

a. Crie um novo banco de dados (schema) no MySQL com o nome `aula15`.

b. Execute os seguintes scripts SQL para criar as tabelas `gafanhoto`, `video` e `interacao`:

```sql
CREATE TABLE gafanhoto (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  sexo CHAR(1),
  idade INT,
  login VARCHAR(20) UNIQUE,
  totassistido INT DEFAULT 0,
  experiencia INT DEFAULT 0
);

CREATE TABLE video (
  id INT PRIMARY KEY AUTO_INCREMENT,
  titulo VARCHAR(255) NOT NULL,
  avaliacao DOUBLE DEFAULT 0,
  views INT DEFAULT 0,
  gostei INT DEFAULT 0,
  naogostei INT DEFAULT 0,
  total_avaliacao INT DEFAULT 0
);

CREATE TABLE interacao (
  id INT PRIMARY KEY AUTO_INCREMENT,
  espectador_id INT,
  video_id INT,
  nota DOUBLE DEFAULT 0,
  estado_like INT DEFAULT 0, -- 0: Nenhum, 1: Like, 2: Dislike
  FOREIGN KEY (espectador_id) REFERENCES gafanhoto(id) ON DELETE CASCADE,
  FOREIGN KEY (video_id) REFERENCES video(id) ON DELETE CASCADE,
  UNIQUE (espectador_id, video_id)
);
