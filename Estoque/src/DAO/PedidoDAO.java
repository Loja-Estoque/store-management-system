/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.time.LocalDateTime;
import model.Pedido;
import Util.Util;

public class PedidoDAO {
    private Pedido[] pedidos = new Pedido[5];
    private int proximaPosicao = 0;
    
    public boolean adicionar(Pedido p) {
        if(proximaPosicao < pedidos.length){
            pedidos[proximaPosicao] = p;
            proximaPosicao++;
            return true;
        } else {
            System.out.println("Limite de pedidos atingido!");
            return false;
        }
    }
    
    public void mostrarTodos() {
        boolean temPedido = false;
        for (int i = 0; i < proximaPosicao; i++) {
            System.out.println(pedidos[i]);
            temPedido = true;
        }
        if (!temPedido) {
            System.out.println("Nenhum pedido registrado.");
        }
    }
    
    
}
