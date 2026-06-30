/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import Util.Util;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pessoa {
    private long id;
    private String nome;
    private LocalDate nascimento;
    private String documento;
    private LocalDateTime data_criacao;
    private LocalDateTime data_modificacao;
    
    
    public Pessoa()
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
    
    
       
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
        this.data_modificacao = Util.getAgora();
    }
    
    public LocalDate getNascimento() {
        return nascimento;
    }
    
    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
        this.data_modificacao = Util.getAgora();
    }
    
    public String getDocumento() {
        return documento;
    }
    
    public void setDocumento(String documento) {
        this.documento = documento;
        this.data_modificacao = Util.getAgora();
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
        return "id: " + id 
                + "\nNome: " + nome 
                + "\nNascimento: " + nascimento 
                + "\nDocumento: " + documento 
                + "\nData_criacao: " + data_criacao 
                + "Data_modificacao: " + data_modificacao;
    }
}
