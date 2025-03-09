# Servi�o de Ve�culos - API Restful

Este � um servi�o de gerenciamento de ve�culos implementado utilizando a arquitetura DDD (Domain-Driven Design) e organizado com base nos princ�pios da Clean Architecture. A API permite opera��es CRUD (Create, Read, Update, Delete) para gerenciar dados relacionados aos ve�culos, como marca, ano e cor.

## Arquitetura

![img_1.png](img_1.png)

A arquitetura do sistema segue os princ�pios do **DDD** (Domain-Driven Design) e � organizada de forma a promover o desacoplamento das responsabilidades do sistema. As camadas principais s�o:

- **controller**: Cont�m os controladores respons�veis pela intera��o com as requisi��es HTTP.
- **domain**: Representa o dom�nio da aplica��o, incluindo as entidades e regras de neg�cio.
- **service**: Implementa��o da l�gica de neg�cios que manipula as entidades.
- **repository**: Interface entre o dom�nio e a base de dados, respons�vel pelas opera��es de persist�ncia.
- **configuration**: Configura��es da aplica��o, como a configura��o do banco de dados e inicializa��o dos servi�os.

## Setup do Projeto

### Pr�-requisitos

Certifique-se de ter os seguintes softwares instalados:

- **Docker**: Para containerizar e rodar o banco de dados.
- **Docker Compose**: Para orquestrar containers.
- **Java 17**: A aplica��o � constru�da com Java 17.
- **Maven 3.9+**: Para gerenciamento de depend�ncias e build.
- **Postman**: Para testar a API via requisi��es HTTP.

### Passos para Rodar a Aplica��o

1. **Clonar o reposit�rio**:
   ```bash
   git clone https://github.com/GuilhermeSilva010/Veiculos-Service.git

2. **Rodar o Banco de Dados com Docker**:

   Dentro da pasta do projeto, execute o seguinte comando para rodar o PostgreSQL no Docker:

   ```bash
   docker-compose up -d

Este comando ir� iniciar o banco de dados PostgreSQL com as configura��es do docker-compose.yml.

### Configura��o do Maven:

Certifique-se de que voc� tem o Maven instalado.
Navegue at� a pasta do projeto e execute o seguinte comando para compilar a aplica��o:
```bash
mvn clean install
```
Logo em seguida rode a classe CoderApplication do SpringBoot.

**Ao subir a aplica��o ser� inserido automaticamente dados mockados no banco de dados estando pronto para uso.**


### Acessando documenta��o no Swagger:

API estar� dispon�vel em http://localhost:8080/documentacao.

### Testando a API com Postman:

Para testar os endpoints da API, use o Postman. O arquivo de cole��es do Postman pode ser encontrado na pasta **Postman_Collections** em resources.

### Estrutura do Projeto
A estrutura do projeto segue os princ�pios da Clean Architecture, com as seguintes pastas e responsabilidades:

![img.png](img.png)


### Descri��o das Pastas

- **configuration**: Cont�m as classes de configura��o, como a configura��o do banco de dados e da camada de persist�ncia.
- **controller**: Onde os controladores REST da aplica��o s�o definidos. Eles s�o respons�veis por lidar com as requisi��es HTTP.
- **domain**: Define as entidades e regras de neg�cio. Cont�m a parte central da aplica��o.
- **service**: L�gica de neg�cios, onde as regras de manipula��o dos dados das entidades s�o executadas.
- **repository**: Respons�vel pela comunica��o com o banco de dados, realizando as opera��es CRUD.
- **exerc�cios**: Os exerc�cios propostos no projeto podem ser encontrados dentro da pasta `exercicios`. Cada exerc�cio est� dentro de sua respectiva pasta, e deve ser seguido o passo a passo de implementa��o de acordo com as instru��es fornecidas.


### Tratamento Global de Exce��es

A aplica��o utiliza **Controller Advice** para o tratamento centralizado de exce��es. O @ControllerAdvice permite capturar e processar exce��es lan�adas em qualquer controlador, garantindo respostas padronizadas e melhorando a manuten��o do c�digo.

Sempre que uma exce��o ocorre, o sistema a intercepta e retorna uma resposta adequada com um c�digo de status HTTP correspondente e uma mensagem clara para o cliente.

Benef�cios:
Centraliza��o do tratamento de erros, evitando duplica��o de c�digo nos controladores.
Padroniza��o das respostas de erro, facilitando o consumo da API.
Melhoria na manuten��o, permitindo a adi��o de novos tratamentos sem modificar diretamente os controladores.

## Testes Unit�rios

Foram criados testes unit�rios da aplica��o de Veiculos das camadas de service, controller e repository.

Para rodar os testes voc� pode ir at� a pasta de testes e executar todos na pasta raiz ou se preferir rode o comando abaixo:
```bash
mvn test
```
