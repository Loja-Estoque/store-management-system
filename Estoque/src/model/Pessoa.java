/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pessoa {
    private static long serial;
    private long id;
    private String nome;
    private LocalDate nascimento;
    private String documento;
    private LocalDateTime data_criacao;
    private LocalDateTime data_modificacao;
    
    //construtor
    /*public Pessoa (String nome, LocalDate nascimento, String documento,LocalDateTime data_criacao,LocalDateTime data_modificacao) {
        this.id = ++Pessoa.serial;
        this.nome = nome;
        this.nascimento = nascimento;
        this.documento = documento;
        this.data_criacao = data_criacao;
        this.data_modificacao = data_modificacao;
    }*/
    
    public long getId(){
        return id;
    }
       
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public LocalDate getNascimento() {
        return nascimento;
    }
    
    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }
    
    public String getDocumento() {
        return documento;
    }
    
    public void setDocumento(String documento) {
        this.documento = documento;
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
        return "Pessoa{" + "id=" + id + ", nome=" + nome + ", nascimento=" + nascimento + ", documento=" + documento + ", data_criacao=" + data_criacao + ", data_modificacao=" + data_modificacao + '}';
    }
}
