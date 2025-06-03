package com.reservashotel.reservashotel.controller;

import com.reservashotel.reservashotel.service.UsuarioService;
import com.reservashotel.reservashotel.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    // Muestra el formulario de login
    @GetMapping("/iniciar-sesion")
    public String mostrarLogin() {
        return "login"; // Busca login.html en templates
    }

    @PostMapping("/iniciar-sesion")
    public String procesarLogin(@RequestParam String username,
                                @RequestParam String password,
                                Model model) {
        Usuario usuario = usuarioService.buscarPorUsernameYPassword(username, password);
        if (usuario != null) {
            if ("administrador".equalsIgnoreCase(usuario.getRol())) {
                return "redirect:/bienvenida";
            } else if ("recepcionista".equalsIgnoreCase(usuario.getRol())) {
                return "redirect:/bienvenida-empleado";
            } else if ("cliente".equalsIgnoreCase(usuario.getRol())) {
                return "redirect:/bienvenida-cliente";
            } else {
                model.addAttribute("error", "Rol de usuario desconocido");
                return "login";
            }
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }
}
