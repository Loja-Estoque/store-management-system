/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Util.Util;
import java.time.LocalDateTime;
import model.Entrega;

/**
 *
 * @author W10
 */
public class EntregaDAO {
    Entrega[] entrega = new Entrega[5];
    
    public Entrega buscarPorId(int id) {
        int ProximaPosicaoLivre = this.proximaPosicaoLivre();
        for (int i = 0; i < ProximaPosicaoLivre; i++) {
            if(entrega[i].getId() == id)
                return entrega[i];
        }
        return null;
    }
    
    public boolean Adicionar(Entrega e)
    {
        int ProximaPosicaoLivre = this.proximaPosicaoLivre();
        if(ProximaPosicaoLivre != -1)
        {
            entrega[ProximaPosicaoLivre] = e;
            return true;
        } else {
            return false;
        }
    }
    
    private int proximaPosicaoLivre() {
        for (int i = 0; i < entrega.length; i++) {
            if (entrega[i] == null) {
                return i;
            }

        }
        return -1;

    }
    
    public void mostrarTodos() {
        boolean temProdutos = false;
        for (Entrega e : entrega) {
            if (e != null) {
                System.out.println(e);
                temProdutos = true;
            }
        }
        if (!temProdutos) {
            System.out.println("nao existe Produto cadastrado");
        }
    }
    
    public boolean remover(String nome) {
        for (int i = 0; i < entrega.length; i++) {
            if (entrega[i] != null && entrega[i].getPedido().equals(nome)) {
                entrega[i] = null;
                return true;
            }
        }
        return false;

    }
    
    public boolean alterar(Entrega produtoAtualizado) {
        for (int i = 0; i < entrega.length; i++) {
            // Verifica se a posição não é nula e se o ID é igual ao do produto atualizado
            if (entrega[i] != null && entrega[i].getId() == produtoAtualizado.getId()) {
                entrega[i] = produtoAtualizado; // Substitui o antigo pelo novo
                return true;
            }
        }
        return false; // Retorna falso se não encontrou o produto para alterar
    }
    
    public void AtualizarStatus()
    {
        LocalDateTime hoje = Util.getAgora();
    

        for(Entrega e : entrega) {
            if (e != null) {
                // Calcula a diferença em horas entre a última mudança e o "agora" simulado
                long horasPassadas = java.time.Duration.between(e.getData_modificacao(), hoje).toHours();

                if (horasPassadas >= 24) {
                    switch (e.getStatus()) {
                        case "PREPARANDO":
                            e.setStatus("ENVIADO");
                            //p.setDataModificacao(hoje);
                            break;
                        case "ENVIADO":
                            e.setStatus("ENTREGUE");
                            //p.setDataModificacao(hoje);
                            break;

                    }
                }
            }
        }
    }
}
