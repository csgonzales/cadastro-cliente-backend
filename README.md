# cadastro-cliente-backend
Teste realizado com objetivo de cadastrar dados simples de cliente utilizando Spring Boot.

O banco de dados utilizado na aplicação é um MySQL e as configurações do projeto são bem simples.

Utilizei os seguintes dados para acesso ao banco de dados para criar o schema e usuário.

Schema: 
```
CREATE SCHEMA `teste-fintech` ;
```

Usuário:
```
CREATE USER 'fintech'@'localhost' IDENTIFIED BY 'fintech';
GRANT ALL PRIVILEGES ON `teste-fintech`.* TO 'fintech'@'localhost';
FLUSH PRIVILEGES;
```


Para executar a aplicação, basta executar a classe ClienteApplication, ou gerar o pacote via Maven com o seguinte comando na raíz do projeto:
```
mvn clean package
```
E em seguida, executar o jar gerado em /target:
```
java -jar cliente-0.0.1-SNAPSHOT.jar
```
