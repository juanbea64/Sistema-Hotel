package com.reservashotel.reservashotel.repository;

import com.reservashotel.reservashotel.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

