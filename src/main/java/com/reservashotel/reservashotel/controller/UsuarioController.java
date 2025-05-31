package com.reservashotel.reservashotel.controller;

import com.reservashotel.reservashotel.entities.Cliente;
import com.reservashotel.reservashotel.entities.Usuario;
import com.reservashotel.reservashotel.repository.ClienteRepository;
import com.reservashotel.reservashotel.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // Página de registro
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registroUsuario";
    }

    @PostMapping("/registrar")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        usuarioRepository.save(usuario);

        // Si el rol es cliente, crea también el registro en la tabla cliente
        if ("cliente".equalsIgnoreCase(usuario.getRol())) {
            Cliente cliente = new Cliente();
            cliente.setNombre(usuario.getUsername());
            cliente.setEmail(usuario.getEmail());
            cliente.setUsuario(usuario);
            clienteRepository.save(cliente);
        }

        return "redirect:/iniciar-sesion"; // Redirige a la ruta del login
    }
}
