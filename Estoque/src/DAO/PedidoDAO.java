/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.time.LocalDateTime;
import model.Pedido;
import model.Usuario;
import model.Cupom;
import Util.Util;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

            List<Pedido> pedidos = getLista();
    public Pedido adicionar(Pedido elemento) {
        String sql =
            "INSERT INTO Pedido "
          + "(fk_usuario,fk_cupom, status, valor_total, forma_pagamento,"
          + " data_criacao, data_modificacao)"
          + " VALUES (?,?,?,?,?,?,?)";

        try(Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt =
                    con.prepareStatement(sql)){

            stmt.setLong(1,
                elemento.getUsuario().getId());
            
            if (elemento.getCupom() != null) {
                stmt.setLong(2, elemento.getCupom().getId());
            } else {
                stmt.setNull(2, java.sql.Types.BIGINT);
            }

            stmt.setString(3,
                elemento.getStatus());

            stmt.setDouble(4,
                elemento.getValor_total());

            stmt.setString(5,
                elemento.getForma_pagamento());

            stmt.setTimestamp(6,
                Timestamp.valueOf(
                    elemento.getData_criacao()));

            stmt.setTimestamp(7,
                Timestamp.valueOf(
                    elemento.getData_modificacao()));

            stmt.executeUpdate();

            return elemento;

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public List<Pedido> getLista() {

        String sql = "select * from Pedido";

        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while(rs.next()){

                Pedido pedido = new Pedido();

                pedido.setId(rs.getLong("id"));
                UsuarioDAO Udao =  new UsuarioDAO();
                
                Usuario usuario = Udao.buscarPorId(rs.getLong("fk_usuario"));
                pedido.setUsuario(usuario);

                long idCupom = rs.getLong("fk_cupom");

                if (!rs.wasNull()) {
                    CupomDAO cupomDAO = new CupomDAO();
                    pedido.setCupom(cupomDAO.buscarPorId(idCupom));
                } else {
                    pedido.setCupom(null);
                }
                
                pedido.setStatus(rs.getString("status"));
                
                pedido.setValor_total(rs.getDouble("valor_total"));
                
                pedido.setForma_pagamento(rs.getString("forma_pagamento"));
                
                pedido.setData_criacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                
                pedido.setData_modificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());

                pedidos.add(pedido);

            }

        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return pedidos;
    }
    
    public Pedido buscarPorId(long id){
        String sql = "select * from Pedido where id = ?";
        
        try (Connection con = new ConnectionFactory().getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1,id);
                
            ResultSet rs = stmt.executeQuery();
                
            if(rs.next()){
                Pedido pedido = new Pedido();

                pedido.setId(rs.getLong("id"));
                UsuarioDAO Udao =  new UsuarioDAO();
                
                Usuario usuario = Udao.buscarPorId(rs.getLong("fk_usuario"));
                pedido.setUsuario(usuario);

                long idCupom = rs.getLong("fk_cupom");

                if (!rs.wasNull()) {
                    CupomDAO cupomDAO = new CupomDAO();
                    pedido.setCupom(cupomDAO.buscarPorId(idCupom));
                } else {
                    pedido.setCupom(null);
                }
                
                pedido.setStatus(rs.getString("status"));
                
                pedido.setValor_total(rs.getDouble("valor_total"));
                
                pedido.setForma_pagamento(rs.getString("forma_pagamento"));
                
                pedido.setData_criacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                
                pedido.setData_modificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());

               
                return pedido;
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return null;
    }
    
    public boolean alterar(Pedido pedido) {
        
        String sql = "Uptade Pedido" 
                + "set fk_usuario = ?"
                + "fk_cupom = ?"
                + "status = ?"
                + "valor_total = ?"
                + "forma_pagamento = ?"
                + "data_moficacao = ?"
                + "where id = ?";
        try(Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setLong(1,
                pedido.getUsuario().getId());
            
            if (pedido.getCupom() != null) {
                stmt.setLong(2, pedido.getCupom().getId());
            } else {
                stmt.setNull(2, java.sql.Types.BIGINT);
            }

            stmt.setString(3,
                pedido.getStatus());
            
            stmt.setDouble(4,
                pedido.getValor_total());
            
            stmt.setString(5,
                pedido.getForma_pagamento());
            
            stmt.setTimestamp(6, Timestamp.valueOf(pedido.getData_modificacao()));

            stmt.setLong(7,
                pedido.getId());
            return true;
            
        }catch(SQLException e){
            return false;
        }

    }
    
    public Pedido Excluir(Pedido pedido){
        String sql = "delete from Pedido where id = ?";
        
        try(Connection con = new ConnectionFactory().getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setLong(1, pedido.getId());
            
            stmt.execute();
            
            System.out.println("Usuario excluído");
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return pedido;
    }
    
    public void Mostrar(){
        pedidos = getLista();
        for(Pedido pedido : pedidos){
            System.out.println(pedido.toString());
        }
    }
    
    public List<Pedido> buscarPorUsuario(Usuario usuario) {

        String sql = "SELECT * FROM Pedido WHERE fk_usuario = ?";

        pedidos = new ArrayList<>();

        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, usuario.getId());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Pedido pedido = new Pedido();

                pedido.setId(rs.getLong("id"));

                // O usuário já é conhecido
                pedido.setUsuario(usuario);

                // Se existir cupom
                long idCupom = rs.getLong("fk_cupom");

                if (!rs.wasNull()) {
                    CupomDAO cupomDAO = new CupomDAO();
                    pedido.setCupom(cupomDAO.buscarPorId(idCupom));
                }

                pedido.setStatus(rs.getString("status"));
                pedido.setValor_total(rs.getDouble("valor_total"));
                pedido.setForma_pagamento(rs.getString("forma_pagamento"));

                Timestamp criacao = rs.getTimestamp("data_criacao");
                if (criacao != null) {
                    pedido.setData_criacao(criacao.toLocalDateTime());
                }

                Timestamp modificacao = rs.getTimestamp("data_modificacao");
                if (modificacao != null) {
                    pedido.setData_modificacao(modificacao.toLocalDateTime());
                }

                pedidos.add(pedido);
            }

            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pedidos;
    }
    
    public void mostrarTodosPorUsuario(Usuario usuario) {

        pedidos = buscarPorUsuario(usuario);

        if (pedidos.isEmpty()) {
            System.out.println("\nVocê ainda não possui pedidos registrados.\n");
            return;
        }

        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
    }

    public double calcularFaturamentoTotal() {
        String sql = """
            SELECT SUM(valor_total) AS total
            FROM Pedido
            WHERE status <> 'CRIADO'
              AND status <> 'CANCELADO'
            """;

        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble("total");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    public double calcularFaturamentoDiario(LocalDate data) {
          String sql = """
            SELECT SUM(valor_total) AS total
            FROM Pedido
            WHERE DATE(data_criacao) = ?
              AND status <> 'CRIADO'
              AND status <> 'CANCELADO'
            """;

        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(data));

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("total");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    public double calcularFaturamentoMensal(LocalDate data) {
        String sql = """
            SELECT SUM(valor_total) AS total
            FROM Pedido
            WHERE MONTH(data_criacao) = ?
              AND YEAR(data_criacao) = ?
              AND status <> 'CRIADO'
              AND status <> 'CANCELADO'
            """;

        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, data.getMonthValue());
            stmt.setInt(2, data.getYear());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("total");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    public double calcularFaturamentoAnual(LocalDate data) {
       String sql = """
            SELECT SUM(valor_total) AS total
            FROM Pedido
            WHERE YEAR(data_criacao) = ?
              AND status <> 'CRIADO'
              AND status <> 'CANCELADO'
            """;

        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, data.getYear());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("total");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }
    
    public void AtualizarStatus()
    {
        LocalDateTime hoje = Util.getAgora();
    
        List<Pedido> pedidos = getLista();
        for(Pedido e : pedidos) {
            if (e != null) {
                // Calcula a diferença em horas entre a última mudança e o "agora" simulado
                long horasPassadas = java.time.Duration.between(e.getData_modificacao(), hoje).toHours();

                if (horasPassadas >= 24) {
                    switch (e.getStatus()) {
                        case "PAGO":
                            e.setStatus("ENVIADO");
                            //p.setDataModificacao(hoje);
                            break;
                        case "ENVIADO":
                            e.setStatus("ENTREGUE");
                            //p.setDataModificacao(hoje);
                            break;

                    }
                }
            }
        }
    }
    
     public List<Pedido> buscarPorStatus(String status) {

        List<Pedido> lista = new ArrayList<>();

        String sql = "SELECT * FROM Pedido WHERE status = ?";

        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, status);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Pedido pedido = new Pedido();

                pedido.setId(rs.getLong("id"));

                UsuarioDAO udao = new UsuarioDAO();
                pedido.setUsuario(udao.buscarPorId(rs.getLong("fk_usuario")));

                pedido.setStatus(rs.getString("status"));
                pedido.setValor_total(rs.getDouble("valor_total"));
                pedido.setForma_pagamento(rs.getString("forma_pagamento"));

                pedido.setData_criacao(
                        rs.getTimestamp("data_criacao").toLocalDateTime());

                pedido.setData_modificacao(
                        rs.getTimestamp("data_modificacao").toLocalDateTime());

                lista.add(pedido);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
    
    public List<Pedido> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {

        List<Pedido> lista = new ArrayList<>();

        String sql = """
                SELECT *
                FROM Pedido
                WHERE DATE(data_criacao)
                BETWEEN ? AND ?
                """;

        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(inicio));
            stmt.setDate(2, java.sql.Date.valueOf(fim));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Pedido pedido = new Pedido();

                pedido.setId(rs.getLong("id"));

                UsuarioDAO udao = new UsuarioDAO();
                pedido.setUsuario(udao.buscarPorId(rs.getLong("fk_usuario")));

                pedido.setStatus(rs.getString("status"));
                pedido.setValor_total(rs.getDouble("valor_total"));
                pedido.setForma_pagamento(rs.getString("forma_pagamento"));

                pedido.setData_criacao(
                        rs.getTimestamp("data_criacao").toLocalDateTime());

                pedido.setData_modificacao(
                        rs.getTimestamp("data_modificacao").toLocalDateTime());

                lista.add(pedido);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
}
