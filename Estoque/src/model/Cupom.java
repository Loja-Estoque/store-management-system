/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import Util.Util;

public class Cupom {
    private static long serial;
    private long id;
    private String codigo;
    private String tipo_desconto;
    private double valor_desconto;
    private double valor_minimo_pedido;
    private LocalDate data_validade;
    private LocalDateTime data_criacao;
    private LocalDateTime data_modificacao;
    
    public Cupom(String codigo, String tipo_desconto, double valor_desconto, double valor_minimo_pedido, LocalDate data_validade, LocalDateTime data_criacao, LocalDateTime data_modificacao) {
        this.id = ++Cupom.serial;
        this.codigo = codigo;
        this.tipo_desconto = tipo_desconto;
        this.valor_desconto = valor_desconto;
        this.valor_minimo_pedido = valor_minimo_pedido;
        this.data_validade = data_validade;
        this.data_criacao = data_criacao;
        this.data_modificacao = data_modificacao;
    }
    
    public Cupom()
    {
        this.id = ++Cupom.serial;
        
        this.data_criacao = LocalDateTime.now();
        this.data_modificacao = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTipo_desconto() {
        return tipo_desconto;
    }

    public double getValor_desconto() {
        return valor_desconto;
    }

    public double getValor_minimo_pedido() {
        return valor_minimo_pedido;
    }

    public LocalDate getData_validade() {
        return data_validade;
    }

    public LocalDateTime getData_criacao() {
        return data_criacao;
    }

    public LocalDateTime getData_modificacao() {
        return data_modificacao;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
        this.data_modificacao = Util.getAgora();
    }

    public void setTipo_desconto(String tipo_desconto) {
        this.tipo_desconto = tipo_desconto;
        this.data_modificacao = Util.getAgora();
    }

    public void setValor_desconto(double valor_desconto) {
        this.valor_desconto = valor_desconto;
        this.data_modificacao = Util.getAgora();
    }

    public void setValor_minimo_pedido(double valor_minimo_pedido) {
        this.valor_minimo_pedido = valor_minimo_pedido;
        this.data_modificacao = Util.getAgora();
    }

    public void setData_validade(LocalDate data_validade) {
        this.data_validade = data_validade;
        this.data_modificacao = Util.getAgora();
    }





    @Override
    public String toString() {
        return "Cupom{" + "id=" + id + ", codigo=" + codigo + ", tipo_desconto=" + tipo_desconto + ", valor_desconto=" + valor_desconto + ", valor_minimo_pedido=" + valor_minimo_pedido + ", data_validade=" + data_validade + ", data_criacao=" + data_criacao + ", data_modificacao=" + data_modificacao + '}';
    }
}
