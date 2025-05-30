// ClienteService.java
package com.reservashotel.reservashotel.service;

import com.reservashotel.reservashotel.entities.Cliente;
import java.util.List;

public interface ClienteService {
    List<Cliente> listarTodos();
    Cliente guardar(Cliente cliente);
    Cliente obtenerPorId(Long id);
    void eliminar(Long id);
}
