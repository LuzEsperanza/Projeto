package br.com.ifpb.cantinaonline.model.dao;

import br.com.ifpb.cantinaonline.model.Produto;
import br.com.ifpb.cantinaonline.model.Usuario;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Query;
import com.datastax.oss.driver.api.mapper.annotations.Select;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProdutoDAO {
    boolean adicionarProduto(Produto produto) throws SQLException, ClassNotFoundException;
    boolean removerProduto(Produto produto) throws SQLException, ClassNotFoundException;
    ArrayList buscarProduto(String nomeProduto) throws SQLException, ClassNotFoundException;
    ArrayList listarProduto() throws SQLException, ClassNotFoundException;

    @Insert(ifNotExists = true)
    void salvar(Usuario pessoa);
    @Select
    Produto buscar(int id);
    @Query("SELECT * FROM Produto WHERE  nome = :nome")
    Produto buscarPorNome(String nome);

}
