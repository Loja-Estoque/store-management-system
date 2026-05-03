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
    public int Menu1()
    {
        Scanner scanner = new Scanner(System.in);
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
    
    

}
