package com.reservashotel.reservashotel.service;

import com.reservashotel.reservashotel.entities.Pago;
import java.util.List;

public interface PagoService {
    List<Pago> listarTodos();
    Pago guardar(Pago pago);
    Pago obtenerPorId(Long id);
    void eliminar(Long id);
}
