package com.howtodoinjava.service;

import com.howtodoinjava.entity.Usuario;
import java.util.List;

public interface UsuarioManager {
    public void addUsuario(Usuario usuario);
    public List<Usuario> getAllUsuarios();
    public Usuario getUsuario(String login);
    public void deleteUsuario(Integer usuarioId);
}
