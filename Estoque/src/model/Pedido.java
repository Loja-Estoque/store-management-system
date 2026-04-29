/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

public class Pedido {
    private static long serial;
    private long id;
    private long id_usuario;
    private String status;  
    private double valor_total;
    private String forma_pagamento;
    private LocalDateTime data_criacao;
    private LocalDateTime data_modificacao;
    
    public Pedido(long id_usuario, String status, double valor_total, String forma_pagamento, LocalDateTime data_criacao, LocalDateTime data_modificacao) {
        this.id = ++Pedido.serial;
        this.id_usuario = id_usuario;
        this.status = status;
        this.valor_total = valor_total;
        this.forma_pagamento = forma_pagamento;
        this.data_criacao = data_criacao;
        this.data_modificacao = data_modificacao;
    }

    public long getId() {
        return id;
    }

    public long getId_usuario() {
        return id_usuario;
    }

    public String getStatus() {
        return status;
    }

    public double getValor_total() {
        return valor_total;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public LocalDateTime getData_criacao() {
        return data_criacao;
    }

    public LocalDateTime getData_modificacao() {
        return data_modificacao;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public void setData_criacao(LocalDateTime data_criacao) {
        this.data_criacao = data_criacao;
    }

    public void setData_modificacao(LocalDateTime data_modificacao) {
        this.data_modificacao = data_modificacao;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", id_usuario=" + id_usuario + ", status=" + status + ", valor_total=" + valor_total + ", forma_pagamento=" + forma_pagamento + ", data_criacao=" + data_criacao + ", data_modificacao=" + data_modificacao + '}';
    }
}
