package com.reservashotel.reservashotel.service;

import com.reservashotel.reservashotel.entities.Pago;
import com.reservashotel.reservashotel.repository.PagoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;

    public PagoServiceImpl(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @Override
    public List<Pago> listarTodos() {
        return pagoRepository.findAll();
    }

    @Override
    public Pago guardar(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public Pago obtenerPorId(Long id) {
        return pagoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        pagoRepository.deleteById(id);
    }
}
