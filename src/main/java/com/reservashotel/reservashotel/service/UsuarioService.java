package com.reservashotel.reservashotel.service;

import com.reservashotel.reservashotel.entities.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> listarTodos();
    Usuario guardar(Usuario usuario);
    Usuario obtenerPorId(Long id);
    void eliminar(Long id);
    Usuario buscarPorUsernameYPassword(String username, String password);
}
