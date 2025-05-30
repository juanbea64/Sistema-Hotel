package com.reservashotel.reservashotel.service;

import com.reservashotel.reservashotel.entities.Reserva;
import com.reservashotel.reservashotel.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public List<Reserva> listarTodos() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva guardar(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva obtenerPorId(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        reservaRepository.deleteById(id);
    }
}
