/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.time.LocalDateTime;
import model.Pedido;
import model.Usuario;
import Util.Util;

public class PedidoDAO {

    private Pedido[] pedidos = new Pedido[5];
    private int proximaPosicao = 0;

    public Pedido buscarPorId(int id) {
        int ProximaPosicaoLivre = this.proximaPosicaoLivre();
        for (int i = 0; i < ProximaPosicaoLivre; i++) {
            if (pedidos[i].getId() == id) {
                return pedidos[i];
            }
        }
        return null;
    }

    public boolean adicionar(Pedido p) {
        if (proximaPosicao < pedidos.length) {
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

    public void mostrarTodosPorUsuario(Usuario u) {
        boolean temPedido = false;
        for (int i = 0; i < proximaPosicao; i++) {
            if (pedidos[i] != null && pedidos[i].getUsuario()== u) {
                System.out.println(pedidos[i]);
                temPedido = true;
            }
        }

        if (!temPedido) {
            System.out.println("\nVocê ainda não possui pedidos registrados.\n\n");
        }
    }

    private int proximaPosicaoLivre() {
        for (int i = 0; i < pedidos.length; i++) {
            if (pedidos[i] == null) {
                return i;
            }

        }
        return -1;

    }

    public double calcularFaturamentoTotal() {
        double total = 0;
        for (int i = 0; i < proximaPosicao; i++) {
            if (pedidos[i] != null && (!pedidos[i].getStatus().equals("CANCELADO") && !pedidos[i].getStatus().equals("CRIADO"))) {
                total += pedidos[i].getValor_total();
            }
        }
        return total;
    }

    public double calcularFaturamentoDiario(LocalDateTime data) {
        double total = 0;
        for (int i = 0; i < proximaPosicao; i++) {
            if (pedidos[i] != null && Util.isMesmoDia(pedidos[i].getData_criacao(), data)) {
                total += pedidos[i].getValor_total();
            }
        }
        return total;
    }

    public double calcularFaturamentoMensal(LocalDateTime data) {
        double total = 0;
        for (int i = 0; i < proximaPosicao; i++) {
            if (pedidos[i] != null && Util.isMesmoMes(pedidos[i].getData_criacao(), data)) {
                total += pedidos[i].getValor_total();
            }
        }
        return total;
    }

    public double calcularFaturamentoAnual(LocalDateTime data) {
        double total = 0;
        for (int i = 0; i < proximaPosicao; i++) {
            if (pedidos[i] != null && Util.isMesmoAno(pedidos[i].getData_criacao(), data)) {
                total += pedidos[i].getValor_total();
            }
        }
        return total;
    }
}
