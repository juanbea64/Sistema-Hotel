package com.reservashotel.reservashotel.controller;

import com.reservashotel.reservashotel.entities.Empleado;
import com.reservashotel.reservashotel.entities.Usuario;
import com.reservashotel.reservashotel.repository.EmpleadoRepository;
import com.reservashotel.reservashotel.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String listarEmpleados(Model model) {
        model.addAttribute("empleados", empleadoRepository.findAll());
        model.addAttribute("empleado", new Empleado()); // Para el formulario
        return "empleados";
    }

    @PostMapping("/guardar")
    public String guardarEmpleado(@ModelAttribute Empleado empleado) {
        Usuario usuario = empleado.getUsuario();
        if (usuario.getIdUsuario() != null) {
            // Si el usuario ya existe, actualízalo
            usuarioRepository.save(usuario);
        } else {
            // Si es nuevo, asigna el rol "recepcionista" y guárdalo
            usuario.setRol("recepcionista");
            usuarioRepository.save(usuario);
        }
        empleado.setUsuario(usuario);
        empleadoRepository.save(empleado);
        return "redirect:/empleados";
    }

    @GetMapping("/editar/{id}")
    public String editarEmpleado(@PathVariable("id") Long id, Model model) {
        Empleado empleado = empleadoRepository.findById(id).orElse(new Empleado());
        model.addAttribute("empleado", empleado);
        model.addAttribute("empleados", empleadoRepository.findAll());
        return "empleados";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable("id") Long id) {
        empleadoRepository.deleteById(id);
        return "redirect:/empleados";
    }
}
