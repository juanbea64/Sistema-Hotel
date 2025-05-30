package com.reservashotel.reservashotel.service;

import com.reservashotel.reservashotel.entities.Habitacion;
import java.util.List;

public interface HabitacionService {
    List<Habitacion> listarTodos();
    Habitacion guardar(Habitacion habitacion);
    Habitacion obtenerPorId(Long id);
    void eliminar(Long id);
}
