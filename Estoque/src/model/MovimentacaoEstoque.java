/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

public class MovimentacaoEstoque {
    private static long serial;
    private long id;
    private long id_produto;
    private int quantidade;
    private String tipo;
    private double valor_unitario;
    private LocalDateTime data_criacao;
    private LocalDateTime data_modificacao;
    
    public MovimentacaoEstoque(long id_produto, int quantidade, String tipo, double valor_unitario, LocalDateTime data_criacao, LocalDateTime data_modificacao) {
        this.id = ++MovimentacaoEstoque.serial;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.valor_unitario = valor_unitario;
        this.data_criacao = data_criacao;
        this.data_modificacao = data_modificacao;
    }

    public long getId() {
        return id;
    }

    public long getId_produto() {
        return id_produto;
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

    public void setId_produto(long id_produto) {
        this.id_produto = id_produto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setValor_unitario(double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public void setData_criacao(LocalDateTime data_criacao) {
        this.data_criacao = data_criacao;
    }

    public void setData_modificacao(LocalDateTime data_modificacao) {
        this.data_modificacao = data_modificacao;
    }

    @Override
    public String toString() {
        return "MovimentacaoEstoque{" + "id=" + id + ", id_produto=" + id_produto + ", quantidade=" + quantidade + ", tipo=" + tipo + ", valor_unitario=" + valor_unitario + ", data_criacao=" + data_criacao + ", data_modificacao=" + data_modificacao + '}';
    }
}
