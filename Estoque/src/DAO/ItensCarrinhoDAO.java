/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.Carrinho;
import model.ItensCarrinho;

/**
 *
 * @author W10
 */
public class ItensCarrinhoDAO {
    ItensCarrinho[] itensc = new ItensCarrinho[5];
    
    public ItensCarrinho buscarPorId(int id) {
        int ProximaPosicaoLivre = this.proximaPosicaoLivre();
        for (int i = 0; i < ProximaPosicaoLivre; i++) {
            if(itensc[i].getId() == id)
                return itensc[i];
        }
        return null;
    }
    
     public ItensCarrinho buscarPorCarrinho(Carrinho carrinho) {
        int ProximaPosicaoLivre = this.proximaPosicaoLivre();
        for (int i = 0; i < ProximaPosicaoLivre; i++) {
            if(itensc[i].get_carrinho() == carrinho)
                return itensc[i];
        }
        return null;
    }
    
    public boolean Adicionar(ItensCarrinho c)
    {
        int ProximaPosicaoLivre = this.proximaPosicaoLivre();
        if(ProximaPosicaoLivre != -1)
        {
            itensc[ProximaPosicaoLivre] = c;
            return true;
        } else {
            return false;
        }
    }
    
    private int proximaPosicaoLivre() {
        for (int i = 0; i < itensc.length; i++) {
            if (itensc[i] == null) {
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
    
    public boolean alterar(ItensCarrinho itenscAtualizado) {
        for (int i = 0; i < itensc.length; i++) {
            // Verifica se a posição não é nula e se o ID é igual ao do produto atualizado
            if (itensc[i] != null && itensc[i].getId() == itenscAtualizado.getId()) {
                itensc[i] = itenscAtualizado; // Substitui o antigo pelo novo
                return true;
            }
        }
        return false; // Retorna falso se não encontrou o produto para alterar
    }
    
    public void mostrarTodos() {
        boolean temProdutos = false;
        for (ItensCarrinho Ic : itensc) {
            if (Ic != null) {
                System.out.println(Ic);
                temProdutos = true;
            }
        }
        if (!temProdutos) {
            System.out.println("nao existe Produto cadastrado");
        }
    }
    
    // No ItensCarrinhoDAO.java
        public void mostrarItensDoCarrinho(long idCarrinho) {
            boolean temItens = false;
            System.out.println("--- ITENS NO SEU CARRINHO ---");
            for (ItensCarrinho ic : itensc) {
                if (ic != null && ic.get_carrinho().getId() == idCarrinho) {
                    System.out.println("Produto: " + ic.get_produto().getNome() + 
                                       " | Qtd: " + ic.getQuantidade() + 
                                       " | Preço Unit: R$" + ic.getPreco_unitario());
                    temItens = true;
                }
            }
            if (!temItens) {
                System.out.println("O carrinho está vazio.");
            }
        }
}
