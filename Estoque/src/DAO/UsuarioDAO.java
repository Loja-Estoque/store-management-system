/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.Usuario;

/**
 *
 * @author W10
 */
public class UsuarioDAO {
    private Usuario[] usuarios = new Usuario[5];
    
    public UsuarioDAO(){
        
    }
    Usuario buscaUsuarioLogin(String login, String senha) {
         for (Usuario u : usuarios) {
            if (u != null && u.getLogin().equals(login) &&
                    u.getSenha().equals(senha)) {
                return u;
            }
        }
        return null;
    }
    
    boolean Adicionar(Usuario u)
    {
        int ProximaPosicaoLivre = this.proximaPosicaoLivre();
        if(ProximaPosicaoLivre != -1)
        {
            usuarios[ProximaPosicaoLivre] = u;
            return true;
        } else {
            return false;
        }
    }
    
    private int proximaPosicaoLivre() {
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] == null) {
                return i;
            }

        }
        return -1;

    }
    
    public void mostrarTodos() {
        boolean temUsuario = false;
        for (Usuario u : usuarios) {
            if (u != null) {
                System.out.println(u);
                temUsuario = true;
            }
        }
        if (!temUsuario) {
            System.out.println("nao existe jogador cadastrado");
        }
    }
    
    public boolean remover(String login) {
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] != null && usuarios[i].getLogin().equals(login)) {
                usuarios[i] = null;
                return true;
            }
        }
        return false;

    }
}
