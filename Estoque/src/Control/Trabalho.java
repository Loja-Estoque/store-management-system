/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Control;


import DAO.PessoaDAO;
import DAO.UsuarioDAO;
import model.Pessoa;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import model.Usuario;
import view.Menu;

/**
 *
 * @author W10
 */
public class Trabalho {

    /**
     * @param args the command line arguments
     */
    
    private PessoaDAO pessoaDAO = new PessoaDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    Scanner scanner = new Scanner(System.in);
        
        Menu mn = new Menu();
        
        public Trabalho(){
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
                        Pessoa temp = this.criaPessoa();
                        if(pessoaDAO.adicionar(temp)){
                            System.out.println("Pessoa adicionada com sucesso");
                            Usuario tempu = this.criaUsuario(temp);
                            if(usuarioDAO.Adicionar(tempu))
                            {
                                System.out.println("Usuario adicionado com sucesso");
                            } else{
                                System.out.println("Usuario nao adicionado");
                            }
                            
                        } else{
                            System.out.println("Pessoa nao adicionada");
                        }
                        
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
    
    public static void main(String[] args) {
        
        new Trabalho();
        
    }

    private Pessoa criaPessoa()
    {
        Pessoa p = new Pessoa();
        //Scanner scanner = new Scanner(System.in);
        System.out.println("Informe seu nome: ");
        p.setNome(scanner.nextLine());
        System.out.println("Informe seu documento: ");
         p.setDocumento(scanner.nextLine());
         
        System.out.println("Informe sua data de nascimento (dd/mm/aa): ");
        String nascimento = scanner.nextLine();

        // trasformando string em local date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy");

        LocalDate birthday = LocalDate.parse(nascimento, dtf);      
        //criando pessoa;
        p.setNascimento(birthday);

        return p;
        
    }
    
    private Usuario criaUsuario(Pessoa p)
    {
        Usuario u1 = new Usuario();
        
        u1.setPessoa(p);
        
        System.out.println("Informe seu login: ");
        u1.setLogin(scanner.nextLine());
         
        System.out.println("Informe sua senha: ");
        u1.setSenha(scanner.nextLine());
         
        return u1;
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
