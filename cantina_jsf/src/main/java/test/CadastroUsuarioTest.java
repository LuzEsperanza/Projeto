package test;

import br.com.ifpb.cantinaonline.model.Endereco;
import br.com.ifpb.cantinaonline.model.Usuario;
import br.com.ifpb.testes.Validacao;
import br.com.ifpb.testes.ValidacaoPadrao;
import br.com.ifpb.testes.exception.CampoNuloException;
import br.com.ifpb.testes.exception.UsuarioException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class CadastroUsuarioTest {

    private Validacao validacao;

    @Before
    public void iniciou() {
        validacao = new ValidacaoPadrao();
        System.out.println("Instanciou validadorCEP");
    }

    @After
    public void parou() {
        System.out.println("Terminou o método de teste");
    }

    @Test(expected = UsuarioException.class)
    public void testarNomeValido() throws CampoNuloException, SQLException, ClassNotFoundException, UsuarioException {
        Usuario usuario = new Usuario(1,"Daniel", "daniel123", 20,"daniel123@gmal.com",
                "123","funcionario", new Endereco("Cidade", "Bairro","Rua",10));
       validacao.cadastrarUsuario(usuario);
    }

    @Test(expected = UsuarioException.class)
    public void nomeUsuariorepetido() throws SQLException, ClassNotFoundException, UsuarioException {
        Usuario usuario = new Usuario(2, "João", "joao123", 29, "joao123@gmail.com","40028922",
                "cliente", new Endereco("Cidade", "bairro","Rua",10));
        validacao.nomeRepetido(usuario);
        validacao.nomeRepetido(usuario);
    }




}
