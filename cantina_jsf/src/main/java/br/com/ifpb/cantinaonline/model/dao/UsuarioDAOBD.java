package br.com.ifpb.cantinaonline.model.dao;


import br.com.ifpb.cantinaonline.model.Acesso;
import br.com.ifpb.cantinaonline.model.Endereco;
import br.com.ifpb.cantinaonline.model.Usuario;
import br.com.ifpb.cantinaonline.model.conexaoBanco.ConnectionFactory;
import sun.rmi.server.UnicastServerRef;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;


public class UsuarioDAOBD implements UsuarioDAO {

    private ConnectionFactory conexao;

    public UsuarioDAOBD() {
        conexao = new ConnectionFactory();

    }
    @Override
    public boolean adicionar(Usuario usuario) throws ClassNotFoundException, SQLException {

        Connection connection = conexao.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO usuario(nomecompleto,nomeusuario," +
                "idade,email,senha,telefone,funcao,cidade,bairro,rua,numero) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
        statement.setString(1,usuario.getNomeCompleto());
        statement.setString(2,usuario.getNomeUsuario());
        statement.setInt(3, usuario.getIdade());
        statement.setString(4,usuario.getEmail());
        statement.setString(5, usuario.getSenha());
        statement.setString(6, usuario.getTelefone());
        statement.setString(7, usuario.getFuncao());
        statement.setString(8, usuario.getEndereco().getCidade());
        statement.setString(9,usuario.getEndereco().getBairro());
        statement.setString(10, usuario.getEndereco().getRua());
        statement.setInt(11, usuario.getEndereco().getNumero());
        return statement.executeUpdate()>0;

    }

    @Override
    public boolean remover(Usuario usuario) throws SQLException, ClassNotFoundException {
        try(Connection connection = conexao.getConnection()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM usuario WHERE id = ?");
            statement.setString(1, usuario.getNomeUsuario());
            return statement.executeUpdate()>0;
        }
    }

    @Override
    public Acesso buscar(Acesso acesso) throws SQLException, ClassNotFoundException {
        try(Connection connection = conexao.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuario " +
                    "WHERE nomeUsuario =? AND senha =?");
            statement.setString(1,acesso.getNomeUsuario());
            statement.setString(2, acesso.getSenha());
            ResultSet set = statement.executeQuery();
            if(set.next()){
                Acesso acesso3 = new Acesso(set.getString("nomeusuario"),
                        set.getString("senha"),set.getString("nomecompleto"),
                        set.getString("funcao"));
                return acesso3;
            }else{
                return null;
            }
        }
    }

    public ArrayList listarUsuario() throws SQLException, ClassNotFoundException{
        try(Connection connection = conexao.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuario");
            ResultSet set = statement.executeQuery();
            ArrayList arrayList = new ArrayList();

            while (set.next()){
                String nomecompleto = set.getString("nomecompleto");
                String nomeusuario = set.getString("nomeusuario");
                int idade = set.getInt("idade");
                String email = set.getString("email");
                String senha = set.getString("senha");
                String telefone = set.getNString("telefone");
                String funcao = set.getString("funcao");
                String cidade = set.getString("cidade");
                String bairro = set.getString("bairro");
                String rua = set.getString("rua");
                Integer numero = set.getInt("numero");

                Usuario usuario = new Usuario(nomecompleto,nomeusuario,idade,email,senha,telefone,funcao,cidade,bairro,rua,numero);
                arrayList.add(usuario);
                System.out.println(usuario);
            }

            return arrayList;

        }
    }
    public Usuario getUser (String nomeUsuario) throws SQLException, ParseException, ClassNotFoundException {
        Usuario user = new Usuario();
        Endereco place = new Endereco();
        String sql = "select * from postagem where nomeUsuario="+nomeUsuario;
        Connection conexao = new ConnectionFactory().getConnection();
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setNomeCompleto(rs.getString("nomeCompleto"));
            user.setNomeUsuario(rs.getString("nomeUsuario"));
            user.setIdade(rs.getInt("idade"));
            user.setEmail(rs.getString("email"));
            user.setSenha(rs.getString("senha"));
            user.setTelefone(rs.getString("telefone"));
            user.setFuncao(rs.getString("funcao"));
        }
        String sqlTwo = "select * from usuario where id="+user.getId();
        PreparedStatement stmtTwo = conexao.prepareStatement(sqlTwo);
        ResultSet rsTwo = stmt.executeQuery();
        while (rsTwo.next()) {
            place.setCidade(rsTwo.getString("cidade"));
            place.setBairro(rsTwo.getString("bairro"));
            place.setRua(rsTwo.getString("rua"));
            place.setNumero(rsTwo.getInt("numero"));
        }
        user.setEndereco(place);
        stmt.close();
        conexao.close();
        return user;
}


}
