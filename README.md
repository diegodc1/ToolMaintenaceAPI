
 #  🛠️ Tool Maintenace API 🔨

 
 ![lastcommit](https://img.shields.io/github/last-commit/diegodc1/ToolMaintenaceAPI?style=flat-square)
 ![license](https://img.shields.io/github/license/diegodc1/ToolMaintenaceAPI?style=flat-square)
 
 ## 💻 Sobre o Projeto
O objetivo desta API é auxiliar no controle de ordens de serviços de manutenção de equipamentos. Com ela é possível realizar cadastro e gerenciamento de clientes, equipamentos e ordens de serviços. 
Para o desenvolvimento desta API foi utilizado Java, Spring Boot, SpringMVC, JPA e Hibernate. Para o armazenamento de dados foi utilizado o H2 database.

## ⚙️ Tecnologias

Esse projeto foi desenvolvido com as seguintes tecnologias:
 
  + Java
  + Spring / Spring Boot / Spring Data
  + JPA / Hibernate 
  + Maven

<table>
  <tr>
    <td>Java</td>
    <td>Spring</td>
  </tr>
  
   <tr>
    <td>19</td>
    <td>3.0.2</td>
  </tr>
</table>


## 🏁 Como Executar
Execute a API com o comando Maven:
```bash
  ./mvnw spring-boot:run 
```

## 🧪 Testes
Execute os teste atráves do comando:
```bash
   ./mvnw test
```

## 🎯 Endpoints

<details>
	<summary>Clientes</summary>
  
  #### 🟩 Adicionar Cliente
  
  ```bash
   http://localhost:8080/clientes/new
  ```
	
  ##### Modelo Post Json
  ```json
  {
     "nome": "Diego",  <String>
     "email": "diego@gmail.com",  <String>
     "endereco": "Rua das Palmeira",  <String>
     "telefone": "41999999999"  <String>
  }
  ```
	
  ***
  
  #### 🟩 Listar todos os clientes cadastrados
  
  ```bash
    http://localhost:8080/clientes
  ```
	
  ##### Resposta:
  ```json
  [
      {
          "id": 1,
          "nome": "Diego",
          "endereco": "Rua Paulo Lucio",
          "telefone": "41997017892",
          "email": "diegoalvesdc12@gmail.com"
      },
      {
          "id": 2,
          "nome": "Luis",
          "endereco": "Rua das Laranja",
          "telefone": "419964654334",
          "email": "luisinho@gmail.com"
      }
  ]
  ```
  
  ***
  
  #### 🟩 Buscar cliente pelo ID
  
  ```bash
    http://localhost:8080/clientes/<clienteId>
  ```
	
  ##### Resposta [clienteId = 1]:
  ```json
  {
    "id": 1,
    "nome": "Diego",
    "endereco": "Rua Paulo Lucio",
    "telefone": "41999999999",
    "email": "diegoalvesdc12@gmail.com"
  }
  ```
  
    
  ***
  
  #### 🟩 Buscar cliente pelo EMAIL
  
  ```bash
    http://localhost:8080/clientes/buscaemail/<clienteEmail>
  ```
	
  ##### Resposta [clienteEmail = diegoalvesdc12@gmail.com]:
  ```json
  {
    "id": 1,
    "nome": "Diego",
    "endereco": "Rua Paulo Lucio",
    "telefone": "41999999999",
    "email": "diegoalvesdc12@gmail.com"
  }
  ```
  
   ***
  
  #### 🟩 Buscar cliente pelo NOME
  
  ```bash
    http://localhost:8080/clientes/buscanome/<clienteNome>
  ```
	
  ##### Resposta [clienteNome = Diego]:
  ```json
  {
    "id": 1,
    "nome": "Diego",
    "endereco": "Rua Paulo Lucio",
    "telefone": "41999999999",
    "email": "diegoalvesdc12@gmail.com"
  }
  ```
</details>
  
  
  
<details>
	<summary>Equipamentos</summary>
  
  #### 🟨 Adicionar Equipamento
  
  ```bash
   http://localhost:8080/equipamentos/new
  ```
	
  ##### Modelo Post Json
  ```json
  {
    "tipo": "Impressora",   <String>
    "marca": "Positivo",    <String>
    "problema": "Está superaquecendo"  <String>
   }
  ```
  
  ***
  
  #### 🟨 Listar todos os equipamentos cadastrados
  
  ```bash
    http://localhost:8080/equipamentos
  ```
	
  ##### Resposta:
  ```json
 [
    {
        "id": 1,
        "tipo": "Impressora",
        "marca": "HP",
        "problema": "Não liga"
    },
    {
        "id": 2,
        "tipo": "Ar Condicionado",
        "marca": "York",
        "problema": "Não está gelando"
    }
  ]
  ```
  
    
  ***
  
  #### 🟨 Buscar um equipamento pelo ID
  
  ```bash
    http://localhost:8080/equipamentos/<equipamentoID>
  ```
	
  ##### Resposta [equipamentoID = 1]:
  ```json
  {
      "id": 1,
      "tipo": "Impressora",
      "marca": "HP",
      "problema": "Não liga"
  }
  ```
  
    
  ***
  
  #### 🟨 Buscar equipamentos pelo TIPO
  
  ```bash
    http://localhost:8080/equipamentos/buscatipo/<nomeTipo> 
  ```
	
  ##### Resposta [nomeTipo = Impressora]:
  ```json
  [
    {
        "id": 1,
        "tipo": "Impressora",
        "marca": "HP",
        "problema": "Não liga"
    },
    {
        "id": 3,
        "tipo": "Impressora",
        "marca": "Positivo",
        "problema": "Está superaquecendo"
    }
  ]
  ```
  
      
  ***
  
  #### 🟨 Buscar equipamentos pela MARCA
  
  ```bash
    http://localhost:8080/equipamentos/buscamarca/<equipamentoMarca>
  ```
	
  ##### Resposta [equipamentoMarca = York]:
  ```json
  [
    {
        "id": 2,
        "tipo": "Ar Condicionado",
        "marca": "York",
        "problema": "Não está gelando"
    }
  ]
  ```
</details>
  
<details>
  <summary>Serviços</summary>
  
  #### 🟦 Adicionar Ordem de Serviço
  
  ```bash
   http://localhost:8080/servicos/new
  ```
	
  ##### Modelo Post Json
  ```json
  {
    "cliente": {
        "id": 1  <Integer>
    },
    "equipamento": {
        "id": 2  <Integer>
    }
  }
  ```
  
  ***
  
    
  #### 🟦 Iniciar uma Ordem de Serviço
  
  ```bash
   http://localhost:8080/servicos/iniciar/<servicoID>
  ```
  
  ##### Resposta [servicoID = 1]:
  ```text
     Ordem de serviço iniciada!
  ```
  
  ***  
  
  #### 🟦 Finalizar uma Ordem de Serviço
  
  ```bash
   http://localhost:8080/servicos/finalizar/<servicoID>
  ```
  
  ##### Resposta [servicoID = 1]:
  ```text
     Ordem de serviço finalizada!
  ```
  
  *** 
  
  #### 🟦 Pausar uma Ordem de Serviço
  
  ```bash
  http://localhost:8080/servicos/pausar/<servicoID>
  ```
  
  ##### Modelo Post Json:
  ```json
    {
      "notas": "falta de peças"
    }
  ```
  
  ##### Resposta [servicoID = 1]:
  ```text
     Ordem de serviço pausada!
  ```
  
  ***
  
  #### 🟦 Cancelar uma Ordem de Serviço
  
  ```bash
  http://localhost:8080/servicos/cancelar/<servicoID>
  ```
  
  ##### Resposta [servicoID = 1]:
  ```text
     Ordem de serviço cancelada!
  ```
  
  ***
  
  #### 🟦 Listar todas as Ordens de Seriços
  
  ```bash
  http://localhost:8080/servicos
  ```
  
  ##### Resposta:
  ```json
  [
    {
        "status": "Cancelada",
        "inicio": "06/03/2023",
        "termino": "06/03/2023",
        "detalhes": "Foi feita a troca da peça defeituosa",
        "notas": "",
        "cliente": {
            "id": 1,
            "nome": "Diego",
            "endereco": "Rua Paulo Lucio",
            "telefone": "41997017892",
            "email": "diegoalvesdc12@gmail.com"
        },
        "equipamento": {
            "id": 2,
            "tipo": "Ar Condicionado",
            "marca": "York",
            "problema": "Não está gelando"
        },
        "id": 1
    },
    {
        "status": "Pendente",
        "inicio": null,
        "termino": null,
        "detalhes": null,
        "notas": null,
        "cliente": {
            "id": 2,
            "nome": "Luis",
            "endereco": "Rua das Laranja",
            "telefone": "419964654334",
            "email": "luisinho@gmail.com"
        },
        "equipamento": {
            "id": 1,
            "tipo": "Impressora",
            "marca": "HP",
            "problema": "Não liga"
        },
        "id": 2
    }
  ]
  ```
  ***
  
  #### 🟦 Buscar uma ordem de serviço pelo ID
  
  ```bash
    http://localhost:8080/servicos/<servicoID>
  ```
  
  ##### Resposta [servicoID = 1]:
  ```json
  {
    "status": "Cancelada",
    "inicio": "06/03/2023",
    "termino": "06/03/2023",
    "detalhes": "Foi feita a troca da peça defeituosa",
    "notas": "",
    "cliente": {
        "id": 1,
        "nome": "Diego",
        "endereco": "Rua Paulo Lucio",
        "telefone": "41997017892",
        "email": "diegoalvesdc12@gmail.com"
    },
    "equipamento": {
        "id": 2,
        "tipo": "Ar Condicionado",
        "marca": "York",
        "problema": "Não está gelando"
    },
    "id": 1
  }
  ```
  ***
  
  #### 🟦 Listar todas as Ordens de serviços pendentes
  
  ```bash
    http://localhost:8080/servicos/pendentes
  ```
  
  ##### Resposta:
  ```json
  [
    {
        "status": "Pendente",
        "inicio": null,
        "termino": null,
        "detalhes": null,
        "notas": null,
        "cliente": {
            "id": 2,
            "nome": "Luis",
            "endereco": "Rua das Laranja",
            "telefone": "419964654334",
            "email": "luisinho@gmail.com"
        },
        "equipamento": {
            "id": 1,
            "tipo": "Impressora",
            "marca": "HP",
            "problema": "Não liga"
        },
        "id": 2
    }
  ]
  ```
  ***
  
  #### 🟦 Listar todas as Ordens de Serviços Ativos
  
  ```bash
    http://localhost:8080/servicos/ativos
  ```
  
  ##### Resposta:
  ```json
    {
        "status": "Ativo",
        "inicio": "06/03/2023",
        "termino": "null",
        "detalhes": "null",
        "notas": "null",
        "cliente": {
            "id": 1,
            "nome": "Diego",
            "endereco": "Rua Paulo Lucio",
            "telefone": "41997017892",
            "email": "diegoalvesdc12@gmail.com"
        },
        "equipamento": {
            "id": 2,
            "tipo": "Ar Condicionado",
            "marca": "York",
            "problema": "Não está gelando"
        },
        "id": 1
    }
]
  ```
  ***
  
  #### 🟦 Listar todas as Ordens de Serviços Pausadas
  
  ```bash
    http://localhost:8080/servicos/pausados
  ```
  
  ##### Resposta:
  ```json
  [
      {
          "status": "Pausado",
          "inicio": "06/03/2023",
          "termino": "null",
          "detalhes": "null",
          "notas": "falta de peças",
          "cliente": {
              "id": 1,
              "nome": "Diego",
              "endereco": "Rua Paulo Lucio",
              "telefone": "41997017892",
              "email": "diegoalvesdc12@gmail.com"
          },
          "equipamento": {
              "id": 2,
              "tipo": "Ar Condicionado",
              "marca": "York",
              "problema": "Não está gelando"
          },
          "id": 1
      }
  ]
  ```
  ***
  
  #### 🟦 Listar todas as Ordens de Serviços Finalizadas
  
  ```bash
    http://localhost:8080/servicos/finalizados
  ```
  
  ##### Resposta:
  ```json
 [
    {
        "status": "Finalizado",
        "inicio": "06/03/2023",
        "termino": "06/03/2023",
        "detalhes": "Foi feita a troca da peça defeituosa",
        "notas": "null",
        "cliente": {
            "id": 1,
            "nome": "Diego",
            "endereco": "Rua Paulo Lucio",
            "telefone": "41997017892",
            "email": "diegoalvesdc12@gmail.com"
        },
        "equipamento": {
            "id": 2,
            "tipo": "Ar Condicionado",
            "marca": "York",
            "problema": "Não está gelando"
        },
        "id": 1
    }
  ]
  ```
  ***
  
   #### 🟦 Atualizar uma ordem de serviço
  
  ```bash
    http://localhost:8080/servicos/update/<servicoID>
  ```
  
  ##### Modelo Put Json:
  ```json
   {
    "status": "Ativo",
    "inicio": "25/04/23",
    "termino": "31/05/23",
    "detalhes": "Foi trocado o filtro",
    "notas": "Nada novo",
    "cliente": {
        "id": 1
    },
    "equipamento": {
        "id": 1
    }
  }
  ```
  
  
  ##### Resposta:
  ```text
    Ordem de serviço atualizada com sucesso!
  ```
  ***
</details>
  
## 📑 Licença
Licenciado sob os termos da licença MIT. Veja [LICENSE](https://github.com/diegodc1/ToolMaintenaceAPI/blob/main/LICENSE) para mais informações.
	
	
## 👨‍💻 Autor
✔️ Diego Alves
	
[![gmail](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:diegoalvesdc12@gmail.com "diegoalvesdc12@gmail.com")
[![github](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/diegodc1 "diegodc1")
[![linkedin](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/diego-alves-5198671bb/ "Diego Alves")
	
		
	
	
	
		
