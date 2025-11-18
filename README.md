## Guia Rápido para Rodar o Projeto Spring Boot

### 1. Requisitos

Antes de iniciar, certifique-se de que os seguintes softwares estão instalados:

* **Docker Desktop** (em execução)
* **Java 21** (verifique com `java -version`)
* **Maven** (verifique com `mvn -version`)

---

### 2. Subindo o Banco de Dados PostgreSQL

Com o Docker Desktop rodando, execute o comando abaixo para criar e iniciar o container do PostgreSQL:

```bash
docker run --name postgres-earthbnb -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=earthbnbDB -p 5432:5432 -d postgres
```

Esse comando cria um container chamado **postgres-earthbnb** com:

* Usuário: `admin`
* Senha: `admin`
* Banco: `earthbnbDB`

Para verificar se o container está ativo:

```bash
docker ps
```

---

### 3. Rodando o Projeto

No diretório raiz do projeto, execute:

```bash
mvn clean install -DskipTests
```
Após, execute:
```bash
mvn spring-boot:run
```

Ou, caso o projeto já tenha sido empacotado:

```bash
java -jar target/nome-do-arquivo.jar
```

---

### 4. Acessando a API

Após iniciar o servidor, acesse:

```
http://localhost:8080
```
