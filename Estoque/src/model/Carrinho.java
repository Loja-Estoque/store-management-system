/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

public class Carrinho {
    private static long serial;
    private long id;
    private Usuario usuario;
    private String status;
    private LocalDateTime data_criacao;
    private LocalDateTime data_modificacao;
    
    public Carrinho(Usuario usuario, String status, LocalDateTime data_criacao, LocalDateTime data_modificacao) {
        this.id = ++Carrinho.serial;
        this.usuario = usuario;
        this.status = status;
        this.data_criacao = data_criacao;
        this.data_modificacao = data_modificacao;
    }

    public long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getData_criacao() {
        return data_criacao;
    }

    public LocalDateTime getData_modificacao() {
        return data_modificacao;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData_criacao(LocalDateTime data_criacao) {
        this.data_criacao = data_criacao;
    }

    public void setData_modificacao(LocalDateTime data_modificacao) {
        this.data_modificacao = data_modificacao;
    }

    @Override
    public String toString() {
        return "Carrinho{" + "id=" + id + ", usuario=" + usuario + ", status=" + status + ", data_criacao=" + data_criacao + ", data_modificacao=" + data_modificacao + '}';
    }
}
