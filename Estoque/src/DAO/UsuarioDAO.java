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
}
