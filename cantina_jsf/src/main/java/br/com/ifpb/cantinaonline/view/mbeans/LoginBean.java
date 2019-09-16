package br.com.ifpb.cantinaonline.view.mbeans;

import br.com.ifpb.cantinaonline.model.Acesso;
import br.com.ifpb.cantinaonline.model.Usuario;
import br.com.ifpb.cantinaonline.model.dao.UsuarioDAOBD;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.ParseException;

@ManagedBean
@SessionScoped
public class LoginBean {
    private String nomeUsuario;
    private String senha;
    private  UsuarioDAOBD usuarioDAOBD = new UsuarioDAOBD();
    private Acesso usuarioLogado;
    private Usuario user = null;
    private String btName;

    public String efetuarLogin() throws SQLException, ClassNotFoundException, ParseException {
        this.usuarioLogado = new Acesso(nomeUsuario,senha,"","");


        if(usuarioDAOBD.buscar(usuarioLogado) != null){
            System.out.println(usuarioDAOBD.buscar(usuarioLogado));
            user = usuarioDAOBD.getUser(usuarioLogado.getNomeUsuario());
            return "template.xhtml";
        } else {
            FacesMessage message = new FacesMessage("Login ou Senha incorretos", "Login ou Senha incorretos");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "";
        }

    }

    public String retornar() throws Exception{
        return "template.xhtml";
        
    }

    public String fazerLogout() {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        return "login.xhtml";
    }
    public String setButtonOne(){
        if(usuarioLogado==null){
            btName = "Login";
            return btName;
        }
        else {
            btName = "Logout";
            return btName;
        }
    }
    public String setButtonTwo(){
        if(usuarioLogado==null){
            return "Cadastro";
        }
        else return user.getNomeUsuario();
    }
    public String setActionBtnOne(){
        if(usuarioLogado==null){
            return "login.xhtml";
        }
        else{
            usuarioLogado=null;
            return "template.html";
        }
    }
    public String setActionBtnTwo(){
        if(usuarioLogado==null){
            return "cadastro.xhtml";
        }
        else {
            return "#";
        }
    }

    public Acesso getUsuarioLogado(){
        return  usuarioLogado;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getUserID (){
        if (user!=null) {
            return user.getId();
        }
        else return 0;
    }
}
