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
import model.Usuario;
import model.Pessoa;

/**
 *
 * @author W10
 */
public class UsuarioDAO {
    
    
     List<Usuario> usuarios = getLista();
    
    
    PessoaDAO pessoaDAO = new PessoaDAO();
    
    public UsuarioDAO(){
        /*Pessoa pAdm = pessoaDAO.buscarDocumento("12345678");
        Pessoa pCm = pessoaDAO.buscarDocumento("87654321");
        
        if(pAdm != null)
        {
            Usuario uAdimin = new Usuario();
            uAdimin.setPessoa(pAdm);
            uAdimin.setLogin("Administrador");
            uAdimin.setSenha("1234");
            this.Adicionar(uAdimin);
            
            Usuario uComum = new Usuario();
            uComum.setPessoa(pCm);
            uComum.setLogin("Comum");
            uComum.setSenha("1234");
            this.Adicionar(uComum);
        }*/
    }
    
    public Usuario adicionar(Usuario elemento) {
        String sql =
        "INSERT INTO Usuario "
        + "(fk_pessoa, login, senha, data_criacao, data_modificacao)"
        + " VALUES (?,?,?,?,?)";

        try(Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){

            stmt.setLong(1, elemento.getPessoa().getId());
            stmt.setString(2, elemento.getLogin());
            stmt.setString(3, elemento.getSenha());

            stmt.setTimestamp(4,
                Timestamp.valueOf(elemento.getData_criacao()));

            stmt.setTimestamp(5,
                Timestamp.valueOf(elemento.getData_modificacao()));

            stmt.executeUpdate();

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        //na verdade deveria retornar o elemento que foi inserido agora
        return elemento;
    }
    
    public List<Usuario> getLista() {

        String sql = "select * from Usuario";


        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while(rs.next()){

                Usuario usuario = new Usuario();

                usuario.setId(rs.getLong("id"));

                PessoaDAO dao = new PessoaDAO();

                Pessoa pessoa =
                        dao.buscarPorId(rs.getLong("fk_pessoa"));

                usuario.setPessoa(pessoa);

                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));

                usuarios.add(usuario);

            }

        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return usuarios;
    }
    
    public Usuario buscarPorId(long id){
        String sql = "select * from Usuario where id = ?";
        
        try (Connection con = new ConnectionFactory().getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1,id);
                
            ResultSet rs = stmt.executeQuery();
                
            if(rs.next()){
                Usuario usuario = new Usuario();
                    
                usuario.setId(rs.getLong("id"));
                
                long idPessoa = rs.getLong("fk_pessoa");
                
                Pessoa pessoa = pessoaDAO.buscarPorId(idPessoa);
                
                usuario.setPessoa(pessoa);
                    
                usuario.setLogin(rs.getString("login"));
                    
                usuario.setSenha(rs.getString("senha"));

                usuario.setData_criacao(rs.getTimestamp("data_criacao").toLocalDateTime());

                usuario.setData_modificacao(rs.getTimestamp("data_modificacao").toLocalDateTime());

                return usuario;
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return null;
    }
    
     public Usuario buscarPorLogin(String login) {

        String sql = "SELECT * FROM Usuario WHERE login = ?";

        try (Connection con = new ConnectionFactory().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, login);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setId(rs.getLong("id"));
                Pessoa pessoa = pessoaDAO.buscarPorId(rs.getLong("fk_pessoa"));
                usuario.setPessoa(pessoa);

                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));

                Timestamp criacao = rs.getTimestamp("data_criacao");
                if (criacao != null) {
                    usuario.setData_criacao(criacao.toLocalDateTime());
                }

                Timestamp modificacao = rs.getTimestamp("data_modificacao");
                if (modificacao != null) {
                    usuario.setData_modificacao(modificacao.toLocalDateTime());
                }

                return usuario;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    
    
    public boolean alterar(Usuario usuario) {
        
        String sql = "Uptade Usuario" 
                + "set fk_pessoa = ?"
                + "login = ?"
                + "senha = ?"
                + "data_moficacao = ?"
                + "where id = ?";
        try(Connection con = new ConnectionFactory().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setLong(1,
                usuario.getPessoa().getId());

            stmt.setString(2,
                usuario.getLogin());

            stmt.setString(3,
                usuario.getSenha());

            stmt.setTimestamp(4,
                Timestamp.valueOf(usuario.getData_modificacao()));

            stmt.setLong(5,
                usuario.getId());
            return true;
            
        }catch(SQLException e){
            return false;
        }

    }
    
    public Usuario Excluir(Usuario usuario){
        String sql = "delete from Usuario where id = ?";
        
        try(Connection con = new ConnectionFactory().getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setLong(1, usuario.getId());
            
            stmt.execute();
            
            System.out.println("Usuario excluído");
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return usuario;
    }
    
    public void Mostrar(){
        usuarios = getLista();
        for(Usuario usuario : usuarios){
            System.out.println(usuario.toString());
        }
    }
}
