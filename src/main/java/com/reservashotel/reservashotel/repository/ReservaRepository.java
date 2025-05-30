package com.reservashotel.reservashotel.repository;

import com.reservashotel.reservashotel.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}