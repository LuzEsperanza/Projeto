package br.com.ifpb.cantinaonline.model.conexaoBanco;


import br.com.ifpb.cantinaonline.model.dao.ProdutoDAO;
import br.com.ifpb.cantinaonline.model.dao.UsuarioDAO;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface IQueFomeMapper {
    @DaoFactory
    UsuarioDAO usuarioDao();
    ProdutoDAO produtoDao();
}
