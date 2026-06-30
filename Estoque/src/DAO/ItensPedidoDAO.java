/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Cupom;
import model.ItensPedido;
import model.Pedido;
import model.Produto;
/**
 *
 * @author Thalita
 */
public class ItensPedidoDAO {
    public ItensPedido adicionar(ItensPedido elemento) {
        String sql =
        "INSERT INTO Itens_pedido "
        + "(fk_pedido, fk_produto, quantidade, preco_unitario, subtotal, data_criacao, data_modificacao)"
        + " VALUES (?,?,?,?,?,?,?)";

        try(Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setLong(1,
                elemento.get_pedido().getId());
            
            stmt.setLong(2,
                elemento.get_produto().getId());
            
            stmt.setInt(3, elemento.getQuantidade());
            
            stmt.setDouble(4, elemento.getPreco_unitario());
            
            stmt.setDouble(5, elemento.getSubtotal());

            stmt.setTimestamp(6,
                Timestamp.valueOf(elemento.getData_criacao()));

            stmt.setTimestamp(7,
                Timestamp.valueOf(elemento.getData_modificacao()));

            stmt.executeUpdate();
            
            return elemento;

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        //na verdade deveria retornar o elemento que foi inserido agora
       
    }
    
    public List<ItensPedido> getLista() {

        String sql = "select * from Itens_pedido";

        List<ItensPedido> itensPedidos = new ArrayList<>();

        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while(rs.next()){

                ItensPedido itens = new ItensPedido();

                itens.setId(rs.getLong("id"));
                
                PedidoDAO pdao =  new PedidoDAO();
                Pedido pedido = pdao.buscarPorId(rs.getLong("fk_pedido"));
                itens.setPedido(pedido);
                
                ProdutoDAO prodao = new ProdutoDAO();
                Produto produto = prodao.buscarPorId(rs.getLong("fk_produto"));
                itens.setProduto(produto);
                
                itens.setQuantidade(rs.getInt("quantidade"));
                
                itens.setPreco_unitario(rs.getDouble("preco_unitario"));
                
                itens.setSubtotal(rs.getDouble("subtotal"));
                
                itens.setData_criacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                itens.setData_modificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());
                
                itensPedidos.add(itens);
            }

        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return itensPedidos;
    }
    
    public ItensPedido buscarPorId(long id){
        String sql = "select * from Itens_pedido where id = ?";
        
        try (Connection con = new ConnectionFactory().getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1,id);
                
            ResultSet rs = stmt.executeQuery();
                
            if(rs.next()){
                ItensPedido itens = new ItensPedido();

                itens.setId(rs.getLong("id"));
                
                PedidoDAO pdao =  new PedidoDAO();
                Pedido pedido = pdao.buscarPorId(rs.getLong("fk_pedido"));
                itens.setPedido(pedido);
                
                ProdutoDAO prodao = new ProdutoDAO();
                Produto produto = prodao.buscarPorId(rs.getLong("fk_produto"));
                itens.setProduto(produto);
                
                itens.setQuantidade(rs.getInt("quantidade"));
                
                itens.setPreco_unitario(rs.getDouble("preco_unitario"));
                
                itens.setSubtotal(rs.getDouble("subtotal"));
                
                itens.setData_criacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                
                itens.setData_modificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());

                return itens;
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return null;
    }
    
    
    public boolean alterar(ItensPedido itens) {
        
        String sql = "Uptade Itens_pedido" 
                + "set fk_pedido = ?"
                + "fk_produto = ?"
                + "quantidade = ?"
                + "preco_unitario = ?"
                + "subtotal = ?"
                + "data_moficacao = ?"
                + "where id = ?";
        try(Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setLong(1,
                itens.get_pedido().getId());
            
            stmt.setLong(2,
                itens.get_produto().getId());
            
            stmt.setInt(3, itens.getQuantidade());
            
            stmt.setDouble(4, itens.getPreco_unitario());
            
            stmt.setDouble(5, itens.getSubtotal());

            stmt.setTimestamp(6,
                Timestamp.valueOf(itens.getData_modificacao()));
            
             stmt.setLong(7,
                itens.getId());
             
            return true;
            
        }catch(SQLException e){
            return false;
        }

    }
    
    public ItensPedido Excluir(ItensPedido itens){
        String sql = "delete from Itens_pedido where id = ?";
        
        try(Connection con = new ConnectionFactory().getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setLong(1, itens.getId());
            
            stmt.execute();
            
            System.out.println("Itens do pedido excluído");
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return itens;
    }
    
    public void Mostrar(){
        List<ItensPedido> itensPedidos = getLista();
        for(ItensPedido itens : itensPedidos){
            System.out.println(itens.toString());
        }
    }
    
   
}
