// EmpleadoRepository.java
package com.reservashotel.reservashotel.repository;

import com.reservashotel.reservashotel.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}
