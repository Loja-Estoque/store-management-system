/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Control;


import DAO.EntregaDAO;
import DAO.PessoaDAO;
import DAO.ProdutoDAO;
import DAO.UsuarioDAO;
import DAO.MovimentacaoEstoqueDAO;
import DAO.PedidoDAO;
import DAO.ItensPedidoDAO;
import DAO.CarrinhoDAO;
import DAO.ItensCarrinhoDAO;
import DAO.CupomDAO;

import model.Pessoa;
import model.Produto;
import model.MovimentacaoEstoque;
import model.Usuario;
import model.Entrega;

import view.Menu;

import Util.Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import model.ItensPedido;
import model.Pedido;
import model.Carrinho;
import model.Cupom;
import model.ItensCarrinho;



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
    private EntregaDAO entregaDAO = new EntregaDAO();
    private CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
    private ItensCarrinhoDAO itensCarrinhoDAO = new ItensCarrinhoDAO();
    private ItensPedidoDAO itensPedidoDAO = new ItensPedidoDAO();
    private CupomDAO cupomDAO = new CupomDAO();
    
    private Carrinho carrinhoAtual = null;
    
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
                            
                            if ("Administrador".equals(logado.getLogin()))
                            {
                                System.out.println("Usuario Administrador logado");

                                //System.out.println("Prox Menu");
                                int assunto = -1;
                                
                                while(assunto != 0){
                                    assunto = mn.MenuAdm();
                                    
                                    if(assunto != 0) {
                                        int acao = mn.MenuAdm1();
                                        
                                        if(acao != 0) {
                                            this.executarAcao(assunto, acao);
                                        }
                                    }
                                }
                            } else
                            {
                                System.out.println("Usuario comum logado");
                                op1 =0;
                                //Comprar(logado);
                                mn.MenuCliente();

                                //int opADM = mn.MenuAdm();  
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
                        
                    /*case 4:
                        usuarioDAO.mostrarTodos();
                        break;
                    case 5:
                        pessoaDAO.mostrarTodos();
                        break;*/
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
         
        System.out.println("Informe sua data de nascimento (dd/mm/aaaa): ");
        String nascimento = scanner.nextLine();

        // trasformando string em local date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
    
    private void executarAcao(int assunto, int acao) {
        if (assunto == 3) { // Produtos
            switch (acao) {
                case 1: 
                    // Chama seu método de criar produto que está na Trabalho
                    break;
                case 2:
                    produtoDAO.mostrarTodos();
                    break;
            }
        }
        
        if (assunto == 4) { // Produtos
            switch (acao) {
                case 1: 
                    // Chama seu método de criar produto que está na Trabalho
                    break;
                case 2:
                    produtoDAO.mostrarTodos();
                    break;
             
                case 8:
                    System.out.println("Data atual do sistema: " + Util.getAgora());
                    System.out.println("Quantos dias deseja avançar no tempo?");
                    int dias = Integer.parseInt(scanner.nextLine());

                    Util.avancarDias(dias);

                    // Após avançar o tempo, rodamos a verificação de status
                    entregaDAO.AtualizarStatus();

                    System.out.println("O tempo passou... Nova data: " + Util.getAgora());
                    break;
                default:
                    throw new AssertionError();
            }
        }
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
    
    public Pedido CriarPedido(Usuario u, double quantidade, Produto p, double total)
    {
        Pedido pedido = new Pedido();
        pedido.setId_usuario(u);
        pedido.setStatus("CRIADO");
        pedido.setValor_total(total);
        System.out.println("Qual será a forma de pagamento");
        pedido.setForma_pagamento(scanner.nextLine());
        
        return pedido;
        
    }
    
    private ItensPedido CriarItensPedido(Pedido pe, Produto po, int quantidade)
    {
        ItensPedido itenspe = new ItensPedido();
        itenspe.setId_pedido(pe);
        itenspe.setId_produto(po);
        itenspe.setQuantidade(quantidade);
        itenspe.setPreco_unitario(pe.getValor_total()/quantidade);
        itenspe.setSubtotal(pe.getValor_total());
        itensPedidoDAO.Adicionar(itenspe);
        
        return itenspe;
    }
    
    
    
    // Método para garantir que o usuário tenha um carrinho aberto
        private void prepararCarrinho(Usuario logado) {
            if (this.carrinhoAtual == null) {
                this.carrinhoAtual = new Carrinho();
                this.carrinhoAtual.setUsuario(logado);
                this.carrinhoAtual.setStatus("ABERTO");
                carrinhoDAO.Adicionar(carrinhoAtual);
            }
        }
    
        
    public void FinalizarCompra(Produto temp, int qnt, Usuario u)
    {
        if(MovimentacaoDAO.registrarSaida(temp, qnt)){
            Pedido novoPedido = new Pedido();
            novoPedido.setId_usuario(u);
            double total = temp.getPreco_venda() * qnt;
            novoPedido.setStatus("CRIADO");
            System.out.println("Quer adicionar algum cupom?");
            System.out.println("0 - não");
            System.out.println("1 - sim");
            int pcupom = Integer.parseInt(scanner.nextLine());
            LocalDate dataDeHojeNoSistema = Util.getAgora().toLocalDate();
            if(pcupom == 1)
            {
                System.out.println("Insira o codigo do Cupom: ");
                String cod = scanner.nextLine();
                
                 // 2. Aplicar Desconto (CUPOM)
               
                
                Cupom cupom = cupomDAO.buscarPorCodigo(cod);
                
                if (cupom != null && total >= cupom.getValor_minimo_pedido() && cupom.getData_validade().isBefore(dataDeHojeNoSistema)) {

                    if (cupom.getTipo_desconto().equals("FIXO")) {
                        total -= cupom.getValor_desconto();
                    } else {
                        total -= (total * (cupom.getValor_desconto() / 100));
                    }
                    novoPedido.setCupom(cupom);
                }               
            }
            novoPedido.setValor_total(total);
            
            System.out.println("Qual será a forma de pagamento");
            novoPedido.setForma_pagamento(scanner.nextLine());
            novoPedido.setStatus("PAGO");
            
            if(pedidoDAO.adicionar(novoPedido)){
                System.out.println("Pedido realizado com sucesso");
                ItensPedido tempIP = this.CriarItensPedido(novoPedido, temp, qnt);
            } else{
                System.out.println("Erro, não foi possível realizar seu pedido");
            }
                        
            System.out.println("Venda Realizada com sucesso");
            
            
                        
        } else{
            System.out.println("Quantidade muito alta para o produto" + temp.getNome());
        }
    }
        
    
    public void finalizarCarrinho(Produto p, int qnt) {
        
        FinalizarCompra(p, qnt,carrinhoAtual.getUsuario());
        
           

        

        // 4. Fechar Carrinho
        carrinhoAtual.setStatus("FECHADO");
        this.carrinhoAtual = null; // Reseta para a próxima compra
    }

    private Entrega CriarEntrega(Pedido pe, LocalDate data)
    {
        Entrega e = new Entrega();
        e.setId_pedido(pe);
        e.setData_envio(data.plusDays(1));
        e.setData_entrega(data.plusDays(2));
        e.setTransportadora("Sedex");
        e.setStatus("PREPARACAO");
        return e;
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