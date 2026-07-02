/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Carrinho;
import model.ItensCarrinho;
import model.Usuario;
import model.Produto;

/**
 *
 * @author W10
 */
public class ItensCarrinhoDAO {
    
 
   
    public boolean adicionar(ItensCarrinho elemento) {
        String sql =
        "INSERT INTO Itens_carrinho "
        + "(fk_carrinho, fk_produto, quantidade, preco_unitario, data_criacao, data_modificacao)"
        + " VALUES (?,?,?,?,?,?)";

        try(Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)){

            stmt.setLong(1, elemento.get_carrinho().getId());
            stmt.setLong(2, elemento.get_produto().getId());
            stmt.setInt(3, elemento.getQuantidade());
            stmt.setDouble(4, elemento.getPreco_unitario());
            stmt.setTimestamp(5, Timestamp.valueOf(elemento.getData_criacao()));
            stmt.setTimestamp(6, Timestamp.valueOf(elemento.getData_modificacao()));

            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    elemento.setId(rs.getLong(1));
                }
            }
            return true;
        } catch(SQLException e){
            System.out.println("Erro ao adicionar item: " + e.getMessage());
            return false;
        }
    }
    
    public List<ItensCarrinho> getLista() {

        String sql = "select * from Itens_carrinho";
        
           List<ItensCarrinho> itensc = new ArrayList<>();

        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            CarrinhoDAO dao = new CarrinhoDAO();
            ProdutoDAO Pdao = new ProdutoDAO();
            while(rs.next()){

                ItensCarrinho item = new ItensCarrinho();

                item.setId(rs.getLong("id"));

                Carrinho carrinho =
                        dao.buscarPorId(rs.getLong("fk_carrinho"));

                item.setId_carrinho(carrinho);
               
                Produto produto =
                        Pdao.buscarPorId(rs.getLong("fk_produto"));

                item.setId_produto(produto);
                
                item.setQuantidade(rs.getInt("quantidade"));

                item.setPreco_unitario(rs.getDouble("preco_unitario"));
                
                item.setData_criacao(rs.getTimestamp("data_criacao").toLocalDateTime());

                item.setData_modificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());

           

                itensc.add(item);

            }

        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return itensc;
    }
    
    public ItensCarrinho buscarPorId(long id){
        String sql = "select * from Itens_carrinho where id = ?";
        
        try (Connection con = new ConnectionFactory().getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1,id);
                
            ResultSet rs = stmt.executeQuery();
                
            if(rs.next()){
                ItensCarrinho item = new ItensCarrinho();

                item.setId(rs.getLong("id"));

                CarrinhoDAO dao = new CarrinhoDAO();

                Carrinho carrinho =
                        dao.buscarPorId(rs.getLong("fk_carrinho"));

                item.setId_carrinho(carrinho);
                
                ProdutoDAO Pdao = new ProdutoDAO();

                Produto produto =
                        Pdao.buscarPorId(rs.getLong("fk_produto"));

                item.setId_produto(produto);
                
                item.setQuantidade(rs.getInt("quantidade"));

                item.setPreco_unitario(rs.getDouble("preco_unitario"));
                
                item.setData_criacao(rs.getTimestamp("data_criacao").toLocalDateTime());

                item.setData_modificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());

                return item;
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return null;
    }
    
    public ItensCarrinho buscarPorCarrinho(long id){
        String sql = "select * from Itens_carrinho where fk_carrinho = ?";
        
        try (Connection con = new ConnectionFactory().getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1,id);
                
            ResultSet rs = stmt.executeQuery();
                
            if(rs.next()){
                ItensCarrinho item = new ItensCarrinho();

                item.setId(rs.getLong("id"));

                CarrinhoDAO dao = new CarrinhoDAO();

                Carrinho carrinho =
                        dao.buscarPorId(rs.getLong("fk_carrinho"));

                item.setId_carrinho(carrinho);
                
                ProdutoDAO Pdao = new ProdutoDAO();

                Produto produto =
                        Pdao.buscarPorId(rs.getLong("fk_produto"));

                item.setId_produto(produto);
                
                item.setQuantidade(rs.getInt("quantidade"));

                item.setPreco_unitario(rs.getDouble("preco_unitario"));
                
                item.setData_criacao(rs.getTimestamp("data_criacao").toLocalDateTime());

                item.setData_modificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());

                return item;
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return null;
    }

    public boolean alterar(ItensCarrinho itens) {
        
        String sql = "UPDATE Itens_carrinho SET " 
                + "fk_carrinho = ?, "
                + "set fk_produto = ?, "
                + "quantidade = ?, "
                + "preco_unitario = ?, "
                + "data_modificacao = ? "
                + "where id = ?";
        try(Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setLong(1, itens.get_carrinho().getId());
            stmt.setLong(2, itens.get_produto().getId());
            
            stmt.setInt(3, itens.getQuantidade());
            stmt.setDouble(4, itens.getPreco_unitario());

            stmt.setTimestamp(5,
                Timestamp.valueOf(itens.getData_modificacao()));

            stmt.setLong(6,
                itens.getId());
            
            stmt.executeUpdate();
            return true;
            
        }catch(SQLException e){
            return false;
        }

    }
    
    public ItensCarrinho Excluir(ItensCarrinho itens){
        String sql = "delete from Itens_carrinho where id = ?";
        
        try(Connection con = new ConnectionFactory().getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setLong(1, itens.getId());
            
            stmt.execute();
            
            System.out.println("Carrinho excluído");
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return itens;
    }
    
    public void mostrar(){
        List<ItensCarrinho>itensc = getLista();
        for(ItensCarrinho item : itensc){
            System.out.println(item.toString());
        }
    }
    
    
    // No ItensCarrinhoDAO.java
        public void mostrarItensDoCarrinho(long idCarrinho) {
            List<ItensCarrinho>itensc = getLista();
            boolean temItens = false;
            System.out.println("--- ITENS NO SEU CARRINHO ---");
            for (ItensCarrinho ic : itensc) {
                if (ic != null && ic.get_carrinho().getId() == idCarrinho) {
                    System.out.println("Produto: " + ic.get_produto().getNome() + 
                                       " | Qtd: " + ic.getQuantidade() + 
                                       " | Preço Unit: R$" + ic.getPreco_unitario());
                    temItens = true;
                }
            }
            if (!temItens) {
                System.out.println("O carrinho está vazio.");
            }
        }
}
