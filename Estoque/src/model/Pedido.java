/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import Util.Util;
import java.time.LocalDateTime;

public class Pedido {
    private long id;
    private Usuario usuario;
    private Cupom cupom;
    private String status;  
    private double valor_total;
    private String forma_pagamento;
    private LocalDateTime data_criacao;
    private LocalDateTime data_modificacao;
    
    public Pedido(long id_usuario, String status, double valor_total, String forma_pagamento, LocalDateTime data_criacao, LocalDateTime data_modificacao) {
        this.status = status;
        this.valor_total = valor_total;
        this.forma_pagamento = forma_pagamento;
        this.data_criacao = data_criacao;
        this.data_modificacao = data_modificacao;
    }
    
    public Pedido()
    {
        
        this.data_criacao = Util.getAgora();
        this.data_modificacao = Util.getAgora();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setData_criacao(LocalDateTime data_criacao) {
        this.data_criacao = data_criacao;
    }

    public void setData_modificacao(LocalDateTime data_modificacao) {
        this.data_modificacao = data_modificacao;
    }

    public Usuario getUsuario() {
        return usuario;
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

    public Cupom getCupom() {
        return cupom;
    }

    public void setId_usuario(Usuario u) {
        this.usuario = u;
        this.data_modificacao = Util.getAgora();
    }

    public void setStatus(String status) {
        this.status = status;
        this.data_modificacao = Util.getAgora();
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
        this.data_modificacao = Util.getAgora();
    }

    public void setCupom(Cupom cupom) {
        this.cupom = cupom;
        this.data_modificacao = Util.getAgora();
    }

    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
        this.data_modificacao = Util.getAgora();
    }


    @Override
    public String toString() {
        return "Id do pedido: " + id 
                + "\nUsuario: " + usuario.getLogin() 
                + "\nStatus: " + status 
                + "\nValor total: " + valor_total 
                + "\nForma de pagamento: " + forma_pagamento;
    }
}
