package com.reservashotel.reservashotel.controller;

import com.reservashotel.reservashotel.entities.Usuario;
import com.reservashotel.reservashotel.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Página de registro
    @GetMapping("/usuarios/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registroUsuario";
    }

    @PostMapping("/usuarios/registrar")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.guardar(usuario);
        return "redirect:/iniciar-sesion"; // Redirige a la ruta del login
    }
}
