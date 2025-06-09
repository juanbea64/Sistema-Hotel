package com.reservashotel.reservashotel.repository;

import com.reservashotel.reservashotel.entities.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    // ESTE MÉTODO es el que evita el error
    List<Habitacion> findByEstadoIgnoreCase(String estado);
}
