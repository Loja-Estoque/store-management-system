/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.MovimentacaoEstoque;
import model.Produto;

import DAO.ProdutoDAO;

import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 *
 * @author W10
 */
public class MovimentacaoEstoqueDAO {
    MovimentacaoEstoque[] Me = new MovimentacaoEstoque[5];
   
    public MovimentacaoEstoqueDAO(ProdutoDAO produtoDAO)
    {
        Produto p1 = produtoDAO.buscarPorId(1);
        Produto p2 = produtoDAO.buscarPorId(2);
        Produto p3 = produtoDAO.buscarPorId(3);
        Produto p4 = produtoDAO.buscarPorId(4);
        
        MovimentacaoEstoque m1 = new MovimentacaoEstoque();
        m1.setProduto(p1);
        m1.setQuantidade(100);
        m1.setTipo("ENTRADA");
        m1.setValor_unitario(p1.getPreco_venda());
        this.Adicionar(m1);
        
        MovimentacaoEstoque m2 = new MovimentacaoEstoque();
        m2.setProduto(p2);
        m2.setQuantidade(80);
        m2.setTipo("ENTRADA");
        m2.setValor_unitario(p2.getPreco_venda());
        this.Adicionar(m2);
        
        MovimentacaoEstoque m3 = new MovimentacaoEstoque();
        m3.setProduto(p3);
        m3.setQuantidade(40);
        m3.setTipo("ENTRADA");
        m3.setValor_unitario(p3.getPreco_venda());
        this.Adicionar(m3);
        
    }
    
     public MovimentacaoEstoque buscarPorId(long id) {
         int ProximaPosicaoLivre = this.proximaPosicaoLivre();
        for (int i = 0; i < ProximaPosicaoLivre; i++) {
            if(Me[i].getId() == id)
                return Me[i];
        }
        return null;
    }
    
    public boolean Adicionar(MovimentacaoEstoque u)
    {
        int ProximaPosicaoLivre = this.proximaPosicaoLivre();
        if(ProximaPosicaoLivre != -1)
        {
            Me[ProximaPosicaoLivre] = u;
            return true;
        } else {
            return false;
        }
    }
    
    private int proximaPosicaoLivre() {
        for (int i = 0; i < Me.length; i++) {
            if (Me[i] == null) {
                return i;
            }

        }
        return -1;

    }
    
    public void mostrarTodos() {
        boolean temUsuario = false;
        for (MovimentacaoEstoque me : Me) {
            if (me != null) {
                System.out.println(me);
                temUsuario = true;
            }
        }
        if (!temUsuario) {
            System.out.println("nao existe Movimentação de Estoque");
        }
    }
    
    public boolean remover(long id) {
        for (int i = 0; i < Me.length; i++) {
            // Verifica se a posição não está nula e se o ID coincide
            if (Me[i] != null && Me[i].getId() == id) {
                Me[i] = null; // Remove a referência do objeto
                return true;
            }
        }
        return false;
    }
    
    public int consultarSaldo(Produto p) {
    int saldo = 0;
    for (int i = 0; i < Me.length; i++) {
      
        if (Me[i] != null && Me[i].getProduto().equals(p)) {
            
            // Lógica de soma e subtração baseada no tipo
            if (Me[i].getTipo().equalsIgnoreCase("ENTRADA")) {
                saldo += Me[i].getQuantidade();
            } else if (Me[i].getTipo().equalsIgnoreCase("SAIDA")) {
                saldo -= Me[i].getQuantidade();
            }
            
        }
    }
    return saldo;
}
    
   public boolean registrarSaida(Produto p, int qtdVendida) {
    // 1. Verificar se há saldo disponível antes de diminuir
        int saldoAtual = this.consultarSaldo(p);

        if(qtdVendida <= saldoAtual)
        {
            MovimentacaoEstoque novaSaida = new MovimentacaoEstoque();
            novaSaida.setProduto(p);
            novaSaida.setQuantidade(qtdVendida);
            novaSaida.setTipo("SAIDA");
            novaSaida.setValor_unitario(p.getPreco_venda());

            return this.Adicionar(novaSaida);
        }
        else{
            return false; // Não há estoque suficiente
        }
    }   
}
