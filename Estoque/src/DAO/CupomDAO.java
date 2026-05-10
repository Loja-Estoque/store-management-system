/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.Cupom;

/**
 *
 * @author W10
 */
public class CupomDAO {
    Cupom[] cupom = new Cupom[5];
    
    public Cupom buscarPorId(int id) {
        int ProximaPosicaoLivre = this.proximaPosicaoLivre();
        for (int i = 0; i < ProximaPosicaoLivre; i++) {
            if(cupom[i].getId() == id)
                return cupom[i];
        }
        return null;
    }
    
    public boolean Adicionar(Cupom c)
    {
        int ProximaPosicaoLivre = this.proximaPosicaoLivre();
        if(ProximaPosicaoLivre != -1)
        {
            cupom[ProximaPosicaoLivre] = c;
            return true;
        } else {
            return false;
        }
    }
    
    private int proximaPosicaoLivre() {
        for (int i = 0; i < cupom.length; i++) {
            if (cupom[i] == null) {
                return i;
            }

        }
        return -1;

    }
    
    /*public boolean remover(String nome) {
        for (int i = 0; i < carrinho.length; i++) {
            if (carrinho[i] != null && carrinho[i].getPedido().equals(nome)) {
                carrinho[i] = null;
                return true;
            }
        }
        return false;

    }*/
    
    public void mostrarTodos() {
        boolean temProdutos = false;
        for (Cupom c : cupom) {
            if (c != null) {
                System.out.println(c);
                temProdutos = true;
            }
        }
        if (!temProdutos) {
            System.out.println("nao existe Produto cadastrado");
        }
    }
    
    public boolean alterar(Cupom cupomAtualizado) {
        for (int i = 0; i < cupom.length; i++) {
            // Verifica se a posição não é nula e se o ID é igual ao do produto atualizado
            if (cupom[i] != null && cupom[i].getId() == cupomAtualizado.getId()) {
                cupom[i] = cupomAtualizado; // Substitui o antigo pelo novo
                return true;
            }
        }
        return false; // Retorna falso se não encontrou o produto para alterar
    }
}
