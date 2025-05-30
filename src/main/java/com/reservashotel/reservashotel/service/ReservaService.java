package com.reservashotel.reservashotel.service;

import com.reservashotel.reservashotel.entities.Reserva;
import java.util.List;

public interface ReservaService {
    List<Reserva> listarTodos();
    Reserva guardar(Reserva reserva);
    Reserva obtenerPorId(Long id);
    void eliminar(Long id);
}
