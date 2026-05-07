/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Control;


import DAO.PessoaDAO;
import DAO.ProdutoDAO;
import DAO.UsuarioDAO;
import DAO.MovimentacaoEstoqueDAO;

import model.Pessoa;
import model.Produto;
import model.MovimentacaoEstoque;
import model.Usuario;

import view.Menu;

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
    
    private PessoaDAO pessoaDAO = new PessoaDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO(pessoaDAO);
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private MovimentacaoEstoqueDAO MovimentacaoDAO = new MovimentacaoEstoqueDAO(produtoDAO);
    
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
                                Comprar();
                                
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
    
    private void Comprar()
    {
        int opC = 99;
        do{
            opC = mn.MenuCompras();

            switch (opC) {
                case 0:
                    System.out.println("0 - Sair do programa");
                    break;
                case 1:
                    System.out.println("1 - Mostrar Produtos\n\n");
                    produtoDAO.mostrarTodos();
                break;
                case 2:
                    System.out.println("2 - Comprar");
                    System.out.println("Qual quantidade deseja comprar?");
                    produtoDAO.mostrarCompra();
                    System.out.println("Digite sua opcao: ");
                    int opP = Integer.parseInt(scanner.nextLine());
                    
                    int id = opP;
                    Produto temp = produtoDAO.buscarPorId(id);
                    System.out.println("Qual quantidade: ");
                    int qnt = Integer.parseInt(scanner.nextLine());
                    
                    if(MovimentacaoDAO.registrarSaida(temp, qnt)){
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
}

/*Comprar:

do{
Mostrar os produtos
Sout("Qual produto deseja comprar?");

for(int i=0; i<p.lenght; i++){
	if(produto[i] != null){
   	  sout(i+1 + " - " + produto[i].GetNome());
	}
}

 op = Integrer.ParseInt(scanner.NextLine());

id = op -1;
Produto temp = produtoDAO.BuscaPorId(id);

Sout("Qual a quantidade?");
int qnt = Integrer.ParseInt(scanner.NextLine());
EstoqueProduto = MovimentaçãoDAO.BuscarPorProduto(temp);
if(EstoqueProduto.DiminuirEstoque(qnt))
{
	Sout("1 - Comprar agora"); // Cria o Pedido
	Sout("2 - Adicionar ao carrinho");//Cria o Carrinho e Itens Carrinho e Pedido
	Sout("3 - Cancelar a compra"); // Desfaz o Diminuir quantidade do Diminuir estoque
	Sout("Como deseja prosseguir: ");
	int opCm = Integrer.ParseInt(scanner.NextLine());
	
	
} else{
	Sout("Quantidade muito alta, temos apenas" + EstoqueProduto.GetQuantidade());
}


}while(op=!0);

//Dentro da MovimentaçãoDAO
Public boolean DiminuirEstoque(int qnt){
if(qnt <= this.quantidade){
	set.quantidade -= qnt;
	Status = "Modificado";
	return true;
} else{
	return false;
}

}*/