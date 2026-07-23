# 🛒 E-commerce de Informática - API REST

Projeto desenvolvido durante o bootcamp **Generation Brasil - Desenvolvedor Full Stack Jr.**

O objetivo é criar uma API REST para gerenciamento de um sistema de **E-commerce de Informática**, aplicando conceitos de Programação Orientada a Objetos, ORM, relacionamento entre entidades e desenvolvimento Back-end com Spring Boot.

---

## 📌 Sobre o Projeto

A aplicação permite gerenciar:

- Clientes
- Pedidos

O relacionamento principal do sistema é:

Cliente 1 -------- N Pedido

Ou seja:

- Um Cliente pode possuir vários Pedidos.
- Um Pedido pertence a apenas um Cliente.

---

# 🚀 Tecnologias utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Git/GitHub
- Insomnia (testes da API)

---

# 🏗️ Arquitetura do Projeto

O projeto foi organizado seguindo uma separação de responsabilidades:

src/main/java/com/generation/ecommerce

├── controller
 │   ├── ClienteController
 │   └── PedidoController
 │
 ├── model
 │   ├── Cliente
 │   └── Pedido
 │
 ├── repository
 │   ├── ClienteRepository
 │   └── PedidoRepository
 │
 └── service
 ├── ClienteService
 └── PedidoService

---

# 🗄️ Modelo de Dados

## Cliente

A entidade Cliente possui:

| Campo | Tipo   |
| ----- | ------ |
| id    | Long   |
| nome  | String |
| email | String |

---

## Pedido

A entidade Pedido possui:

| Campo     | Tipo       |
| --------- | ---------- |
| id        | Long       |
| produto   | String     |
| descricao | String     |
| valor     | BigDecimal |
| data      | LocalDate  |
| cliente   | Cliente    |

---

# 🔗 Relacionamento entre entidades

Foi implementado o relacionamento:

### Cliente

```java
@OneToMany(mappedBy = "cliente")
private List<Pedido> pedidos;
```

### Pedido

```java
@ManyToOne
@JoinColumn(name = "cliente_id")
private Cliente cliente;
```

# 📚 Funcionalidades

## Cliente

### Listar clientes

```
GET /clientes
```

### Buscar cliente por ID

```
GET /clientes/{id}
```

### Criar cliente

```
POST /clientes
```

Exemplo:

```
{
    "nome": "Maria",
    "email": "maria@email.com"
}
```

------

### Atualizar cliente

```
PUT /clientes/{id}
```

------

### Deletar cliente

```
DELETE /clientes/{id}
```

------

# Pedido

### Listar pedidos

```
GET /pedidos
```

------

### Buscar pedido por ID

```
GET /pedidos/{id}
```

------

### Criar pedido

```
POST /pedidos
```

Exemplo:

```
{
    "produto": "Notebook Gamer",
    "descricao": "Notebook para jogos",
    "valor": 4500.00,
    "cliente": {
        "id": 1
    }
}
```

------

### Atualizar pedido

```
PUT /pedidos/{id}
```

------

### Deletar pedido

```
DELETE /pedidos/{id}
```

------

# 🔎 Pesquisa personalizada

Foi criado um endpoint para buscar pedidos pelo nome do cliente.

Exemplo:

```
GET /pedidos/pesquisa?cliente=Maria
```

Retorna todos os pedidos associados ao cliente informado.

Implementação utilizando Spring Data JPA:

```
List<Pedido> findAllByClienteNomeContainingIgnoreCase(String nome);
```

------

# ⚙️ Configuração do Banco de Dados

O projeto utiliza MySQL.

Exemplo de configuração:

```
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

------

# 🧪 Testes

Os endpoints foram testados utilizando:

- Insomnia

Foram realizados testes de:

✅ Cadastro
 ✅ Consulta
 ✅ Atualização
 ✅ Exclusão
 ✅ Pesquisa personalizada

------

# 👨‍💻 Desenvolvedor

Projeto desenvolvido por:

**Edson Silva**

Bootcamp:
 **Generation Brasil - Desenvolvedor Full Stack Jr**