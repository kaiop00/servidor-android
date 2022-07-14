package com.example.navegacaoentretelas;

public class Objeto {
    private String nome;
    private String descricao;
    private int id;

    public Objeto(String nome, String descricao ,int id) {
        this.nome = nome;
        this.descricao = descricao;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Objeto{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                '}';
    }
}
