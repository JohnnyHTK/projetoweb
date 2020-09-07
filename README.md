<img src="image/logo_fatecsjc.png" height=150px>

# Laboratorio de projeto de Banco de dados
Projeto sobre desenvolvido nas aulas de laboratorio de projeto de Banco de dados da Fatec SJC.

## Integrantes: 
- Carlos Henrique Monteiro Neto
- Daniel Willians Ignacio de Souza
- Victor Cardial De Menezes Pereira
- Lucas Campioni Rodrigues Porto
- Jonathan Moreno Martins

## Tecnologias utilizadas:
- [<img src="image\mysql.png" height=40px>](https://www.mysql.com//) MySQL

# Sprints anteriores
- [Sprint 1: Backend + Frontend + BD para iniciar o desenvolvimento](https://github.com/JohnnyHTK/projetoweb)

- [Sprint 2: ]()

- [Sprint 3: ]()

- [Sprint 4: ]()

- [Sprint 5: ]()

# Sprint 6: Entrega final
Nessa sprint foi atualizado ...

# Instalação e execução
Para a execução do projeto, é necessaria a instalação do [Node.js](#tecnologias-utilizadas) e [PostgreSQL](#tecnologias-utilizadas).
## Nos diretórios backend e frontend (também na segunda_aplicacao):
### 1 - Instale as dependências do projeto
> npm install
## Na raiz do projeto
### 1 - Execute o comando para gerar a pasta "secrets" e arquivos necessários
> node config_project.js
### 2 - Edite os arquivos "backend/secrets/databaseConfiguration.js" e "segunda_aplicacao/backend/secrets/databaseConfiguration.js" com os dados do PostgreSQL
> user: Usuário do banco de dados,\
> host: Host do banco de dados,\
> database: Nome do banco de dados,\
> password: Senha do usuário do banco de dados,\
> port: Porta que o banco de dados utiliza
### 3 - Edite os arquivos "backend/secrets/index.js" e "segunda_aplicacao/backend/secrets/index.js"
> APP_SECRET = String que será usada para gerar *hashes* na aplicação
### 4 - Crie uma credencial OAuth utilizando uma conta google.
> Lembre-se que as aplicacoes estão configuradas para utilizar os ports 1234, 1235, 3000 e 3001
> https://support.google.com/googleapi/answer/6158849?hl=en
### 5 - Edite os arquivos "backend/secrets/keys.js" e "segunda_aplicacao/backend/secrets/keys.js"
> clientID: Client ID da credencial OAuth,\
> clientSecret: Client  da credencial OAuth,\
> cookieKey: String que será usada para gerar o cookie de sessão na aplicação
### 6 - Edite os arquivos ".pem" com um par de chaves privada e publica RSA