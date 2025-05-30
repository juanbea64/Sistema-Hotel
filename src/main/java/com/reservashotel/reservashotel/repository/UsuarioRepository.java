package com.reservashotel.reservashotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.reservashotel.reservashotel.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsernameAndPassword(String username, String password);
}
