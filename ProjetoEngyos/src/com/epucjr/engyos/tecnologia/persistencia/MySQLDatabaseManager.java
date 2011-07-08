package com.epucjr.engyos.tecnologia.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabaseManager {

    private final String host;
    private final String nomeDeUsuario;
    private final String senha;
    private final String database;
    private Connection connection;

    public MySQLDatabaseManager() {
        this.host = "localhost";
        this.nomeDeUsuario = "root";
        this.senha = "nights";
        this.database = "ProjetoEngyos";
        this.connection = null;
    }



    /* Realiza conexão com o banco de dados, retornando o objeto Connection */
    public Connection getConnection() throws
            ClassNotFoundException, SQLException {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://" + this.host + "/" + this.database;
        String user = this.nomeDeUsuario;
        String password = this.senha;
        Class.forName(driver);
        this.connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public void closeConnection() throws SQLException{
        if(this.connection != null && !this.connection.isClosed()){
            this.connection.close();
        }
    }
}
