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
import java.util.ArrayList;
import java.util.List;
import model.Produto;

/**
 *
 * @author W10
 */
public class ProdutoDAO {
    
    public Produto adicionar(Produto elemento) {
        String sql =
        "INSERT INTO Produto "
        + "(nome, descricao, preco_venda, ativo, data_criacao, data_modificacao)"
        + " VALUES (?,?,?,?,?,?)";

        try(Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setString(1, elemento.getNome());

            stmt.setString(2, elemento.getDescricao());

            stmt.setDouble(3, elemento.getPreco_venda());
            
            stmt.setBoolean(4, elemento.isAtivo());
            

            stmt.setTimestamp(5,
                Timestamp.valueOf(elemento.getData_criacao()));

            stmt.setTimestamp(6,
                Timestamp.valueOf(elemento.getData_modificacao()));

            stmt.executeUpdate();
            
            return elemento;

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        //na verdade deveria retornar o elemento que foi inserido agora
       
    }
    
    public List<Produto> getLista() {

        String sql = "select * from Produto";

        List<Produto> produtos = new ArrayList<>();

        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while(rs.next()){

                Produto produto = new Produto();

                produto.setId(rs.getLong("id"));
                
                produto.setNome(rs.getString("nome"));
                
                produto.setDescricao(rs.getString("descricao"));
                
                produto.setPreco_venda(rs.getDouble("preco_venda"));
                
                produto.setAtivo(rs.getBoolean("ativo"));
                
                produto.setData_criacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                
                produto.setData_modificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());
                
                produtos.add(produto);
            }

        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return produtos;
    }
    
    public Produto buscarPorId(long id){
        String sql = "select * from Produto where id = ?";
        
        try (Connection con = new ConnectionFactory().getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1,id);
                
            ResultSet rs = stmt.executeQuery();
                
            if(rs.next()){
                Produto produto = new Produto();

                produto.setId(rs.getLong("id"));
                
                produto.setNome(rs.getString("nome"));
                
                produto.setDescricao(rs.getString("descricao"));
                
                produto.setPreco_venda(rs.getDouble("preco_venda"));
                
                produto.setAtivo(rs.getBoolean("ativo"));
                
                produto.setData_criacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                
                produto.setData_modificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());

                return produto;
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return null;
    }
    
    public Produto buscarPorNome(String nome){
        String sql = "select * from Produto where nome = ?";
        
        try (Connection con = new ConnectionFactory().getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,nome);
                
            ResultSet rs = stmt.executeQuery();
                
            if(rs.next()){
                Produto produto = new Produto();

                produto.setId(rs.getLong("id"));
                
                produto.setNome(rs.getString("nome"));
                
                produto.setDescricao(rs.getString("descricao"));
                
                produto.setPreco_venda(rs.getDouble("preco_venda"));
                
                produto.setAtivo(rs.getBoolean("ativo"));
                
                produto.setData_criacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                
                produto.setData_modificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());

                return produto;
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return null;
    }
    
    
    public boolean alterar(Produto produto) {
        
        String sql = "Uptade Produto" 
                + "set nome = ?"
                + "descricao = ?"
                + "preco_venda = ?"
                + "ativo = ?"
                + "data_moficacao = ?"
                + "where id = ?";
        try(Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setString(1, produto.getNome());

            stmt.setString(2, produto.getDescricao());

            stmt.setDouble(3, produto.getPreco_venda());
            
            stmt.setBoolean(4, produto.isAtivo());
            
            stmt.setTimestamp(5, Timestamp.valueOf(produto.getData_modificacao()));

            stmt.setLong(7,
                produto.getId());
            return true;
            
        }catch(SQLException e){
            return false;
        }

    }
    
    public Produto Excluir(Produto produto){
        String sql = "delete from Produto where id = ?";
        
        try(Connection con = new ConnectionFactory().getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setLong(1, produto.getId());
            
            stmt.execute();
            
            System.out.println("Produto excluído");
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return produto;
    }
    
    public void Mostrar(){
        List<Produto> produtos = getLista();
        for(Produto produto : produtos){
            System.out.println(produto.toString());
        }
    }
    
    public void MostrarCompra(){
        List<Produto> produtos = getLista();
        for(Produto produto : produtos){
            System.out.println(produto.getId() +" - "+ produto.getNome());
        }
    }
}
