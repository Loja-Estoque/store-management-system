/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

public class Usuario {
    private static long serial;
    private long id;
    private Pessoa pessoa;
    private String login;
    private String senha;
    private LocalDateTime data_criacao;
    private LocalDateTime data_modificacao;
    
    //construtor
    public Usuario(Pessoa pessoa,String login,String senha,LocalDateTime data_criacao,LocalDateTime data_modificacao) {
        this.id = ++Usuario.serial;
        this.pessoa = pessoa;
        this.login = login;
        this.senha = senha;
        this.data_criacao = data_criacao;
        this.data_modificacao = data_modificacao;
    }
    
    public long getId(){
        return id;
    }
        
    public Pessoa getPessoa(){
        return pessoa;
    }
    
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public String getLogin(){
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getSenha(){
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public LocalDateTime getData_criacao() {
        return data_criacao;
    } 
    
    public void setData_criacao(LocalDateTime data_criacao) {
        this.data_criacao = data_criacao;
    }
    
    public LocalDateTime getData_modificacao() {
        return data_modificacao;
    }
    
    public void setData_modificacao(LocalDateTime data_modificacao) {
        this.data_modificacao = data_modificacao;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", pessoa=" + pessoa + ", login=" + login + ", senha=" + senha + ", data_criacao=" + data_criacao + ", data_modificacao=" + data_modificacao + '}';
    }
}
