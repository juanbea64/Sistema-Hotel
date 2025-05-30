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
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("cliente", new Cliente()); // Esto es necesario
        return "clientes";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        Usuario usuario = cliente.getUsuario();
        usuario.setRol("cliente");
        usuario.setEmail(cliente.getEmail());

        // Si el usuario ya existe (tiene id), actualiza; si no, crea uno nuevo
        if (usuario.getIdUsuario() != null) {
            Usuario usuarioExistente = usuarioRepository.findById(usuario.getIdUsuario()).orElse(usuario);
            usuarioExistente.setUsername(usuario.getUsername());
            usuarioExistente.setPassword(usuario.getPassword());
            usuarioExistente.setEmail(usuario.getEmail());
            usuarioExistente.setRol("cliente");
            usuario = usuarioRepository.save(usuarioExistente);
        } else {
            usuario = usuarioRepository.save(usuario);
        }

        cliente.setUsuario(usuario);
        clienteRepository.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable("id") Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id).orElse(new Cliente());
        model.addAttribute("cliente", cliente);
        model.addAttribute("clientes", clienteRepository.findAll());
        return "clientes";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable("id") Long id) {
        clienteRepository.deleteById(id);
        return "redirect:/clientes";
    }
}
