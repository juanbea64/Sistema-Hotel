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
import java.util.List;
import java.util.stream.Collectors;

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

    // Mostrar listado y formulario
    @GetMapping
    public String listarReservas(Model model) {
        List<Reserva> reservas = reservaRepository.findAll();
        List<Habitacion> todasHabitaciones = habitacionRepository.findByEstadoIgnoreCase("disponible");

        // Obtener las habitaciones ya reservadas (aunque estén disponibles por error)
        List<Long> habitacionesReservadas = reservas.stream()
                .map(reserva -> reserva.getHabitacion().getIdHabitacion())
                .toList();

        // Filtrar las que no están en reservas
        List<Habitacion> habitacionesDisponibles = todasHabitaciones.stream()
                .filter(habitacion -> !habitacionesReservadas.contains(habitacion.getIdHabitacion()))
                .toList();

        model.addAttribute("reservas", reservas);
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("habitaciones", habitacionesDisponibles);
        model.addAttribute("reserva", new Reserva());
        return "reservas";
    }

    // Editar una reserva existente
    @GetMapping("/editar/{id}")
    public String editarReserva(@PathVariable("id") Long id, Model model) {
        Reserva reserva = reservaRepository.findById(id).orElse(null);
        if (reserva == null) {
            model.addAttribute("error", "Reserva no encontrada");
            return "error";
        }
        model.addAttribute("reserva", reserva);
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("habitaciones", habitacionRepository.findByEstadoIgnoreCase("disponible"));
        return "reservas";
    }

    // Guardar una reserva
    @PostMapping("/guardar")
    public String guardarReserva(@ModelAttribute Reserva reserva) {
        Habitacion habitacion = reserva.getHabitacion();
        if (habitacion != null && "disponible".equalsIgnoreCase(habitacion.getEstado())) {
            habitacion.setEstado("ocupada");
            habitacionService.guardar(habitacion);
        }

        reservaRepository.save(reserva);
        return "redirect:/reservas";
    }

    // Reservar desde botón
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

    // Eliminar una reserva y liberar la habitación
    @PostMapping("/eliminar/{id}")
    public String eliminarReserva(@PathVariable("id") Long id, Model model) {
        Reserva reserva = reservaRepository.findById(id).orElse(null);
        if (reserva == null) {
            model.addAttribute("error", "Reserva no encontrada");
            return "error";
        }

        Habitacion habitacion = reserva.getHabitacion();
        if (habitacion != null) {
            habitacion.setEstado("disponible");
            habitacionService.guardar(habitacion);
        }

        reservaRepository.deleteById(id);
        return "redirect:/reservas?eliminada";
    }

    // Liberar habitación directamente
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

    @GetMapping("/nueva/{id}")
    public String nuevaReservaDesdeHabitacion(@PathVariable("id") Long id, Model model) {
        Habitacion habitacion = habitacionRepository.findById(id).orElse(null);

        if (habitacion == null) {
            model.addAttribute("error", "Habitación no encontrada");
            return "error";
        }

        if (!"disponible".equalsIgnoreCase(habitacion.getEstado())) {
            model.addAttribute("error", "La habitación no está disponible para reservar.");
            return "error";
        }

        Reserva reserva = new Reserva();
        reserva.setHabitacion(habitacion);

        model.addAttribute("reserva", reserva);
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("habitacionSeleccionada", habitacion);
        model.addAttribute("habitaciones", habitacionRepository.findByEstadoIgnoreCase("disponible"));

        return "reservas";
    }
}
