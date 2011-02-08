package com.epucjr.engyos.tecnologia.persistencia;


	
	import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConecaoBD {

  /* Realiza conexão com o banco de dados, retornando o objeto Connection */
 public static Connection getConnection( ) throws
 ClassNotFoundException, SQLException
  {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://192.169.9.2/ProjetoEngyos";
    String user = "root";
    String password = "alaska";
    Class.forName(driver);
    Connection con = DriverManager.getConnection( url, user, password );
    return con;
  }


}