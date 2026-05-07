/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

public class ItensCarrinho {
    private static long serial;
    private long id;
    private Carrinho carrinho;
    private Produto produto;
    private int quantidade;
    private double preco_unitario;
    private LocalDateTime data_criacao;
    private LocalDateTime data_modificacao;
    
    public ItensCarrinho( long id_carrinho, long id_produto, int quantidade, double preco_unitario, LocalDateTime data_criacao, LocalDateTime data_modificacao) {
        this.id = ++ItensCarrinho.serial;
        this.quantidade = quantidade;
        this.preco_unitario = preco_unitario;
        this.data_criacao = data_criacao;
        this.data_modificacao = data_modificacao;
    }
    
    public ItensCarrinho()
    {
        this.id = ++ItensCarrinho.serial;
        
        this.data_criacao = LocalDateTime.now();
        this.data_modificacao = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public Carrinho get_carrinho() {
        return carrinho;
    }

    public Produto get_produto() {
        return produto;
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

    public void setId_carrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public void setId_produto(Produto produto) {
        this.produto = produto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPreco_unitario(double preco_unitario) {
        this.preco_unitario = preco_unitario;
    }


    @Override
    public String toString() {
        return "ItensCarrinho{" + "id=" + id + ", id_carrinho=" + carrinho + ", id_produto=" + produto + ", quantidade=" + quantidade + ", preco_unitario=" + preco_unitario + ", data_criacao=" + data_criacao + ", data_modificacao=" + data_modificacao + '}';
    }
}
