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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Carrinho;

import model.Usuario;

/**
 *
 * @author W10
 */
public class CarrinhoDAO {
    
    List<Carrinho> carrinhos = getLista();
    
      public Carrinho adicionar(Carrinho elemento) {
        String sql =
        "INSERT INTO Carrinho "
        + "(fk_usuario, status, data_criacao, data_modificacao)"
        + " VALUES (?,?,?,?)";

        try(Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){

            stmt.setLong(1, elemento.getUsuario().getId());
            stmt.setString(2, elemento.getStatus());

            stmt.setTimestamp(3,
                Timestamp.valueOf(elemento.getData_criacao()));

            stmt.setTimestamp(4,
                Timestamp.valueOf(elemento.getData_modificacao()));

            stmt.executeUpdate();

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        //na verdade deveria retornar o elemento que foi inserido agora
        return elemento;
    }
    
    public List<Carrinho> getLista() {

        String sql = "select * from Carrinho";

        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while(rs.next()){

                Carrinho carrinho = new Carrinho();

                carrinho.setId(rs.getLong("id"));

                UsuarioDAO dao = new UsuarioDAO();

                Usuario usuario =
                        dao.buscarPorId(rs.getLong("fk_usuario"));

                carrinho.setUsuario(usuario);

                carrinho.setStatus(rs.getString("status"));
                
                carrinho.setData_criacao(rs.getTimestamp("data_criacao").toLocalDateTime());

                carrinho.setData_modificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());

           

                carrinhos.add(carrinho);

            }

        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return carrinhos;
    }
    
    public Carrinho buscarPorId(long id){
        String sql = "select * from Carrinho where id = ?";
        
        try (Connection con = new ConnectionFactory().getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1,id);
                
            ResultSet rs = stmt.executeQuery();
                
            if(rs.next()){
                Carrinho carrinho = new Carrinho();
                    
                carrinho.setId(rs.getLong("id"));
                
                long idUsuario = rs.getLong("fk_usuario");
                
                UsuarioDAO dao = new UsuarioDAO();
                
                Usuario usuario = dao.buscarPorId(idUsuario);
                
                carrinho.setUsuario(usuario);
                    
                carrinho.setStatus(rs.getString("status"));

                carrinho.setData_criacao(rs.getTimestamp("data_criacao").toLocalDateTime());

                carrinho.setData_modificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());

                return carrinho;
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return null;
    }

    public boolean alterar(Carrinho carrinho) {
        
        String sql = "Uptade Carrinho" 
                + "set fk_usuario = ?"
                + "status = ?"
                + "data_moficacao = ?"
                + "where id = ?";
        try(Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setLong(1,
                carrinho.getUsuario().getId());

            stmt.setString(2,
                carrinho.getStatus());

            stmt.setTimestamp(3,
                Timestamp.valueOf(carrinho.getData_modificacao()));

            stmt.setLong(4,
                carrinho.getId());
            return true;
            
        }catch(SQLException e){
            return false;
        }

    }
    
    public Carrinho Excluir(Carrinho carrinho){
        String sql = "delete from Carrinho where id = ?";
        
        try(Connection con = new ConnectionFactory().getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setLong(1, carrinho.getId());
            
            stmt.execute();
            
            System.out.println("Carrinho excluído");
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return carrinho;
    }
    
    public void Mostrar(){
        carrinhos = getLista();
        for(Carrinho carrinho : carrinhos){
            System.out.println(carrinho.toString());
        }
    }
    
    
    public void verificarCarrinhosExpirados(LocalDateTime agoraSimulado) {
        carrinhos = getLista();
        for (Carrinho carrinho : carrinhos) {
            if (carrinho != null && carrinho.getStatus().equals("ABERTO")) {
                // Calcula a diferença entre a criação e o tempo atual
                long horas = java.time.Duration.between(carrinho.getData_criacao(), agoraSimulado).toHours();

                if (horas >= 24) {
                    carrinho.setStatus("EXPIRADO");
                    carrinho.setData_modificacao(agoraSimulado);
                    System.out.println("Carrinho ID " + carrinho.getId() + " expirou por tempo.");
                }
            }
        }
    }
    
}
