/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Entrega {
    private static long serial;
    private long id;
    private long id_pedido;
    private String status;
    private String transportadora;
    private String codigo_rastreio;
    private LocalDate data_envio;
    private LocalDate data_entrega;
    private LocalDateTime data_criacao;
    private LocalDateTime data_modificacao;

    public Entrega(long id_pedido, String status, String transportadora, String codigo_rastreio, LocalDate data_envio, LocalDate data_entrega, LocalDateTime data_criacao, LocalDateTime data_modificacao) {
        this.id = ++Entrega.serial;
        this.id_pedido = id_pedido;
        this.status = status;
        this.transportadora = transportadora;
        this.codigo_rastreio = codigo_rastreio;
        this.data_envio = data_envio;
        this.data_entrega = data_entrega;
        this.data_criacao = data_criacao;
        this.data_modificacao = data_modificacao;
    }

    public long getId() {
        return id;
    }

    public long getId_pedido() {
        return id_pedido;
    }

    public String getStatus() {
        return status;
    }

    public String getTransportadora() {
        return transportadora;
    }

    public String getCodigo_rastreio() {
        return codigo_rastreio;
    }

    public LocalDate getData_envio() {
        return data_envio;
    }

    public LocalDate getData_entrega() {
        return data_entrega;
    }

    public LocalDateTime getData_criacao() {
        return data_criacao;
    }

    public LocalDateTime getData_modificacao() {
        return data_modificacao;
    }

    public void setId_pedido(long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTransportadora(String transportadora) {
        this.transportadora = transportadora;
    }

    public void setCodigo_rastreio(String codigo_rastreio) {
        this.codigo_rastreio = codigo_rastreio;
    }

    public void setData_envio(LocalDate data_envio) {
        this.data_envio = data_envio;
    }

    public void setData_entrega(LocalDate data_entrega) {
        this.data_entrega = data_entrega;
    }

    public void setData_criacao(LocalDateTime data_criacao) {
        this.data_criacao = data_criacao;
    }

    public void setData_modificacao(LocalDateTime data_modificacao) {
        this.data_modificacao = data_modificacao;
    }
    
    
}
