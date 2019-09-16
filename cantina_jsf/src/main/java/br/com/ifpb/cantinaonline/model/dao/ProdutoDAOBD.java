package br.com.ifpb.cantinaonline.model.dao;

import br.com.ifpb.cantinaonline.model.Produto;
import br.com.ifpb.cantinaonline.model.Usuario;
import br.com.ifpb.cantinaonline.model.conexaoBanco.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAOBD implements ProdutoDAO {

    private ConnectionFactory conexao;

    public ProdutoDAOBD() {
        conexao = new ConnectionFactory();

    }

    @Override
    public boolean adicionarProduto(Produto produto) throws SQLException, ClassNotFoundException {
        try(Connection connection = conexao.getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO produto(id,nome," +
                    "preco,quantidade) VALUES(?,?,?,?)");
            statement.setString(1,produto.getId());
            statement.setString(2,produto.getNome());
            statement.setDouble(3,produto.getPreco());
            statement.setInt(4,produto.getQuantidade());
            return statement.executeUpdate()>0;
        }
    }

    @Override
    public boolean removerProduto(Produto produto) throws SQLException, ClassNotFoundException {
        try(Connection connection = conexao.getConnection()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM produto WHERE id = ?");
            statement.setString(1, produto.getId());
            return statement.executeUpdate()>0;
        }
    }
    @Override
    public ArrayList buscarProduto(String nomeProduto) throws SQLException, ClassNotFoundException {
        try(Connection connection = conexao.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM produto " +
                    "WHERE nome ILIKE ? ");
            System.out.println("oi");
            statement.setString(1,nomeProduto);
            ResultSet set = statement.executeQuery();
            ArrayList produtos = new ArrayList();
            while (set.next()){
                String id = set.getString("id");
                String nome = set.getString("nome");
                double preco = set.getDouble("preco");
                Integer quantidade = set.getInt("quantidade");

                Produto produto = new Produto(id,nome,preco,quantidade);
                produtos.add(produto);
            }
            return produtos;
        }

    }

    @Override
    public ArrayList listarProduto() throws SQLException, ClassNotFoundException{
        try(Connection connection = conexao.getConnection()){
           PreparedStatement statement = connection.prepareStatement("SELECT * FROM produto");
           ResultSet set = statement.executeQuery();
           ArrayList arrayList = new ArrayList();

            while (set.next()){
                String id = set.getString("id");
                String nome = set.getString("nome");
                double preco = set.getDouble("preco");
                Integer quantidade = set.getInt("quantidade");

                Produto produto = new Produto(id,nome,preco,quantidade);
                arrayList.add(produto);

            }

            return arrayList;

        }
    }

    @Override
    public void salvar(Usuario pessoa) {

    }

    @Override
    public Produto buscar(int id) {
        return null;
    }

    @Override
    public Produto buscarPorNome(String nome) {
        return null;
    }
}
