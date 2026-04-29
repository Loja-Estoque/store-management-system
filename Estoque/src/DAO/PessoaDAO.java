/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.Arrays;
import model.Pessoa;

public class PessoaDAO {
    public Pessoa[] pessoa = new Pessoa[5];
    public int proximaPosicao = 0;
    
    public void adicionar(Pessoa p) {
        if(proximaPosicao < pessoa.length){
            pessoa[proximaPosicao] = p;
            proximaPosicao++;
        } else {
            System.out.println("Erro: Capacidade máxima atingida.");
        }
    }
    
    public Pessoa buscarPorId(int id) {
        for (int i = 0; i < proximaPosicao; i++) {
            if(pessoa[i].getId() == id)
                return pessoa[i];
        }
        return null;
    }
    
    public boolean remove(String nome) {
        for(int i=0; i < proximaPosicao; i++) {
            if(pessoa[i].getNome().equals(nome))
                pessoa[i] = null;
        }
        return false;
    }
   
}
