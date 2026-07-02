/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Util.Util;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.MovimentacaoEstoque;
import model.Produto;


public class MovimentacaoEstoqueDAO {
    public MovimentacaoEstoqueDAO(ProdutoDAO produtoDAO) {
        
    }

    public boolean Adicionar(MovimentacaoEstoque elemento) {
        String sql = "INSERT INTO Movimentacao_Estoque "
                + "(fk_produto, quantidade, tipo, valor_unitario, data_criacao, data_modificacao) "
                + "VALUES (?,?,?,?,?,?)";

        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, elemento.getProduto().getId());
            stmt.setInt(2, elemento.getQuantidade());
            stmt.setString(3, elemento.getTipo());
            stmt.setDouble(4, elemento.getValor_unitario());
            stmt.setTimestamp(5, Timestamp.valueOf(elemento.getData_criacao()));
            stmt.setTimestamp(6, Timestamp.valueOf(elemento.getData_modificacao()));

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao registrar movimentacao: " + e.getMessage());
            return false;
        }
    }

    public List<MovimentacaoEstoque> getLista() {
        String sql = "SELECT * FROM Movimentacao_Estoque";
        List<MovimentacaoEstoque> movs = new ArrayList<>();

        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            ProdutoDAO pdao = new ProdutoDAO();

            while (rs.next()) {
                MovimentacaoEstoque mov = new MovimentacaoEstoque();
                mov.setProduto(pdao.buscarPorId(rs.getLong("fk_produto")));
                mov.setQuantidade(rs.getInt("quantidade"));
                mov.setTipo(rs.getString("tipo"));
                mov.setValor_unitario(rs.getDouble("valor_unitario"));
                
                // Usando reflection para inserir o id do BD
                try {
                    java.lang.reflect.Field idField = MovimentacaoEstoque.class.getDeclaredField("id");
                    idField.setAccessible(true);
                    idField.set(mov, rs.getLong("id"));
                } catch (Exception ex) {}

                movs.add(mov);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movs;
    }

    public boolean remover(long id) {
        String sql = "DELETE FROM Movimentacao_Estoque WHERE id = ?";
        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarTodos() {
        List<MovimentacaoEstoque> movs = getLista();
        if (movs.isEmpty()) {
            System.out.println("Nao existe Movimentação de Estoque");
        } else {
            for (MovimentacaoEstoque me : movs) {
                System.out.println(me);
            }
        }
    }
    
    public boolean alterar(MovimentacaoEstoque mov) {
        
        String sql = "Uptade Pedido SET " 
                + "fk_produto = ?, "
                + "quantidade = ?, "
                + "tipo = ?, "
                + "valor_unitario = ?, "
                + "data_modificacao = ? "
                + "where id = ?";
        try(Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setLong(1,
                mov.getProduto().getId());

            stmt.setInt(2,
                mov.getQuantidade());
            
            stmt.setString(3,
                "AJUSTE");
            
            stmt.setDouble(4,
                mov.getValor_unitario());
            
            stmt.setTimestamp(5, Timestamp.valueOf(mov.getData_modificacao()));

            stmt.setLong(6,
                mov.getId());
            
            stmt.executeUpdate();
            return true;
            
        }catch(SQLException e){
            return false;
        }

    }

    public int consultarSaldo(Produto p) {
        // O próprio banco de dados fará a matemática para descobrir o saldo
        String sql = "SELECT "
                + "COALESCE(SUM(CASE WHEN tipo = 'ENTRADA' THEN quantidade ELSE 0 END), 0) - "
                + "COALESCE(SUM(CASE WHEN tipo = 'SAIDA' THEN quantidade ELSE 0 END), 0) - "
                + "COALESCE(SUM(CASE WHEN tipo = 'AJUSTE' THEN quantidade ELSE 0 END), 0) AS saldo "
                + "FROM Movimentacao_Estoque WHERE fk_produto = ?";

        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, p.getId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("saldo");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public boolean registrarSaida(Produto p, int qtdVendida) {
        int saldoAtual = this.consultarSaldo(p);

        if (qtdVendida <= saldoAtual) {
            MovimentacaoEstoque novaSaida = new MovimentacaoEstoque();
            novaSaida.setProduto(p);
            novaSaida.setQuantidade(qtdVendida);
            novaSaida.setTipo("SAIDA");
            novaSaida.setValor_unitario(p.getPreco_venda());

            return this.Adicionar(novaSaida);
        } else {
            return false; // Não há estoque suficiente
        }
    }   
    
    public MovimentacaoEstoque Excluir(MovimentacaoEstoque mov){
        String sql = "delete from Movimentacao_Estoque where id = ?";
        
        try(Connection con = new ConnectionFactory().getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setLong(1, mov.getId());
            
            stmt.execute();
            
            System.out.println("Estoque excluída");
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return mov;
    }
}
