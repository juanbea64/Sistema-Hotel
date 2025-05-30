package com.reservashotel.reservashotel.controller;

import com.reservashotel.reservashotel.entities.Habitacion;
import com.reservashotel.reservashotel.entities.TipoHabitacion;
import com.reservashotel.reservashotel.repository.HabitacionRepository;
import com.reservashotel.reservashotel.repository.TipoHabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    @GetMapping
    public String listarHabitaciones(Model model) {
        model.addAttribute("habitaciones", habitacionRepository.findAll());
        model.addAttribute("habitacion", new Habitacion());
        model.addAttribute("tiposHabitacion", tipoHabitacionRepository.findAll());
        return "habitaciones";
    }

    @PostMapping("/guardar")
    public String guardarHabitacion(@ModelAttribute Habitacion habitacion) {
        habitacionRepository.save(habitacion);
        return "redirect:/habitaciones";
    }

    @GetMapping("/editar/{id}")
    public String editarHabitacion(@PathVariable("id") Long id, Model model) {
        Habitacion habitacion = habitacionRepository.findById(id).orElse(new Habitacion());
        model.addAttribute("habitacion", habitacion);
        model.addAttribute("habitaciones", habitacionRepository.findAll());
        model.addAttribute("tiposHabitacion", tipoHabitacionRepository.findAll());
        return "habitaciones";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarHabitacion(@PathVariable("id") Long id) {
        habitacionRepository.deleteById(id);
        return "redirect:/habitaciones";
    }
}
