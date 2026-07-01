/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import model.Pessoa;
import connection.ConnectionFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {
    
     List<Pessoa> pessoas = getLista();
     public Pessoa adicionar(Pessoa elemento) {
        String sql = "insert into Pessoa "
                + "(nome,nascimento,documento,data_criacao,data_modificacao )" + " values (?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            // seta os valores
            stmt.setString(1, elemento.getNome());
            stmt.setDate(2, java.sql.Date.valueOf(elemento.getNascimento()));
            stmt.setString(3, elemento.getDocumento());
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(elemento.getData_criacao()));
            stmt.setTimestamp(5, java.sql.Timestamp.valueOf(elemento.getData_modificacao()));
            
            stmt.execute();
            
            System.out.println("Elemento inserido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //na verdade deveria retornar o elemento que foi inserido agora
        return elemento;
    }
    
    public List<Pessoa> getLista() {

        String sql = "select * from Pessoa";

       

        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while(rs.next()) {

                Pessoa pessoa = new Pessoa();

                pessoa.setId(rs.getLong("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setDocumento(rs.getString("documento"));

                pessoa.setNascimento(rs.getDate("nascimento").toLocalDate());

                pessoa.setData_criacao(rs.getTimestamp("data_criacao").toLocalDateTime());

                pessoa.setData_modificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());

                pessoas.add(pessoa);
            }

        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return pessoas;
    }
    
    public Pessoa buscarPorId(long id){
        String sql = "select * from Pessoa where id = ?";
        
        try (Connection con = new ConnectionFactory().getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1,id);
                
            ResultSet rs = stmt.executeQuery();
                
            if(rs.next()){
                Pessoa pessoa = new Pessoa();
                    
                pessoa.setId(rs.getLong("id"));
                    
                pessoa.setNome(rs.getString("nome"));
                    
                pessoa.setDocumento(rs.getString("documento"));
                    
                pessoa.setNascimento(rs.getDate("nascimento").toLocalDate());

                pessoa.setData_criacao(rs.getTimestamp("data_criacao").toLocalDateTime());

                pessoa.setData_modificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());

                return pessoa;
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return null;
    }
    
     public Pessoa buscarDocumento(String doc){
        String sql = "select * from Pessoa where documento = ?";
        
        try (Connection con = new ConnectionFactory().getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,doc);
                
            ResultSet rs = stmt.executeQuery();
                
            if(rs.next()){
                Pessoa pessoa = new Pessoa();
                    
                pessoa.setId(rs.getLong("id"));
                    
                pessoa.setNome(rs.getString("nome"));
                    
                pessoa.setDocumento(rs.getString("documento"));
                    
                pessoa.setNascimento(rs.getDate("nascimento").toLocalDate());

                pessoa.setData_criacao(rs.getTimestamp("data_criacao").toLocalDateTime());

                pessoa.setData_modificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());

                return pessoa;
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return null;
    }
    
    
    public void alterar(Pessoa pessoa) {
        
        String sql = "Uptade Pessoa" 
                + "set nome = ?"
                + "nascimento = ?"
                + "documento = ?"
                + "data_moficacao = ?"
                + "where id = ?";
        try(Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setString(1, pessoa.getNome());
            stmt.setDate(2, java.sql.Date.valueOf(pessoa.getNascimento()));
            stmt.setString(3, pessoa.getDocumento());
            
            stmt.setTimestamp(4, 
                    java.sql.Timestamp.valueOf(
                    pessoa.getData_modificacao()));
            stmt.setLong(5, pessoa.getId());
            
            stmt.execute();
            
            System.out.println("Pessoa alterada com sucesso.");
            
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }
    
    public Pessoa Excluir(Pessoa pessoa){
        String sql = "delete from Pessoa where id = ?";
        
        try(Connection con = new ConnectionFactory().getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setLong(1, pessoa.getId());
            
            stmt.execute();
            
            System.out.println("Pessoa excluída");
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return pessoa;
    }
    
    public void Mostrar(){
        pessoas = getLista();
        for(Pessoa pessoa : pessoas){
            System.out.println(pessoa.toString());
        }
    }
}
