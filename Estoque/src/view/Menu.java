/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Aluno
 */
public class Menu {
    
    Scanner scanner = new Scanner(System.in);
    public int Menu1()
    {
        StringBuilder builder = new StringBuilder("");
        builder.append("SEJA BEM VINDO AO NOSSO PROGRAMA\n\n");
        builder.append("LISTA DE PRODUTOS CADASTRADOS\n");
        builder.append("--------------------------------\n");
        builder.append("0 - Para sair do programa\n");
        builder.append("1 - Fazer login\n");
        builder.append("2 - Cadastrar Usuario\n");
        builder.append("3 - Mostrar produtos\n");
        builder.append("4 - Mostrar Usuarios\n");
        builder.append("5 - Mostrar Pessoas\n");
        builder.append("Qual opcao deseja: ");

        System.out.println(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }
    
    public int MenuAdm()
    {
        StringBuilder builder = new StringBuilder("");
        builder.append("Qual Item deseja?\n");
        builder.append("0 - Para sair do programa\n");
        builder.append("1 - Usuários\n");
        builder.append("2 - Pessoas\n");
        builder.append("3 - Produtos\n");
        builder.append("4 - Cupom\n");
        builder.append("5 - Pedido\n");
        builder.append("6 - Entrega\n");
        builder.append("7 - Estoque\n");
        builder.append("8 - Calendário\n");
        builder.append("Qual opcao deseja: ");
        
        System.out.println(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int MenuAdm1()
    {
        StringBuilder builder = new StringBuilder("");
        builder.append("Qual Item deseja alterar?\n");
        builder.append("0 - Para sair do programa\n");
        builder.append("1 - Criar\n");
        builder.append("2 - Mostrar Relatório\n");
        builder.append("3 - Alterar\n");
        builder.append("4 - Deletar\n");
        builder.append("Qual opcao deseja: ");
        
        System.out.println(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int MenuCliente()
    {
        StringBuilder builder = new StringBuilder("");
        builder.append("Qual Item deseja alterar?\n");
        builder.append("0 - Para sair do programa\n");
        builder.append("1 - Produtos\n");
        builder.append("2 - Carrinho\n");
        builder.append("3 - Pedidos\n");
        builder.append("4 - Cupom\n");
        builder.append("5 - Seu Usuário\n");
        builder.append("Qual opcao deseja: ");
        
        System.out.println(builder.toString());
        return Integer.parseInt(scanner.nextLine());
    }

}
