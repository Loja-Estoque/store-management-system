/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import Util.Util;
import java.time.LocalDateTime;
import java.util.Objects;

public class Usuario {
    private long id;
    private Pessoa pessoa;
    private String login;
    private String senha;
    private LocalDateTime data_criacao;
    private LocalDateTime data_modificacao;
    
    //construtor

    public Usuario()
    {
        
        this.data_criacao = Util.getAgora();
        
        this.data_modificacao = Util.getAgora();
    }
    
    public long getId(){
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
        
    public Pessoa getPessoa(){
        return pessoa;
    }
    
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        this.data_modificacao = Util.getAgora();
    }
    
    public String getLogin(){
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
        this.data_modificacao = Util.getAgora();
    }
    
    public String getSenha(){
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
        this.data_modificacao = Util.getAgora();
    }
    
    public LocalDateTime getData_criacao() {
        return data_criacao;
    } 

    public void setData_criacao(LocalDateTime data_criacao) {
        this.data_criacao = data_criacao;
    }

    public void setData_modificacao(LocalDateTime data_modificacao) {
        this.data_modificacao = data_modificacao;
    }
    
    
    public LocalDateTime getData_modificacao() {
        return data_modificacao;
    }
    

    @Override
    public String toString() {
        return "id: " + id 
                + "\nPessoa: \n" + pessoa 
                + "\nLogin: " + login 
                + "\nSenha: " + senha;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.login);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.login, other.login);
    }

}
