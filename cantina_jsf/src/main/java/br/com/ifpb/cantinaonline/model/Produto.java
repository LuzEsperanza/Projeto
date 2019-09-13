package br.com.ifpb.cantinaonline.model;

import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

public class Produto {
    @PartitionKey
    String id;
    String nome;
    double preco;
    Integer quantidade;

    public Produto(){

    }

    public Produto(String id, String nome, double preco, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
