/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Control;


import DAO.PessoaDAO;
import DAO.ProdutoDAO;
import DAO.UsuarioDAO;
import DAO.MovimentacaoEstoqueDAO;
import DAO.PedidoDAO;

import model.Pessoa;
import model.Produto;
import model.MovimentacaoEstoque;
import model.Usuario;

import view.Menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import model.Pedido;



/**
 *
 * @author W10
 */
public class Trabalho {

    /**
     * @param args the command line arguments
     */
    
    private PessoaDAO pessoaDAO = new PessoaDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO(pessoaDAO);
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private MovimentacaoEstoqueDAO MovimentacaoDAO = new MovimentacaoEstoqueDAO(produtoDAO);
    private PedidoDAO pedidoDAO = new PedidoDAO();
    
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
                        System.out.println("Login: ");
                        String login = scanner.nextLine();
                        System.out.println("Senha: ");
                        String senha = scanner.nextLine();
                        Usuario logado = usuarioDAO.buscaUsuarioLogin(login, senha);
                        
                        if(logado != null){
                            
                            if(logado.getLogin() == "Administrador")
                            {
                                System.out.println("Usuario Administrador logado");
                                System.out.println("Prox Menu");
                            } else
                            {
                                System.out.println("Usuario comum logado");
                                op1 =0;
                                Comprar(logado);
                                
                            }                        
                            
                            //loop adm ou comum
                        } else {
                            System.out.println("Usuario invalido. Tente novamente");
                        }
                        
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
                        produtoDAO.mostrarTodos();
                        break;
                        
                    case 4:
                        usuarioDAO.mostrarTodos();
                        break;
                    case 5:
                        pessoaDAO.mostrarTodos();
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
    
    private void Comprar(Usuario u)
    {
        int opC = 99;
        do{
            
                produtoDAO.mostrarCompra();
                opC = mn.MenuCompras();
                System.out.println("Digite sua opcao: ");
            switch (opC) {

                case 0:
                    System.out.println("0 - Sair do programa");
                    break;
                case 1:
                    System.out.println("1 - Mostrar Produtos\n\n");
                    produtoDAO.mostrarTodos();
                break;
                case 2:
                    System.out.println("Qual item deseja comprar?");
                    produtoDAO.mostrarCompra();
                    
                    int opP = Integer.parseInt(scanner.nextLine());
                    
                    int id = opP;
                    Produto temp = produtoDAO.buscarPorId(id);
                    System.out.println("Qual quantidade: ");
                    int qnt = Integer.parseInt(scanner.nextLine());
                    
                    if(MovimentacaoDAO.registrarSaida(temp, qnt)){
                        Pedido tempP = this.CriarPedido(u, qnt, temp);
                        if(pedidoDAO.adicionar(tempP)){
                            System.out.println("Pedido realizado com sucesso");
                        } else{
                            System.out.println("Erro, não foi possível realizar seu pedido");
                        }
                        
                        System.out.println("Venda Realizada com sucesso");
                        
                    } else{
                        System.out.println("Quantidade muito alta para o produto" + temp.getNome());
                    }
                    
                    
                break;
                case 3:
                    System.out.println("3 - Adcionar ao Carrinho");
                    produtoDAO.mostrarTodos();
                break;
                default:
                    System.out.println("Por favor, escolha uma opcao valida\n");
                    break;
            }
        }while(opC!=0);
            
    }
    
    public Pedido CriarPedido(Usuario u, double quantidade, Produto p)
    {
        Pedido pedido = new Pedido();
        pedido.setId_usuario(u);
        pedido.setStatus("CRIADO");
        pedido.setValor_total(p.getPreco_venda() * quantidade);
        System.out.println("Qual será a forma de pagamento");
        pedido.setForma_pagamento(scanner.nextLine());
        
        return pedido;
        
    }
}

/* private static long serial;
    private long id;
    private long id_usuario;
    private String status;  
    private double valor_total;
    private String forma_pagamento;
    private LocalDateTime data_criacao;
    private LocalDateTime data_modificacao;*/