/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

public class MovimentacaoEstoque {
    private static long serial;
    private long id;
    private Produto produto;
    private int quantidade;
    private String tipo;
    private double valor_unitario;
    private LocalDateTime data_criacao;
    private LocalDateTime data_modificacao;
    
    public MovimentacaoEstoque(int quantidade, String tipo, double valor_unitario, LocalDateTime data_criacao, LocalDateTime data_modificacao) {
        this.id = ++MovimentacaoEstoque.serial;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.valor_unitario = valor_unitario;
        this.data_criacao = data_criacao;
        this.data_modificacao = data_modificacao;
    }
    
    public MovimentacaoEstoque()
    {
        this.id = ++MovimentacaoEstoque.serial;
         
        this.data_criacao = LocalDateTime.now();
        this.data_modificacao = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor_unitario() {
        return valor_unitario;
    }

    public LocalDateTime getData_criacao() {
        return data_criacao;
    }

    public LocalDateTime getData_modificacao() {
        return data_modificacao;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.data_modificacao = LocalDateTime.now();
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
        this.data_modificacao = LocalDateTime.now();
    }

    public void setValor_unitario(double valor_unitario) {
        this.valor_unitario = valor_unitario;
        this.data_modificacao = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "MovimentacaoEstoque{" + "id=" + id + ", id_produto=" + produto + ", quantidade=" + quantidade + ", tipo=" + tipo + ", valor_unitario=" + valor_unitario + ", data_criacao=" + data_criacao + ", data_modificacao=" + data_modificacao + '}';
    }
}
