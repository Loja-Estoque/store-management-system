/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.ItensPedido;
/**
 *
 * @author Thalita
 */
public class ItensPedidoDAO {
    
    ItensPedido[] itens = new ItensPedido[5];
    
    public ItensPedido buscarPorId(int id) {
        int ProximaPosicaoLivre = this.proximaPosicaoLivre();
        for (int i = 0; i < ProximaPosicaoLivre; i++) {
            if(itens[i].getId() == id)
                return itens[i];
        }
        return null;
    }
    
    public boolean Adicionar(ItensPedido i)
    {
        int ProximaPosicaoLivre = this.proximaPosicaoLivre();
        if(ProximaPosicaoLivre != -1)
        {
            itens[ProximaPosicaoLivre] = i;
            return true;
        } else {
            return false;
        }
    }
    
    private int proximaPosicaoLivre() {
        for (int i = 0; i < itens.length; i++) {
            if (itens[i] == null) {
                return i;
            }

        }
        return -1;

    }
    
    public void mostrarTodos() {
        boolean temProdutos = false;
        for (ItensPedido i : itens) {
            if (i != null) {
                System.out.println(i);
                temProdutos = true;
            }
        }
        if (!temProdutos) {
            System.out.println("nao existe Produto cadastrado");
        }
    }
    
    
    /*public boolean remover(String nome) {
        for (int i = 0; i < itens.length; i++) {
            if (itens[i] != null && itens[i].getNome().equals(nome)) {
                itens[i] = null;
                return true;
            }
        }
        return false;

    }*/
    
    public boolean alterar(ItensPedido ItensPedidoAtualizado) {
        for (int i = 0; i < itens.length; i++) {
            // Verifica se a posição não é nula e se o ID é igual ao do produto atualizado
            if (itens[i] != null && itens[i].getId() == ItensPedidoAtualizado.getId()) {
                itens[i] = ItensPedidoAtualizado; // Substitui o antigo pelo novo
                return true;
            }
        }
        return false; // Retorna falso se não encontrou o produto para alterar
    }
}
