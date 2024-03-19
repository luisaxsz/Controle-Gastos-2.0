<p>
  <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white"/>
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white"/>
  <img src="https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white"/>
  <img src="https://img.shields.io/badge/angular-%23DD0031.svg?style=for-the-badge&logo=angular&logoColor=white"/>
  <img src="https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white"/>
</p>

# Controle-Gastos-2.0
#### Estilização e testes unitários com JUnit e Mockito ainda em andamento.
<img alt="Static Badge" src="https://img.shields.io/badge/Springboot-BackEnd Finalizado-darkgreen">
<img alt="Static Badge" src="https://img.shields.io/badge/Angular-FrontEnd Finalizado-darkgreen">

Aplicação permite gerenciar gastos e lucros de uma determinada conta. A aplicação permite criar conta de usuario, atualizar a conta deletar e buscar conta por id.Também é possível adicionar,atualizar,consultar e deletar as transações de GASTO e Lucro.Toda vez que uma transação for adicionada ou modificada ao selecionar uma conta o total dessa conta vai ser modificado de acordo com a conta selecionada e escolhida pelo id.

## Features
- Criar conta
- Atualizar conta
- Consultar contas
- Deletar conta
- Login de conta
- Adicionar transação
- Atualizar transação
- Consultar transações
- Deletar transações
- Total atulizado toda vez em que transação ocorre

## Front-End(Angular)
A aplicação conecta ao front com o framework angular, você pode alterar atráves de formulários
os atributos de conta e transacões. Também inclui a api a possibilidade de cadastro e login. A verificação é 
feita pelo email e a senha é guardada criptografada. Você também pode consultar seus gastos/lucros assim que fizer login,
a lista de gasto e/ou lucro pode ser visitada apenas pelo responsável da conta. Todos os campos do formulário possuem verificação. O total continua sendo atualizado automaticamente.
O estilo da aplicação ainda está sendo ajustado e desenvolvido mas as funcionalidades estão todas funcionando.Utilizei do curso de formulários
orientados a templates da alura para desenvolver essa parte do projeto.
  
### Buscar Contas Existentes
``GET /contas``

```json 
  [{
    "id":2,
    "nome":"Maria",
    "sobrenome":"Sousa",
    "total":102.00},
  {
    "id":3,
    "nome":"Maria",
    "sobrenome":"Sousa",
    "total":0.00
}] 
```

### Buscar Transações
- Todas as transações ``GET /transacoes``
- Por tipo de transação ``GET /transacoes/tipo/{TIPO}``
```JSON 
[
    {
        "id": 4,
        "tipo": "GASTO",
        "valor": 2.00,
        "descricao": "teste"
    },
    {
        "id": 7,
        "tipo": "GASTO",
        "valor": 100.00,
        "descricao": "teste"
    }
]
```
### Adicionar um Novo Valor

- Controller Contas
``POST /contas/transacao/{idConta}``
- Controller Transações
``POST /trasacoes/{idConta}``

- Ao inserir o id da conta que você quer adicionar o valor ele será adicionado apenas a conta de id correspondente, caso o id não exista vai retornar erro.Toda vez que inserir um novo valor seja de gasto ou de lucro o total da conta será atualizado. 

```json 
{
    "tipo": "GASTO",
    "valor": 100.00,
    "descricao": "teste"
},
{
    "tipo": "LUCRO",
    "valor": 100.00,
    "descricao": "teste"
}
```

### Atualizar um Gasto/Lucro
``PUT /transacoes/{idTransação}/conta/{idConta}``

- Ao atualizar alguma transação o valor total da conta escolhida por id também será alterado automaticamente e caso o id não exista també retornará um erro.

### Atualizar Conta
``PUT /contas/{idConta}``

### Deletar conta ou transação
``DELETE /contas/{idConta}``
``DELETE /transacoes/{idTransacao}``

## FrontEnd Com Angular

### Login 
- Ao se cadastrar você pode acessar sua conta atráves da página de login, caso suas credenciais estejam inválidas você não consegue acessar sua conta.

![login.png](..%2F..%2F..%2FPictures%2FScreenshots%2Flogin.png)
![credenciais invalidas.png](..%2F..%2F..%2FPictures%2FScreenshots%2Fcredenciais%20invalidas.png)

- Caso você entre na sua conta, retorne para página de login e tente acessar sua conta novamente seu acesso será negado e você precisará se autenticar novamente.

![Captura de tela 2024-03-19 153206.png](..%2F..%2F..%2FPictures%2FScreenshots%2FCaptura%20de%20tela%202024-03-19%20153206.png)

### Tela Principal
- A tela principal é a da sua conta, você terá acesso ao valor total de sua conta e suas transações. Além disso você pode modificar informações de conta ou transações.
- Caso você não tenha adicionado nenhuma transação irá aparecer uma mensagem na tela para você.

![Captura de Tela (1).png](..%2F..%2F..%2FPictures%2FScreenshots%2FCaptura%20de%20Tela%20%281%29.png)

### Editar Conta
- Você pode alterar as informações da sua conta diretamente da aplicação.

![Captura de tela 2024-03-19 152736.png](..%2F..%2F..%2FPictures%2FScreenshots%2FCaptura%20de%20tela%202024-03-19%20152736.png)
![Captura de tela 2024-03-19 152929.png](..%2F..%2F..%2FPictures%2FScreenshots%2FCaptura%20de%20tela%202024-03-19%20152929.png)

### Editar Transação
- Você também pode alterar as transações.

![Captura de tela 2024-03-19 152830.png](..%2F..%2F..%2FPictures%2FScreenshots%2FCaptura%20de%20tela%202024-03-19%20152830.png)
### Deletar Transação 

![Captura de tela 2024-03-19 153002.png](..%2F..%2F..%2FPictures%2FScreenshots%2FCaptura%20de%20tela%202024-03-19%20153002.png)