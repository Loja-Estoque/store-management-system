/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

public class ItensCarrinho {
    private static long serial;
    private long id;
    private long id_carrinho;
    private long id_produto;
    private int quantidade;
    private double preco_unitario;
    private LocalDateTime data_criacao;
    private LocalDateTime data_modificacao;
    
    public ItensCarrinho( long id_carrinho, long id_produto, int quantidade, double preco_unitario, LocalDateTime data_criacao, LocalDateTime data_modificacao) {
        this.id = ++ItensCarrinho.serial;
        this.id_carrinho = id_carrinho;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.preco_unitario = preco_unitario;
        this.data_criacao = data_criacao;
        this.data_modificacao = data_modificacao;
    }

    public long getId() {
        return id;
    }

    public long getId_carrinho() {
        return id_carrinho;
    }

    public long getId_produto() {
        return id_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco_unitario() {
        return preco_unitario;
    }

    public LocalDateTime getData_criacao() {
        return data_criacao;
    }

    public LocalDateTime getData_modificacao() {
        return data_modificacao;
    }

    public void setId_carrinho(long id_carrinho) {
        this.id_carrinho = id_carrinho;
    }

    public void setId_produto(long id_produto) {
        this.id_produto = id_produto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPreco_unitario(double preco_unitario) {
        this.preco_unitario = preco_unitario;
    }

    public void setData_criacao(LocalDateTime data_criacao) {
        this.data_criacao = data_criacao;
    }

    public void setData_modificacao(LocalDateTime data_modificacao) {
        this.data_modificacao = data_modificacao;
    }

    @Override
    public String toString() {
        return "ItensCarrinho{" + "id=" + id + ", id_carrinho=" + id_carrinho + ", id_produto=" + id_produto + ", quantidade=" + quantidade + ", preco_unitario=" + preco_unitario + ", data_criacao=" + data_criacao + ", data_modificacao=" + data_modificacao + '}';
    }
}
