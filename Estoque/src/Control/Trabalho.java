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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import model.Cupom;
import model.ItensPedido;
import model.Pedido;
import model.Carrinho;
import model.ItensCarrinho;

public class Trabalho {

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

    public Trabalho() {
        int op1 = 9999;
        while (op1 != 0) {

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

                    if (logado != null) {

                        if ("Administrador".equals(logado.getLogin())) {
                            System.out.println("Usuario Administrador logado");

                            //System.out.println("Prox Menu");
                            int assunto = -1;

                            while (assunto != 0) {
                                assunto = mn.MenuAdm();

                                if (assunto != 0) {
                                    int acao = mn.MenuAdm1(assunto);

                                    if (acao != 0) {
                                        this.executarAcao(assunto, acao);
                                    }
                                }
                            }
                        } else {
                            System.out.println("Usuario comum logado");
                            op1 = 0;
                            //Comprar(logado);
                            this.gerenciarMenuCliente(logado);

                        }

                        //loop adm ou comum
                    } else {
                        System.out.println("Usuario invalido. Tente novamente");
                    }

                    break;
                case 2:
                    Pessoa temp = this.criaPessoa();
                    if (pessoaDAO.adicionar(temp)) {
                        System.out.println("Pessoa adicionada com sucesso");
                        Usuario tempu = this.criaUsuario(temp);
                        if (usuarioDAO.Adicionar(tempu)) {
                            System.out.println("Usuario adicionado com sucesso");
                        } else {
                            System.out.println("Usuario nao adicionado");
                        }

                    } else {
                        System.out.println("Pessoa nao adicionada");
                    }

                    break;
                case 3:
                    produtoDAO.mostrarTodos();
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

    private Pessoa criaPessoa() {
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

    private Usuario criaUsuario(Pessoa p) {
        Usuario u1 = new Usuario();

        u1.setPessoa(p);

        System.out.println("Informe seu login: ");
        u1.setLogin(scanner.nextLine());

        System.out.println("Informe sua senha: ");
        u1.setSenha(scanner.nextLine());

        return u1;
    }

    private void executarAcao(int assunto, int acao) {
        if (assunto == 1) { // Usuários
            switch (acao) {
                case 1: //criar
                    Pessoa p = criaPessoa();
                    pessoaDAO.adicionar(p);

                    Usuario u = criaUsuario(criaPessoa());
                    usuarioDAO.Adicionar(u);

                    System.out.println("Usuário criado com sucesso!");
                    break;
                case 2: //alterar
                    System.out.println("Digite o Login e Senha do usuário que deseja alterar:");
                    String loginA = scanner.nextLine();
                    String senhaA = scanner.nextLine();

                    Usuario usuarioExistente = usuarioDAO.buscaUsuarioLogin(loginA, senhaA);

                    if (usuarioExistente != null) {
                        System.out.println("Informe o NOVO login: ");
                        String novoLogin = scanner.nextLine();
                        System.out.println("Informe a NOVA senha: ");
                        String novaSenha = scanner.nextLine();

                        usuarioExistente.setLogin(novoLogin);
                        usuarioExistente.setSenha(novaSenha);

                        if (usuarioDAO.alterar(usuarioExistente)) {
                            System.out.println("Usuário removido com sucesso!");
                        } else {
                            System.out.println("Erro ao salvar alterações.");
                        }
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;
                case 3: //deletar
                    System.out.println("Digite o Login do usuário que deseja remover:");
                    String login = scanner.nextLine();

                    if (usuarioDAO.remover(login)) {
                        System.out.println("Usuário removido com sucesso!");
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;
                case 4: //mostrar relatório
                    System.out.println("--- RELATÓRIO GERAL DE USUARIOS ---\n\n");
                    usuarioDAO.mostrarTodos();
                    break;
            }
        }

        if (assunto == 2) { // Pessoas
            switch (acao) {
                case 1: //criar
                    Pessoa p = criaPessoa();
                    pessoaDAO.adicionar(p);
                    System.out.println("Pessoa criada com sucesso!");
                    break;
                case 2:
                    System.out.println("Digite o documento da pessoa que deseja alterar:");
                    String doc = scanner.nextLine();

                    Pessoa pExistente = pessoaDAO.buscarDocumento(doc);
                    if (pExistente != null) {
                        System.out.println("Nome atual: " + pExistente.getNome());
                        System.out.print("Informe o NOVO nome: ");
                        pExistente.setNome(scanner.nextLine());

                        if (pessoaDAO.alterar(pExistente)) {
                            System.out.println("Dados da pessoa atualizados!");
                        }
                    } else {
                        System.out.println("Pessoa não encontrada!");
                    }
                    break;
                case 3:
                    System.out.println("Digite o documento da pessoa que deseja remover:");
                    String docD = scanner.nextLine();

                    if (pessoaDAO.remove(docD)) {
                        System.out.println("Pessoa removida com sucesso!");
                    } else {
                        System.out.println("Pessoa não encontrada!");
                    }
                    break;
                case 4:
                    System.out.println("--- RELATÓRIO GERAL DE PESSOAS ---\n\n");
                    pessoaDAO.mostrarTodos();
                    break;
            }
        }

        if (assunto == 3) { // Produtos
            switch (acao) {
                case 1: //criar
                    Produto p = new Produto();
                    System.out.println("Nome do produto: ");
                    p.setNome(scanner.nextLine());

                    System.out.println("Preço: ");
                    p.setPreco_venda(Double.parseDouble(scanner.nextLine()));

                    System.out.println("Descricao: ");
                    p.setDescricao(scanner.nextLine());

                    p.setData_criacao(Util.getAgora());

                    System.out.println("Para ativar o produto precisa adiciona-lo ao estoque");

                    MovimentacaoEstoque tempME = CriarMovimentacaoEntrada(p);
                    if (MovimentacaoDAO.Adicionar(tempME)) {
                        p.setAtivo(true);
                        produtoDAO.adicionar(p);
                        System.out.println("Adicionado");
                    } else {
                        System.out.println("Não foi possível adicionar esse produto");
                    }

                    break;
                case 2: //alterar
                    produtoDAO.mostrarTodos();
                    System.out.println("Digite o id do produto que deseja alterar:");
                    int idProd = Integer.parseInt(scanner.nextLine());
                    Produto pExistente = produtoDAO.buscarPorId(idProd);
                    if (pExistente != null) {
                        System.out.println("Informe o NOVO nome: ");
                        pExistente.setNome(scanner.nextLine());

                        System.out.println("Informe o NOVA descricao: ");
                        pExistente.setDescricao(scanner.nextLine());

                        System.out.println("Informe o NOVO preco: ");
                        pExistente.setPreco_venda(Double.parseDouble(scanner.nextLine()));

                        System.out.println("O produto está ativo? (1 - Sim / 2 - Não): ");
                        int opAtivo = Integer.parseInt(scanner.nextLine());
                        pExistente.setAtivo(opAtivo == 1);

                        if (produtoDAO.alterar(pExistente)) {
                            System.out.println("Produto alterado com sucesso!");
                        } else {
                            System.out.println("Erro ao alterar produto.");
                        }
                    } else {
                        System.out.println("Produto não encontrado!");
                    }
                    break;
                case 3: //remover
                    produtoDAO.mostrarTodos();
                    System.out.println("Digite o nome do produto que deseja alterar:");
                    String nomeP = scanner.nextLine();

                    if (produtoDAO.remover(nomeP)) {
                        System.out.println("Produto removido com sucesso!");
                    } else {
                        System.out.println("Produto não encontrado!");
                    }
                    break;
                case 4:
                    System.out.println("--- RELATÓRIO GERAL DE PRODUTOS ---\n\n");
                    produtoDAO.mostrarTodos();
                    break;
            }
        }

        if (assunto == 4) { // Cupom
            switch (acao) {
                case 1: //criar
                    System.out.println("--- CADASTRAR NOVO CUPOM ---");

                    System.out.print("Informe o Código (ex: PROMO20): ");
                    String cod = scanner.nextLine();

                    System.out.print("Informe o Tipo de Desconto (ex: Fixo/Percentual): ");
                    String tipo = scanner.nextLine();

                    System.out.print("Informe o Valor do Desconto: ");
                    double valDesc = Double.parseDouble(scanner.nextLine());

                    System.out.print("Informe o Valor Mínimo do Pedido: ");
                    double valMin = Double.parseDouble(scanner.nextLine());

                    System.out.print("Informe a Validade do Cupom (dd/mm/aaaa): ");
                    String dataValStr = scanner.nextLine();
                    DateTimeFormatter dtfCupom = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate validade = LocalDate.parse(dataValStr, dtfCupom);

                    Cupom novoCupom = new Cupom(cod, tipo, valDesc, valMin, validade, Util.getAgora(), Util.getAgora());

                    if (cupomDAO.Adicionar(novoCupom)) {
                        System.out.println("Cupom cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro: Não foi possível cadastrar seu cupom.");
                    }
                    break;
                case 2: //alterar
                    cupomDAO.mostrarTodos();
                    System.out.print("Digite o ID do cupom que deseja alterar: ");
                    int idAlt = Integer.parseInt(scanner.nextLine());
                    Cupom cupomExistente = cupomDAO.buscarPorId(idAlt);

                    if (cupomExistente != null) {
                        System.out.print("Informe o NOVO código: ");
                        cupomExistente.setCodigo(scanner.nextLine());

                        System.out.print("Informe o NOVO valor de desconto: ");
                        cupomExistente.setValor_desconto(Double.parseDouble(scanner.nextLine()));

                        System.out.print("Informe a NOVA Validade do Cupom (dd/mm/aaaa): ");
                        dataValStr = scanner.nextLine();
                        dtfCupom = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        validade = LocalDate.parse(dataValStr, dtfCupom);
                        cupomExistente.setData_validade(validade);

                        if (cupomDAO.alterar(cupomExistente)) {
                            System.out.println("Cupom atualizado com sucesso!");
                        }
                    } else {
                        System.out.println("Erro: Cupom não encontrado.");
                    }
                    break;
                case 3: //remover
                    cupomDAO.mostrarTodos();
                    System.out.println("Digite o Codigo do cupom que deseja remover:");
                    String nomeC = scanner.nextLine();

                    if (cupomDAO.remover(nomeC)) {
                        System.out.println("Cupom removido com sucesso!");
                    } else {
                        System.out.println("Cupom não encontrado!");
                    }
                    break;
                case 4: //relatório
                    cupomDAO.mostrarTodos();
                    break;
            }
        }

        if (assunto == 5) { // Pedido
            switch (acao) {
                case 1: //mudar status
                    System.out.println("Informe o ID do pedido para alterar o status: ");
                    int idPed = Integer.parseInt(scanner.nextLine());
                    Pedido pedidoE = pedidoDAO.buscarPorId(idPed);

                    System.out.println("Informe o NOVO status (CRIADO, PAGO, SEPARACAO, ENVIADO, ENTREGUE, CANCELADO): ");
                    String novoStatus = scanner.nextLine().toUpperCase();
                    pedidoE.setStatus(novoStatus);

                    System.out.println("Status atualizado com sucesso!");
                    break;
                case 2: //ver detalhes
                    System.out.println("Informe o ID do pedido que deseja consultar: ");
                    idPed = Integer.parseInt(scanner.nextLine());
                    pedidoE = pedidoDAO.buscarPorId(idPed);

                    pedidoE.toString();
                    break;
                case 3: //relatório de faturamento
                    System.out.println("--- RELATÓRIO DE FATURAMENTO ---");
                    LocalDateTime agora = Util.getAgora();
                    double faturamentoD = pedidoDAO.calcularFaturamentoDiario(agora);
                    double faturamentoM = pedidoDAO.calcularFaturamentoMensal(agora);
                    double faturamentoA = pedidoDAO.calcularFaturamentoAnual(agora);
                    double faturamento = pedidoDAO.calcularFaturamentoTotal();

                    System.out.println("Data da Consulta: " + agora);
                    System.out.printf("Faturamento do Dia atual: R$ %.2f\n", faturamentoD);
                    System.out.printf("Faturamento do Mês atual: R$ %.2f\n", faturamentoM);
                    System.out.printf("Faturamento do Ano atual: R$ %.2f\n", faturamentoA);
                    System.out.printf("Faturamento Total Acumulado: R$ %.2f\n", faturamento);
                    System.out.println("===============================================");
                    break;
                case 4:
                    System.out.println("--- LISTAGEM GERAL DE PEDIDOS ---");
                    produtoDAO.mostrarTodos();
                    break;
            }
        }

        if (assunto == 6) { // Entrega
            switch (acao) {
                case 1:
                    System.out.println("Informe o ID da Entrega:");
                    int idEnt = Integer.parseInt(scanner.nextLine());
                    Entrega entStatus = entregaDAO.buscarPorId(idEnt);
                    if (entStatus != null) {
                        System.out.println("Informe NOVO Status (PREPARANDO/ENVIADO/EM_TRANSITO/ENTREGUE/CANCELADA): ");
                        entStatus.setStatus(scanner.nextLine().toUpperCase());
                        System.out.println("Status atualizado com sucesso!");
                    }
                    break;
                case 2: //atualizar transportadora
                    System.out.println("Informe o ID da Entrega: ");
                    int idTransp = Integer.parseInt(scanner.nextLine());
                    Entrega entTransp = entregaDAO.buscarPorId(idTransp);
                    if (entTransp != null) {
                        System.out.println("Informe NOVA Transportadora:");
                        entTransp.setTransportadora(scanner.nextLine());
                        System.out.println("Informe NOVO Código de Rastreio:");
                        entTransp.setCodigo_rastreio(scanner.nextLine());
                    }
                    break;
                case 3: //cancelar registro de entrega
                    System.out.println("Informe o ID da Entrega para remover: ");
                    int idRem = Integer.parseInt(scanner.nextLine());

                    if (entregaDAO.remover(idRem)) {
                        System.out.println("Cupom removido com sucesso!");
                    } else {
                        System.out.println("Cupom não encontrado!");
                    }
                    break;
                case 4:
                    System.out.println("--- RELATÓRIO DE ENTREGAS ---");
                    entregaDAO.mostrarTodos();
                    break;
            }
        }

        if (assunto == 7) { // Estoque
            switch (acao) {
                case 1: //registrar entrada
                    System.out.println("ID do Produto para entrada:");
                    int idProd = Integer.parseInt(scanner.nextLine());
                    Produto prod = produtoDAO.buscarPorId(idProd);
                    if (prod != null) {
                        System.out.println("Quantidade de entrada:");
                        int qtd = Integer.parseInt(scanner.nextLine());

                        MovimentacaoEstoque mov = new MovimentacaoEstoque();
                        mov.setProduto(prod);
                        mov.setQuantidade(qtd);
                        mov.setTipo("ENTRADA");
                        mov.setValor_unitario(prod.getPreco_venda());

                        if (MovimentacaoDAO.Adicionar(mov)) {
                            System.out.println("Entrada registrada com sucesso!");
                        }
                    }
                    break;
                case 2: //registro de saída por perda
                    System.out.println("ID do Produto:");
                    int idAjuste = Integer.parseInt(scanner.nextLine());
                    Produto pAjuste = produtoDAO.buscarPorId(idAjuste);
                    if (pAjuste != null) {
                        System.out.println("Quantidade de saída/ajuste:");
                        int qtdAjuste = Integer.parseInt(scanner.nextLine());
                        MovimentacaoDAO.registrarSaida(pAjuste, qtdAjuste);
                        System.out.println("Ajuste realizado.");
                    }
                    break;
                case 3: //saldo por produto
                    System.out.println("ID do Produto:");
                    int idSaldo = Integer.parseInt(scanner.nextLine());
                    Produto pSaldo = produtoDAO.buscarPorId(idSaldo);
                    if (pSaldo != null) {
                        int saldo = MovimentacaoDAO.consultarSaldo(pSaldo);
                        System.out.println("Saldo atual de " + pSaldo.getNome() + ": " + saldo);
                    }
                    break;
                case 4:
                    System.out.println("--- EXTRATO GERAL DE ESTOQUE ---");
                    MovimentacaoDAO.mostrarTodos();
                    break;
            }
        }

        if (assunto == 8) { // Calendário
            switch (acao) {
                case 1:
                    System.out.println("Data atual do sistema: " + Util.getAgora());
                    System.out.println("Quantos dias deseja avançar no tempo?");
                    int dias = Integer.parseInt(scanner.nextLine());

                    Util.avancarDias(dias);

                    // Após avançar o tempo, rodamos a verificação de status
                    carrinhoDAO.verificarCarrinhosExpirados(Util.getAgora());
                    entregaDAO.AtualizarStatus();

                    System.out.println("O tempo passou... Nova data: " + Util.getAgora());
                    break;
                case 2:
                    System.out.println("Data atual: " + Util.getAgora());
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private void gerenciarMenuCliente(Usuario u) {
        int opCliente = -1;
        while (opCliente != 0) {
            // Exibe o menu de opções para o cliente comum
            opCliente = mn.MenuCliente();

            switch (opCliente) {
                case 1: // Comprar Produtos
                    this.Comprar(u);
                    break;

                case 2: // Meu Carrinho
                    if (carrinhoAtual != null && carrinhoAtual.getStatus().equals("ABERTO")) {
                        System.out.println("--- SEU CARRINHO ATUAL ---");
                        System.out.println(carrinhoAtual);

                        // Mostra os itens já adicionados ao carrinho
                        itensCarrinhoDAO.mostrarTodos();

                        System.out.println("\nDeseja gerenciar seu carrinho?");
                        System.out.println("1 - Sim / 0 - Voltar");
                        int gerenciar = Integer.parseInt(scanner.nextLine());

                        if (gerenciar == 1) {
                            this.gerenciarCarrinho(u);
                        }
                    } else {
                        System.out.println("Seu carrinho está vazio ou expirado.");
                    }
                    break;

                case 3: // Meus Pedidos
                    System.out.println("--- SEU HISTÓRICO DE PEDIDOS ---");
                    pedidoDAO.mostrarTodosPorUsuario(u);
                    break;

                case 4: // Cupons
                    System.out.println("--- CUPONS DISPONÍVEIS ---");
                    cupomDAO.mostrarTodos();
                    break;

                case 5: // Meu Usuário
                    System.out.println("--- SEUS DADOS ---");
                    System.out.println(u);
                    break;

                case 0:
                    System.out.println("Saindo da área do cliente...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    private void Comprar(Usuario u) {
        int opC = 99;
        do {

            System.out.println("Qual item deseja comprar?");
            produtoDAO.mostrarCompra();

            int opP = Integer.parseInt(scanner.nextLine());

            int id = opP;
            Produto temp = produtoDAO.buscarPorId(id);
            System.out.println("Qual quantidade: ");
            int qnt = Integer.parseInt(scanner.nextLine());
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
                    FinalizarCompra(temp, qnt, u);
                    ItensCarrinho TempIc = CriarItemCarrinho(carrinhoAtual, temp, qnt);
                    if (itensCarrinhoDAO.Adicionar(TempIc)) {
                        System.out.println("Item Adicionado com sucesso");
                    } else {
                        System.out.println("Não foi possivel adicionar ao carrinho.");
                    }
                    opC = 0;
                    break;
                case 3:
                    prepararCarrinho(u);
                    ItensCarrinho Ic = CriarItemCarrinho(carrinhoAtual, temp, qnt);
                    if (itensCarrinhoDAO.Adicionar(Ic)) {
                        System.out.println(temp.getNome() + "Foi adicionado com sucesso ao carrinho");
                    } else {
                        System.out.println("Não foi possível Adicionar ao carrinho");
                    }

                    opC = 0;

                    break;
                default:
                    System.out.println("Por favor, escolha uma opcao valida\n");
                    break;
            }
        } while (opC != 0);

    }

    public Pedido CriarPedido(Usuario u, double quantidade, Produto p, double total) {
        Pedido pedido = new Pedido();
        pedido.setId_usuario(u);
        pedido.setStatus("CRIADO");
        pedido.setValor_total(total);
        System.out.println("Qual será a forma de pagamento");
        pedido.setForma_pagamento(scanner.nextLine());
        return pedido;

    }

    private ItensPedido CriarItensPedido(Pedido pe, Produto po, int quantidade) {
        ItensPedido itenspe = new ItensPedido();
        itenspe.setId_pedido(pe);
        itenspe.setId_produto(po);
        itenspe.setQuantidade(quantidade);
        itenspe.setPreco_unitario(pe.getValor_total() / quantidade);
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

    private ItensCarrinho CriarItemCarrinho(Carrinho carrinho, Produto p, int quantidade) {
        ItensCarrinho Ic = new ItensCarrinho();
        Ic.setId_carrinho(carrinho);
        Ic.setId_produto(p);
        Ic.setPreco_unitario(p.getPreco_venda());
        Ic.setQuantidade(quantidade);
        return Ic;
    }

    private MovimentacaoEstoque CriarMovimentacaoEntrada(Produto p) {
        MovimentacaoEstoque m = new MovimentacaoEstoque();
        m.setProduto(p);
        System.out.println("Qual sera a quantidade? ");
        m.setQuantidade(Integer.parseInt(scanner.nextLine()));
        m.setTipo("ENTRADA");
        m.setValor_unitario(p.getPreco_venda());
        return m;
    }

    public void FinalizarCompra(Produto temp, int qnt, Usuario u) {
        if (MovimentacaoDAO.registrarSaida(temp, qnt)) {
            Pedido novoPedido = new Pedido();
            novoPedido.setId_usuario(u);
            double total = temp.getPreco_venda() * qnt;
            novoPedido.setStatus("CRIADO");
            System.out.println("Quer adicionar algum cupom?");
            System.out.println("0 - não");
            System.out.println("1 - sim");
            int pcupom = Integer.parseInt(scanner.nextLine());
            LocalDate dataDeHojeNoSistema = Util.getAgora().toLocalDate();
            if (pcupom == 1) {
                System.out.println("Insira o codigo do Cupom: ");
                String cod = scanner.nextLine();

                // 2. Aplicar Desconto (CUPOM)
                Cupom cupom = cupomDAO.buscarPorCodigo(cod);

                if (cupom != null && total >= cupom.getValor_minimo_pedido() && !cupom.getData_validade().isBefore(dataDeHojeNoSistema)) {

                    if (cupom.getTipo_desconto().equals("FIXO")) {
                        total -= cupom.getValor_desconto();
                    } else {
                        total -= (total * (cupom.getValor_desconto() / 100));
                    }
                    novoPedido.setCupom(cupom);
                    
                    System.out.println("O valor após o cupom eh de: R$ " + total);
                } else {
                    System.out.println("Cupom invalido");
                }
            }
            novoPedido.setValor_total(total);

            System.out.println("Qual será a forma de pagamento");
            novoPedido.setForma_pagamento(scanner.nextLine());
            novoPedido.setStatus("PAGO");

            if (pedidoDAO.adicionar(novoPedido)) {
                System.out.println("Pedido realizado com sucesso");
                ItensPedido tempIP = this.CriarItensPedido(novoPedido, temp, qnt);
            } else {
                System.out.println("Erro, não foi possível realizar seu pedido");
            }

            System.out.println("Venda Realizada com sucesso");

            Entrega entregaTemp = CriarEntrega(novoPedido, dataDeHojeNoSistema);
            if (entregaDAO.Adicionar(entregaTemp)) {
                System.out.println("Seu pedido esta em processo de " + entregaTemp.getStatus());
                System.out.println("Sua previsão de chegada no dia " + entregaTemp.getData_entrega());
            } else {
                System.out.println("Nao foi possivel realizar a entrega");
            }

        } else {
            System.out.println("Quantidade muito alta para o produto" + temp.getNome());
        }
    }

    public void finalizarCarrinho(Produto p, int qnt) {

        FinalizarCompra(p, qnt, carrinhoAtual.getUsuario());
        // 4. Fechar Carrinho
        carrinhoAtual.setStatus("FECHADO");
        this.carrinhoAtual = null; // Reseta para a próxima compra
    }

    private Entrega CriarEntrega(Pedido pe, LocalDate data) {
        Entrega e = new Entrega();
        e.setId_pedido(pe);
        e.setData_envio(data.plusDays(1));
        e.setData_entrega(data.plusDays(2));
        e.setTransportadora("Sedex");
        e.setStatus("PREPARACAO");
        return e;
    }

    // No Trabalho.java, dentro do loop de cliente ou no método Comprar
    private void gerenciarCarrinho(Usuario logado) {

        int op = -1;
        while (op != 0) {
            System.out.println("\n--- GERENCIAR CARRINHO (Status: " + carrinhoAtual.getStatus() + ") ---");
            System.out.println("1 - Ver Itens");
            System.out.println("2 - Finalizar Compra");
            System.out.println("3 - Cancelar Carrinho");
            System.out.println("0 - Voltar");
            op = Integer.parseInt(scanner.nextLine());

            switch (op) {
                case 1:
                    itensCarrinhoDAO.mostrarItensDoCarrinho(carrinhoAtual.getId());
                    break;
                case 2:

                    System.out.println("Processando finalização...");
                    ItensCarrinho tempIC = itensCarrinhoDAO.buscarPorCarrinho(carrinhoAtual);
                    finalizarCarrinho(tempIC.get_produto(), tempIC.getQuantidade());
                    op = 0; // Sai após finalizar
                    break;
                case 3:
                    carrinhoAtual.setStatus("CANCELADO");
                    carrinhoAtual.setData_modificacao(Util.getAgora());
                    System.out.println("Carrinho cancelado com sucesso.");
                    this.carrinhoAtual = null; // Remove a referência atual
                    op = 0;
                    break;
            }
        }
    }

}
