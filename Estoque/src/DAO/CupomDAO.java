/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Util.Util;
import java.time.LocalDate;
import model.Cupom;

/**
 *
 * @author W10
 */
public class CupomDAO {
    Cupom[] cupom = new Cupom[5];
    
    public CupomDAO()
    {
        LocalDate dataDeHojeNoSistema = Util.getAgora().toLocalDate();
        Cupom c1 = new Cupom();
        c1.setCodigo("PROMO1");
        c1.setData_validade(dataDeHojeNoSistema.plusDays(2));
        c1.setValor_desconto(20);
        c1.setTipo_desconto("FIXO");
        c1.setValor_minimo_pedido(25.00);
        this.Adicionar(c1);
        
        Cupom c2 = new Cupom();
        c2.setCodigo("PROMO2");
        c2.setData_validade(dataDeHojeNoSistema.plusDays(2));
        c2.setValor_desconto(5);
        c2.setTipo_desconto("PERCENTUAL");
        c2.setValor_minimo_pedido(25.00);
        this.Adicionar(c2);
    }
    
    public Cupom buscarPorId(int id) {
        int ProximaPosicaoLivre = this.proximaPosicaoLivre();
        for (int i = 0; i < ProximaPosicaoLivre; i++) {
            if(cupom[i].getId() == id)
                return cupom[i];
        }
        return null;
    }
    
    public Cupom buscarPorCodigo(String Codigo) {
        int ProximaPosicaoLivre = this.proximaPosicaoLivre();
        for (int i = 0; i < ProximaPosicaoLivre; i++) {
            if(cupom[i].getCodigo().equals(Codigo))
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
    
    public boolean remover(String codigo) {
        for (int i = 0; i < cupom.length; i++) {
            if (cupom[i] != null && cupom[i].getCodigo().equals(codigo)) {
                cupom[i] = null;
                return true;
            }
        }
        return false;

    }
    
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
