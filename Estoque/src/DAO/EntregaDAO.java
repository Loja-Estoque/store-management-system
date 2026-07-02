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
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import model.Entrega;

public class EntregaDAO {

    public boolean Adicionar(Entrega elemento) {
        String sql = "INSERT INTO Entrega "
                + "(fk_pedido, status, transportadora, codigo_rastreio, data_envio, data_entrega, data_criacao, data_modificacao) "
                + "VALUES (?,?,?,?,?,?,?,?)";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, elemento.getPedido().getId());
            stmt.setString(2, elemento.getStatus());
            stmt.setString(3, elemento.getTransportadora());
            stmt.setString(4, elemento.getCodigo_rastreio());

            if (elemento.getData_envio() != null) {
                stmt.setDate(5, Date.valueOf(elemento.getData_envio()));
            } else {
                stmt.setNull(5, java.sql.Types.DATE);
            }

            if (elemento.getData_entrega() != null) {
                stmt.setDate(6, Date.valueOf(elemento.getData_entrega()));
            } else {
                stmt.setNull(6, java.sql.Types.DATE);
            }

            stmt.setTimestamp(7, Timestamp.valueOf(elemento.getData_criacao()));
            stmt.setTimestamp(8, Timestamp.valueOf(elemento.getData_modificacao()));

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar Entrega no banco: " + e.getMessage());
            return false;
        }
    }

    public List<Entrega> getLista() {
        String sql = "SELECT * FROM Entrega";
        List<Entrega> entregas = new ArrayList<>();

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            PedidoDAO pdao = new PedidoDAO();

            while (rs.next()) {
                Entrega entrega = new Entrega();
                entrega.setId_pedido(pdao.buscarPorId(rs.getLong("fk_pedido")));
                entrega.setStatus(rs.getString("status"));
                entrega.setTransportadora(rs.getString("transportadora"));
                entrega.setCodigo_rastreio(rs.getString("codigo_rastreio"));

                if (rs.getDate("data_envio") != null) {
                    entrega.setData_envio(rs.getDate("data_envio").toLocalDate());
                }
                if (rs.getDate("data_entrega") != null) {
                    entrega.setData_entrega(rs.getDate("data_entrega").toLocalDate());
                }

                entrega.setData_criacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                entrega.setData_modificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());

                // Sobrescrevendo o ID para garantir que usamos o do banco
                try {
                    java.lang.reflect.Field idField = Entrega.class.getDeclaredField("id");
                    idField.setAccessible(true);
                    idField.set(entrega, rs.getLong("id"));
                } catch (Exception ex) {
                }

                entregas.add(entrega);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entregas;
    }

    public Entrega buscarPorId(long id) {
        String sql = "SELECT * FROM Entrega WHERE id = ?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Entrega entrega = new Entrega();
                PedidoDAO pdao = new PedidoDAO();
                entrega.setId_pedido(pdao.buscarPorId(rs.getLong("fk_pedido")));
                entrega.setStatus(rs.getString("status"));
                entrega.setTransportadora(rs.getString("transportadora"));
                entrega.setCodigo_rastreio(rs.getString("codigo_rastreio"));

                if (rs.getDate("data_envio") != null) {
                    entrega.setData_envio(rs.getDate("data_envio").toLocalDate());
                }
                if (rs.getDate("data_entrega") != null) {
                    entrega.setData_entrega(rs.getDate("data_entrega").toLocalDate());
                }

                entrega.setData_criacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                entrega.setData_modificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());

                // Setando o ID via Reflection para contornar o auto-increment da memória
                try {
                    java.lang.reflect.Field idField = Entrega.class.getDeclaredField("id");
                    idField.setAccessible(true);
                    idField.set(entrega, rs.getLong("id"));
                } catch (Exception ex) {
                }

                return entrega;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean alterar(Entrega entrega) {
        String sql = "UPDATE Entrega SET fk_pedido=?, status=?, transportadora=?, codigo_rastreio=?, data_envio=?, data_entrega=?, data_modificacao=? WHERE id=?";

        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, entrega.getPedido().getId());
            stmt.setString(2, entrega.getStatus());
            stmt.setString(3, entrega.getTransportadora());
            stmt.setString(4, entrega.getCodigo_rastreio());

            if (entrega.getData_envio() != null) {
                stmt.setDate(5, Date.valueOf(entrega.getData_envio()));
            } else {
                stmt.setNull(5, java.sql.Types.DATE);
            }

            if (entrega.getData_entrega() != null) {
                stmt.setDate(6, Date.valueOf(entrega.getData_entrega()));
            } else {
                stmt.setNull(6, java.sql.Types.DATE);
            }

            stmt.setTimestamp(7, Timestamp.valueOf(Util.getAgora()));
            stmt.setLong(8, entrega.getId());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean remover(long id) {
        String sql = "DELETE FROM Entrega WHERE id = ?";
        try (Connection con = new ConnectionFactory().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarTodos() {
        List<Entrega> entregas = getLista();
        if (entregas.isEmpty()) {
            System.out.println("Nao existe Entrega cadastrada");
        } else {
            for (Entrega e : entregas) {
                System.out.println(e);
            }
        }
    }

    public void AtualizarStatus() {

        LocalDateTime hoje = Util.getAgora();

        List<Entrega> entregas = getLista();

        for (Entrega e : entregas) {

            long horasPassadas =
                    java.time.Duration.between(
                            e.getData_modificacao(),
                            hoje).toHours();

            if (horasPassadas >= 24) {

                switch (e.getStatus()) {

                    case "PREPARANDO":
                        e.setStatus("ENVIADO");
                        e.setData_modificacao(hoje);
                        alterar(e);
                        break;

                    case "ENVIADO":
                        e.setStatus("ENTREGUE");
                        e.setData_modificacao(hoje);
                        alterar(e);
                        break;
                }
            }
        }
    }
}
