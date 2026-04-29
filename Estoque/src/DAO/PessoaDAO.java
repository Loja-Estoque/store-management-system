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
    
    public boolean adicionar(Pessoa p) {
        if(proximaPosicao < pessoa.length){
            pessoa[proximaPosicao] = p;
            proximaPosicao++;
            
            return true;
        } else {
            return false;
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
    
        public void mostrarTodos() {
        boolean temPessoa = false;
        for (Pessoa j : pessoa) {
            if (j != null) {
                System.out.println(j);
                temPessoa = true;
            }
        }
        if (!temPessoa) {
            System.out.println("não existe pessoa cadastrada");
        }
    }

   
}
