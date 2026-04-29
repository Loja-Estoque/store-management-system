/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;


import DAO.PessoaDAO;
import model.Pessoa;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author W10
 */
public class Trabalho {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int op1 = 9999;
        while(op1 !=0){ 

            //op1 = Menu1();
        
            switch (op1) {
                case 0:
                    System.out.println("Saindo ...\n");
                    break;
                case 1:
                    System.out.println("1 - Fazer Login\n");
                    break;
                case 2:
                    System.out.println("2 - Cadastrar Usuario\n");
                    break;
                default:
                    System.out.println("Por favor, escolha uma opcao valida\n");
                    break;
            }
        }
        
        System.out.println("\n\nSaiu do programa");
    }

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
        builder.append("Qual opcao deseja: ");

        System.out.println(builder.toString());

        return Integer.parseInt(scanner.nextLine());
    }

    void CriarUsuario()
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

        // criando pessoa;
        Pessoa p = new Pessoa();

        p.setNome(n);
        p.setDocumento(documento);
        p.setNascimento(birthday);

        // como adiciona pessoa no vetor?????? 
        PessoaDAO pDAO = new PessoaDAO();
        pDAO.adicionar(p);

        //verificar se já existe
    }

    /*
    Usuário:
    private long id;
    private Pessoa pessoa;
    private String login;
    private String senha;
    private LocalDateTime data_criacao;
    private LocalDateTime data_modificacao; */

    /*
    Pessoa: 
    private String nome;
    private LocalDate nascimento;
    private String documento; */
}
