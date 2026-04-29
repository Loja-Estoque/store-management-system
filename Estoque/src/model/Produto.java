/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

public class Produto {
    private static long serial;
    private long id;
    private String nome;
    private String descricao;
    private double preco_venda;
    private LocalDateTime data_criacao;
    private LocalDateTime data_modificacao;
    
    public Produto (String nome, String descricao, double preco_venda, LocalDateTime data_criacao,LocalDateTime data_modificacao) {
        this.id = ++Produto.serial;
        this.nome = nome;
        this.descricao = descricao;
        this.preco_venda = preco_venda;
        this.data_criacao = data_criacao;
        this.data_modificacao = data_modificacao;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco_venda() {
        return preco_venda;
    }

    public LocalDateTime getData_criacao() {
        return data_criacao;
    }

    public LocalDateTime getData_modificacao() {
        return data_modificacao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco_venda(double preco_venda) {
        this.preco_venda = preco_venda;
    }

    public void setData_criacao(LocalDateTime data_criacao) {
        this.data_criacao = data_criacao;
    }

    public void setData_modificacao(LocalDateTime data_modificacao) {
        this.data_modificacao = data_modificacao;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco_venda=" + preco_venda + ", data_criacao=" + data_criacao + ", data_modificacao=" + data_modificacao + '}';
    }
}
