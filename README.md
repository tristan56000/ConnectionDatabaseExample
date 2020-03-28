# README

This project aims to find a way to product an hash for each entry of a table in a database.
The main issue that remains is that two different entries should lead to two different hashes.

You can understand how this project has been realised by looking at the *Example.md* file,
in the *src/main/resources* directory.

This project is supposed to work for all tables, provided that you use the *MySql* *DBMS*. 


## How to build the project ?
Run the following command into a terminal:
    <br/>`mvn clean install`<br/>

## How to run the project ?
To run the client, run the following command:
    <br/>`java -jar target/ConnectDatabaseExample-2-jar-with-dependencies.jar <dbmsUser> <userPassword> <databaseName> <table>`<br/>
where `<dbmsUser>` and `<userPassword>` are the credentials to the *DBMS*, and where `<databaseName>` and `<table>` are 
the database's table you want to analyze.