/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Util.Util;
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

/**
 *
 * @author W10
 */
public class CupomDAO {
    
    
    

    public CupomDAO()
    {
        /*LocalDate dataDeHojeNoSistema = Util.getAgora().toLocalDate();
        Cupom c1 = new Cupom();
        c1.setCodigo("PROMO1");
        c1.setData_validade(dataDeHojeNoSistema.plusDays(2));
        c1.setValor_desconto(5);
        c1.setTipo_desconto("FIXO");
        c1.setValor_minimo_pedido(10.00);
        this.Adicionar(c1);
        
        Cupom c2 = new Cupom();
        c2.setCodigo("PROMO2");
        c2.setData_validade(dataDeHojeNoSistema.plusDays(2));
        c2.setValor_desconto(5);
        c2.setTipo_desconto("PERCENTUAL");
        c2.setValor_minimo_pedido(10.00);
        this.Adicionar(c2);*/
    }

    public Cupom adicionar(Cupom elemento) {
        String sql =
        "INSERT INTO Cupom "
        + "(codigo, tipo_desconto, valor_minimo_pedido, data_validade, ativo, data_criacao, data_modificacao)"
        + " VALUES (?,?,?,?,?,?,?)";

        try(Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){

            stmt.setString(1, elemento.getCodigo());
            stmt.setString(2, elemento.getTipo_desconto());
            stmt.setDouble(3, elemento.getValor_minimo_pedido());
            
            stmt.setDate(4, Date.valueOf(elemento.getData_validade()));
            stmt.setBoolean(5, elemento.isAtivo());
            

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
    
    public List<Cupom> getLista() {

        String sql = "select * from Cupom";
        
            List<Cupom> cupons = new ArrayList<>();

        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while(rs.next()){

                Cupom cupom = new Cupom();

                cupom.setId(rs.getLong("id"));

                cupom.setCodigo(rs.getString("codigo"));
                cupom.setTipo_desconto(rs.getString("tipo_desconto"));
                cupom.setValor_minimo_pedido(rs.getDouble("valor_minimo_pedido"));

                cupom.setData_validade(rs.getDate("data_validade").toLocalDate());
                cupom.setAtivo(rs.getBoolean("ativo"));
                cupom.setData_criacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                cupom.setData_modificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());
                
                cupons.add(cupom);
            }

        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return cupons;
    }
    
    public Cupom buscarPorId(long id){
        String sql = "select * from Cupom where id = ?";
        
        try (Connection con = new ConnectionFactory().getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1,id);
                
            ResultSet rs = stmt.executeQuery();
                
            if(rs.next()){
                Cupom cupom = new Cupom();

                cupom.setId(rs.getLong("id"));

                cupom.setCodigo(rs.getString("codigo"));
                cupom.setTipo_desconto(rs.getString("tipo_desconto"));
                cupom.setValor_minimo_pedido(rs.getDouble("valor_minimo_pedido"));

                cupom.setData_validade(rs.getDate("data_validade").toLocalDate());
                cupom.setAtivo(rs.getBoolean("ativo"));
                cupom.setData_criacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                cupom.setData_modificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());
                

                return cupom;
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return null;
    }
    
    public Cupom buscarPorCodigo(String codigo){
        String sql = "select * from Cupom where codigo = ?";
        
        try (Connection con = new ConnectionFactory().getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,codigo);
                
            ResultSet rs = stmt.executeQuery();
                
            if(rs.next()){
                Cupom cupom = new Cupom();

                cupom.setId(rs.getLong("id"));

                cupom.setCodigo(rs.getString("codigo"));
                cupom.setTipo_desconto(rs.getString("tipo_desconto"));
                cupom.setValor_minimo_pedido(rs.getDouble("valor_minimo_pedido"));

                cupom.setData_validade(rs.getDate("data_validade").toLocalDate());
                cupom.setAtivo(rs.getBoolean("ativo"));
                cupom.setData_criacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                cupom.setData_modificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());
                

                return cupom;
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return null;
    }
    
    
    public boolean alterar(Cupom cupom) {
        
        String sql = "UPDATE Cupom SET " 
                + "codigo = ?, "
                + "tipo_desconto = ?, "
                + "valor_minimo_pedido = ?, "
                + "data_validade = ?, "
                + "ativo = ?, "
                + "data_modificacao = ? "
                + "where id = ?";
        try(Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setString(1,
                cupom.getCodigo());

            stmt.setString(2,
                cupom.getTipo_desconto());

            stmt.setDouble(3,
                cupom.getValor_minimo_pedido());
            
            stmt.setDate(4,
                    Date.valueOf(cupom.getData_validade()));
            
            stmt.setBoolean(5, cupom.isAtivo()); 
            
            stmt.setTimestamp(6,
                Timestamp.valueOf(cupom.getData_modificacao()));

            stmt.setLong(7,
                cupom.getId());
            
            int linhas = stmt.executeUpdate();

            return linhas > 0;
            
        }catch(SQLException e){
            //e.printStackTrace();
            return false;
        }

    }
    
    public Cupom Excluir(Cupom cupom){
        String sql = "delete from Cupom where id = ?";
        
        try(Connection con = new ConnectionFactory().getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setLong(1, cupom.getId());
            
            stmt.execute();
            
            System.out.println("Cupom excluído");
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return cupom;
    }
    
    public void Mostrar(){
        List<Cupom> cupons = getLista();
        for(Cupom cupom : cupons){
            System.out.println(cupom.toString());
        }
    }
}
