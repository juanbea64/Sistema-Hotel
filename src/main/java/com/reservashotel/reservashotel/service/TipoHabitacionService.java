package com.reservashotel.reservashotel.service;

import com.reservashotel.reservashotel.entities.TipoHabitacion;
import java.util.List;

public interface TipoHabitacionService {
    List<TipoHabitacion> listarTodos();
    TipoHabitacion guardar(TipoHabitacion tipo);
    TipoHabitacion obtenerPorId(Long id);
    void eliminar(Long id);
}
