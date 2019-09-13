package br.com.ifpb.cantinaonline.model;

import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

public class Usuario {
    @PartitionKey
    private String nomeCompleto;
    private String nomeUsuario;
    private Integer idade;
    private String email;
    private String senha;
    private String telefone;
    private String funcao;
    private Endereco endereco = new Endereco();

   public Usuario(){

   }


    public Usuario(String nomeCompleto, String nomeUsuario, Integer idade, String senha, String telefone, String funcao, Endereco endereco) {
        this.nomeCompleto = nomeCompleto;
        this.nomeUsuario = nomeUsuario;
        this.idade = idade;
        this.senha = senha;
        this.telefone = telefone;
        this.funcao = funcao;
        this.endereco = endereco;
    }

    public Usuario(String nomeCompleto, String nomeUsuario, int idade, String email,String senha, String telefone, String funcao, String cidade, String bairro, String rua, Integer numero) {
        this.nomeCompleto = nomeCompleto;
        this.nomeUsuario = nomeUsuario;
        this.idade = idade;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.funcao = funcao;
        this.endereco.setCidade(cidade);
        this.endereco.setBairro(bairro);
        this.endereco.setRua(rua);
        this.endereco.setNumero(numero);
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
