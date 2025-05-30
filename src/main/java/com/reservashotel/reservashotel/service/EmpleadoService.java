package com.reservashotel.reservashotel.service;

import com.reservashotel.reservashotel.entities.Empleado;
import java.util.List;

public interface EmpleadoService {
    List<Empleado> listarTodos();
    Empleado guardar(Empleado empleado);
    Empleado obtenerPorId(Long id);
    void eliminar(Long id);
}
