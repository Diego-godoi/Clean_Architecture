## 📦 Clean Architecture — Java + Spring Boot

Este projeto foi desenvolvido com o objetivo de estudar e aplicar os princípios da **Clean Architecture**, organizando o código em camadas independentes e garantindo fácil manutenção, escalabilidade e testabilidade.

---

## 🏗 Tecnologias utilizadas

* **Java 21 (JDK 21)**
* **Spring Boot**
* **Maven**
* **PostgreSQL**
* **Docker**
* **Clean Architecture**
* **IntelliJ IDEA**

---

## 📁 Estrutura do Projeto

```
src/
 ├─ application/      → Casos de uso (regras de aplicação)
 ├─ core/           → Entidades e regras de negócio
 ├─ infra/   →  repositórios, config, persistence
 └─ adapter -> Controllers
```

* O **core** é totalmente independente de framework.
* O **application** contém regras específicas de aplicação.
* O **infra** contém detalhes externos (Spring, banco, etc).

---

# ▶ Como rodar o projeto

## 1️⃣ Clonar o repositório

```sh
git clone https://github.com/Diego-godoi/Clean_Architecture.git
cd Clean_Architecture
```

---

## 2️⃣ Criar arquivo `.env`

Crie um arquivo **.env** na raiz do projeto com o seguinte modelo:

```env
POSTGRES_DB=
POSTGRES_USER=
POSTGRES_PASSWORD=
POSTGRES_PORT=
POSTGRES_HOST=
```

A aplicação já está configurada para carregar essas variáveis automaticamente.

---

# ▶ Rodando a API

## 3️⃣ Instalar dependências

```sh
mvn clean install -DskipTests
```

## 4️⃣ Executar

```sh
mvn spring-boot:run
```

A API iniciará em:

```
http://localhost:8080
```

---

# 📡 Endpoints

Teste na rota principal: **http://localhost:8080/users**
Ainda **não há documentação Swagger** no projeto.
Você pode testar os endpoints usando:

* Postman
* Thunder Client
* cURL
* Insomnia

Se quiser, posso adicionar Swagger no seu projeto depois.

---

# 🤝 Contribuição

Sinta-se à vontade para abrir PR ou issues com sugestões e melhorias.

---

# 📄 Licença

Este projeto está sob a licença MIT.

---
