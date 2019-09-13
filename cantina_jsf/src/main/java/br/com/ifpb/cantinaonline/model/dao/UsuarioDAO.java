package br.com.ifpb.cantinaonline.model.dao;

import br.com.ifpb.cantinaonline.model.Acesso;
import br.com.ifpb.cantinaonline.model.Produto;
import br.com.ifpb.cantinaonline.model.Usuario;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Query;
import com.datastax.oss.driver.api.mapper.annotations.Select;

import java.sql.SQLException;

public interface UsuarioDAO {
    boolean adicionar(Usuario usuario) throws SQLException, ClassNotFoundException;
    boolean remover(Usuario usuario) throws SQLException, ClassNotFoundException;
    Acesso buscar(Acesso acesso) throws SQLException, ClassNotFoundException;

    @Insert(ifNotExists = true)
    void salvar(Usuario pessoa);
    @Select
    Usuario buscar(String nomeCompleto);
    @Query("SELECT * FROM Usuario WHERE  nomeUsuario = :nomeUsuario")
    Produto buscarPorNomeUsuario(String nomeUsuario);
}
