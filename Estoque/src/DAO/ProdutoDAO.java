/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.Produto;

/**
 *
 * @author W10
 */
public class ProdutoDAO {
    Produto[] produtos = new Produto[5];
    
    public ProdutoDAO()
    {
        Produto p1 = new Produto();
        p1.setNome("Pao Frances");
        p1.setDescricao("Pao pequeno quentinho");
        p1.setPreco_venda(0.50);
        this.Adicionar(p1);
        
        Produto p2 = new Produto();
        p2.setNome("Broa de milho");
        p2.setDescricao("Broa doce para deixar sua manha mais feliz");
        p2.setPreco_venda(0.60);
        this.Adicionar(p2);
        
        Produto p3 = new Produto();
        p3.setNome("Sonho");
        p3.setDescricao("Sonho recheado com doce de Leite, nem parece que acordou");
        p3.setPreco_venda(0.30);
        this.Adicionar(p3);
    }
    
    public boolean Adicionar(Produto p)
    {
        int ProximaPosicaoLivre = this.proximaPosicaoLivre();
        if(ProximaPosicaoLivre != -1)
        {
            produtos[ProximaPosicaoLivre] = p;
            return true;
        } else {
            return false;
        }
    }
    
    private int proximaPosicaoLivre() {
        for (int i = 0; i < produtos.length; i++) {
            if (produtos[i] == null) {
                return i;
            }

        }
        return -1;

    }
    
    public void mostrarTodos() {
        boolean temProdutos = false;
        for (Produto p : produtos) {
            if (p != null) {
                System.out.println(p);
                temProdutos = true;
            }
        }
        if (!temProdutos) {
            System.out.println("nao existe Produto cadastrado");
        }
    }
    
    public boolean remover(String nome) {
        for (int i = 0; i < produtos.length; i++) {
            if (produtos[i] != null && produtos[i].getNome().equals(nome)) {
                produtos[i] = null;
                return true;
            }
        }
        return false;

    }
}
