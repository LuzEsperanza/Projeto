package br.com.ifpb.cantinaonline.model.conexaoBanco;

import br.com.ifpb.cantinaonline.model.dao.ProdutoDAO;
import com.datastax.oss.driver.api.core.CqlSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private String usuario;
    private String url;
    private String senha;

    public ConnectionFactory(){
        usuario ="postgres";
       senha = "postgres";

        url = "jdbc:postgresql://localhost:5432/CantinaJsf";
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url,usuario,senha);
    }
    public void conexaoCassandra (){
        try (CqlSession session = CqlSession.builder().withKeyspace("IQueFome").build()) {
            IQueFomeMapper iQueFomeMapper = new IQueFomeMapperBuilder(session).build();
            ProdutoDAO produtoDAO = IQueFomeMapper.produtoDao();
        }
    }

}
