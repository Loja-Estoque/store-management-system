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
        
        Menu mn = new Menu();
        
        int op1 = 9999;
        while(op1 !=0){ 

            op1 = mn.Menu1();
        
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
                case 3:
                    System.out.println("3 - Mostrar produtos\n");
                    break;
                default:
                    System.out.println("Por favor, escolha uma opcao valida\n");
                    break;
            }
        }
        
        System.out.println("\n\nSaiu do programa");
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
