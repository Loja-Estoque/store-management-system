/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import Util.Util;
import java.time.LocalDateTime;

public class ItensPedido {
    private static long serial;
    private long id;
    private Pedido pedido;
    private Produto produto;
    private int quantidade;
    private double preco_unitario;
    private double subtotal;
    private LocalDateTime data_criacao;
    private LocalDateTime data_modificacao;
    
    public ItensPedido(long id, long id_pedido, long id_produto, int quantidade, double preco_unitario, double subtotal, LocalDateTime data_criacao, LocalDateTime data_modificacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.preco_unitario = preco_unitario;
        this.subtotal = subtotal;
        this.data_criacao = data_criacao;
        this.data_modificacao = data_modificacao;
    }
    
    public ItensPedido()
    {
         this.id = ++ItensPedido.serial;
        
        this.data_criacao = Util.getAgora();
        this.data_modificacao = Util.getAgora();
    }

    public long getId() {
        return id;
    }

    public Pedido get_pedido() {
        return pedido;
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

    public double getSubtotal() {
        return subtotal;
    }

    public LocalDateTime getData_criacao() {
        return data_criacao;
    }

    public LocalDateTime getData_modificacao() {
        return data_modificacao;
    }

    public void setId_pedido(Pedido pedido) {
        this.pedido = pedido;
        this.data_modificacao = Util.getAgora();
    }

    public void setId_produto(Produto produto) {
        this.produto = produto;
        this.data_modificacao = Util.getAgora();
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.data_modificacao = Util.getAgora();
    }

    public void setPreco_unitario(double preco_unitario) {
        this.preco_unitario = preco_unitario;
        this.data_modificacao = Util.getAgora();
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
        this.data_modificacao = Util.getAgora();
    }

    public void setData_modificacao(LocalDateTime data_modificacao) {
        this.data_modificacao = data_modificacao;
    }

    @Override
    public String toString() {
        return "ItensPedido{" + "id=" + id + ", id_pedido=" + pedido + ", id_produto=" + produto + ", quantidade=" + quantidade + ", preco_unitario=" + preco_unitario + ", subtotal=" + subtotal + ", data_criacao=" + data_criacao + ", data_modificacao=" + data_modificacao + '}';
    }
}
