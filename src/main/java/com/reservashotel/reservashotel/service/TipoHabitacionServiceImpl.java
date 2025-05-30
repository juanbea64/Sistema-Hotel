package com.reservashotel.reservashotel.service;

import com.reservashotel.reservashotel.entities.TipoHabitacion;
import com.reservashotel.reservashotel.repository.TipoHabitacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoHabitacionServiceImpl implements TipoHabitacionService {

    private final TipoHabitacionRepository tipoHabitacionRepository;

    public TipoHabitacionServiceImpl(TipoHabitacionRepository tipoHabitacionRepository) {
        this.tipoHabitacionRepository = tipoHabitacionRepository;
    }

    @Override
    public List<TipoHabitacion> listarTodos() {
        return tipoHabitacionRepository.findAll();
    }

    @Override
    public TipoHabitacion guardar(TipoHabitacion tipo) {
        return tipoHabitacionRepository.save(tipo);
    }

    @Override
    public TipoHabitacion obtenerPorId(Long id) {
        return tipoHabitacionRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        tipoHabitacionRepository.deleteById(id);
    }
}
