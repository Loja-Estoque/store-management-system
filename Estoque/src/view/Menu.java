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
    int Menu1()
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
        builder.append("Qual opcao deseja: ");

        System.out.println(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }

    void MenuCriarUsuario()
    {
         Scanner scanner = new Scanner(System.in);
        StringBuilder builder = new StringBuilder("");
        builder.append("Informe seu nome: ");
        String n = scanner.nextLine();
        builder.append("Informe seu documento: ");
        String documento = scanner.nextLine();
        builder.append("Informe sua data de nascimento: ");
        String nascimento = scanner.nextLine();

        // trasformando string em local date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy");

        LocalDate birthday = LocalDate.parse(nascimento, dtf);      
        
        /*
        // criando pessoa;
        Pessoa p = new Pessoa();

        p.setNome(n);
        p.setDocumento(documento);
        p.setNascimento(birthday);

        // como adiciona pessoa no vetor?????? 
        PessoaDAO pDAO = new PessoaDAO();
        pDAO.adicionar(p);

        //verificar se já existe
        */
    }
}
