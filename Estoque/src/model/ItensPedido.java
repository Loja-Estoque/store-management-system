/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

public class ItensPedido {
    private long id;
    private long id_pedido;
    private long id_produto;
    private int quantidade;
    private double preco_unitario;
    private int subtotal;
    private LocalDateTime data_criacao;
    private LocalDateTime data_modificacao;
    
    public ItensPedido(long id, long id_pedido, long id_produto, int quantidade, double preco_unitario, int subtotal, LocalDateTime data_criacao, LocalDateTime data_modificacao) {
        this.id = id;
        this.id_pedido = id_pedido;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.preco_unitario = preco_unitario;
        this.subtotal = subtotal;
        this.data_criacao = data_criacao;
        this.data_modificacao = data_modificacao;
    }

    public long getId() {
        return id;
    }

    public long getId_pedido() {
        return id_pedido;
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

    public int getSubtotal() {
        return subtotal;
    }

    public LocalDateTime getData_criacao() {
        return data_criacao;
    }

    public LocalDateTime getData_modificacao() {
        return data_modificacao;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId_pedido(long id_pedido) {
        this.id_pedido = id_pedido;
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

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public void setData_criacao(LocalDateTime data_criacao) {
        this.data_criacao = data_criacao;
    }

    public void setData_modificacao(LocalDateTime data_modificacao) {
        this.data_modificacao = data_modificacao;
    }

    @Override
    public String toString() {
        return "ItensPedido{" + "id=" + id + ", id_pedido=" + id_pedido + ", id_produto=" + id_produto + ", quantidade=" + quantidade + ", preco_unitario=" + preco_unitario + ", subtotal=" + subtotal + ", data_criacao=" + data_criacao + ", data_modificacao=" + data_modificacao + '}';
    }
}
