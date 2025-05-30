package com.reservashotel.reservashotel.controller;

import com.reservashotel.reservashotel.entities.TipoHabitacion;
import com.reservashotel.reservashotel.repository.TipoHabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tipohabitacion")
public class TipoHabitacionController {

    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    @GetMapping
    public String listarTipos(Model model) {
        model.addAttribute("tiposHabitacion", tipoHabitacionRepository.findAll());
        model.addAttribute("tipoHabitacion", new TipoHabitacion());
        return "tipoHabitacion";
    }

    @PostMapping("/guardar")
    public String guardarTipo(@ModelAttribute TipoHabitacion tipoHabitacion) {
        tipoHabitacionRepository.save(tipoHabitacion);
        return "redirect:/tipohabitacion";
    }

    @GetMapping("/editar/{id}")
    public String editarTipo(@PathVariable("id") Long id, Model model) {
        TipoHabitacion tipo = tipoHabitacionRepository.findById(id).orElse(new TipoHabitacion());
        model.addAttribute("tipoHabitacion", tipo);
        model.addAttribute("tiposHabitacion", tipoHabitacionRepository.findAll());
        return "tipoHabitacion";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarTipo(@PathVariable("id") Long id) {
        tipoHabitacionRepository.deleteById(id);
        return "redirect:/tipohabitacion";
    }
}
