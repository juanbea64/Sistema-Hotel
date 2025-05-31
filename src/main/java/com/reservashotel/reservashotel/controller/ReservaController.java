package com.reservashotel.reservashotel.controller;

import com.reservashotel.reservashotel.entities.Reserva;
import com.reservashotel.reservashotel.entities.Habitacion;
import com.reservashotel.reservashotel.repository.ReservaRepository;
import com.reservashotel.reservashotel.repository.ClienteRepository;
import com.reservashotel.reservashotel.repository.HabitacionRepository;
import com.reservashotel.reservashotel.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private HabitacionService habitacionService;

    @GetMapping
    public String listarReservas(Model model) {
        model.addAttribute("reservas", reservaRepository.findAll());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("habitaciones", habitacionRepository.findAll());
        model.addAttribute("reserva", new Reserva());
        return "reservas";
    }

    @PostMapping("/guardar")
    public String guardarReserva(@ModelAttribute Reserva reserva) {
        reservaRepository.save(reserva);
        return "redirect:/reservas";
    }

    @PostMapping("/reservar")
    public String reservarHabitacion(@RequestParam Long habitacionId, @ModelAttribute Reserva reserva, Model model) {
        Habitacion habitacion = habitacionService.obtenerPorId(habitacionId);
        if (habitacion == null) {
            model.addAttribute("error", "Habitación no encontrada");
            return "error";
        }
        if ("disponible".equalsIgnoreCase(habitacion.getEstado())) {
            habitacion.setEstado("ocupada");
            habitacionService.guardar(habitacion);
            reserva.setHabitacion(habitacion);
            reservaRepository.save(reserva);
            return "redirect:/reservas?exito";
        } else {
            model.addAttribute("error", "La habitación no está disponible para reservar.");
            return "error";
        }
    }

    @PostMapping("/liberar")
    public String liberarHabitacion(@RequestParam Long habitacionId, Model model) {
        Habitacion habitacion = habitacionService.obtenerPorId(habitacionId);
        if (habitacion == null) {
            model.addAttribute("error", "Habitación no encontrada");
            return "error";
        }
        habitacion.setEstado("disponible");
        habitacionService.guardar(habitacion);
        return "redirect:/habitaciones?liberada";
    }
}