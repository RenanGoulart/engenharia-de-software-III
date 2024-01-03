# CRUD de Clientes

Projeto realizado para a matéria de Engenharia de Software III da FATEC de Mogi das Cruzes orientado pelo professor Rodrigo Rocha.

## Tecnologias

- HTML / CSS / JS (Front-End)
- Java / JSP (Back-End)
- PostgreSQL - SQL (Banco de Dados)

## Arquitetura

O projeto utiliza MVC como padrão arquitetural em conjunto aos padrões Facade, Strategy, Singleton e DAO.

- MVC: Separa o projeto em 3 camadas que possibilitam maior flexibilidade da camada de visualização em relação a mudanças;
- Facade: É utilizada na recepção dos dados dos formulários, possibilita que todos os dados recebam o mesmo tratamento;
- Strategy: É utilizada para aplicar as regras de negócio nos dados dos formulários, quando as informações dos fomulários chegam a fachada são executadas estratégias correspondentes a entidade de domínio;
- Singleton: É utilizada para manter somente um instancia de classe de conexão de banco de dados, possibilita acesso global a mesma instancia de classe;
- DAO (Data Access Object): É utilizada para realizar operações com a base de dados, abstraí a lógica de persistência de dados das regras de negócio;

## Como Utilizar

Clone e rode o projeto em um servidor Tomcat (comece pelo arquivo clientsView.jsp).

- É recomendável a utlização da IDE Eclipse;
- É necessário a utilização de um servidor Tomcat (versão utilizada: 8.5);

Foi utilizado a versão 21 do Java para realização do projeto.
