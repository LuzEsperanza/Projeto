package br.com.ifpb.cantinaonline.model.conexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {

    private String usuario;
    private String url;
    private String senha;

    public ConnectionFactory(){
        usuario ="postgres";
       senha = "1234";

        url = "jdbc:postgresql://localhost:5432/CantinaJsf";
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url,usuario,senha);
    }


}
