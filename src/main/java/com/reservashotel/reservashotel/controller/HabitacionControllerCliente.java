package com.reservashotel.reservashotel.controller;

import com.reservashotel.reservashotel.entities.Habitacion;
import com.reservashotel.reservashotel.repository.HabitacionRepository;
import com.reservashotel.reservashotel.repository.TipoHabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/habitaciones-clientes")
public class HabitacionControllerCliente {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    @GetMapping
    public String listarHabitacionesCliente(Model model) {
        model.addAttribute("habitaciones", habitacionRepository.findAll());
        model.addAttribute("tiposHabitacion", tipoHabitacionRepository.findAll());
        return "habitaciones-clientes"; // Asegúrate de que este nombre coincida con el archivo HTML
    }
}
