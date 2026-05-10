/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.time.LocalDateTime;
import model.Carrinho;

/**
 *
 * @author W10
 */
public class CarrinhoDAO {
    
    Carrinho[] carrinho = new Carrinho[5];
    
     public Carrinho buscarPorId(int id) {
        int ProximaPosicaoLivre = this.proximaPosicaoLivre();
        for (int i = 0; i < ProximaPosicaoLivre; i++) {
            if(carrinho[i].getId() == id)
                return carrinho[i];
        }
        return null;
    }
    
    public boolean Adicionar(Carrinho c)
    {
        int ProximaPosicaoLivre = this.proximaPosicaoLivre();
        if(ProximaPosicaoLivre != -1)
        {
            carrinho[ProximaPosicaoLivre] = c;
            return true;
        } else {
            return false;
        }
    }
    
    private int proximaPosicaoLivre() {
        for (int i = 0; i < carrinho.length; i++) {
            if (carrinho[i] == null) {
                return i;
            }

        }
        return -1;

    }
    
    public void mostrarTodos() {
        boolean temProdutos = false;
        for (Carrinho c : carrinho) {
            if (c != null) {
                System.out.println(c);
                temProdutos = true;
            }
        }
        if (!temProdutos) {
            System.out.println("nao existe Produto cadastrado");
        }
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
    
    public boolean alterar(Carrinho carrinhoAtualizado) {
        for (int i = 0; i < carrinho.length; i++) {
            // Verifica se a posição não é nula e se o ID é igual ao do produto atualizado
            if (carrinho[i] != null && carrinho[i].getId() == carrinhoAtualizado.getId()) {
                carrinho[i] = carrinhoAtualizado; // Substitui o antigo pelo novo
                return true;
            }
        }
        return false; // Retorna falso se não encontrou o produto para alterar
    }
    
    
    public void verificarCarrinhosExpirados(LocalDateTime agoraSimulado) {
        for (int i = 0; i < carrinho.length; i++) {
            if (carrinho[i] != null && carrinho[i].getStatus().equals("ABERTO")) {
                // Calcula a diferença entre a criação e o tempo atual
                long horas = java.time.Duration.between(carrinho[i].getData_criacao(), agoraSimulado).toHours();

                if (horas >= 24) {
                    carrinho[i].setStatus("EXPIRADO");
                    carrinho[i].setData_modificacao(agoraSimulado);
                    System.out.println("Carrinho ID " + carrinho[i].getId() + " expirou por tempo.");
                }
            }
        }
    }
    
}
