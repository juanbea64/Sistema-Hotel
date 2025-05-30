package com.reservashotel.reservashotel.service;

import com.reservashotel.reservashotel.entities.Habitacion;
import com.reservashotel.reservashotel.repository.HabitacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitacionServiceImpl implements HabitacionService {

    private final HabitacionRepository habitacionRepository;

    public HabitacionServiceImpl(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }

    @Override
    public List<Habitacion> listarTodos() {
        return habitacionRepository.findAll();
    }

    @Override
    public Habitacion guardar(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    @Override
    public Habitacion obtenerPorId(Long id) {
        return habitacionRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        habitacionRepository.deleteById(id);
    }
}
