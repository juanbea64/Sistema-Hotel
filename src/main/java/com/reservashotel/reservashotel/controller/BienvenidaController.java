package com.reservashotel.reservashotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BienvenidaController {

    @GetMapping("/bienvenida")
    public String mostrarBienvenida() {
        return "bienvenida";
    }

    @GetMapping("/logout-success")
    public String logoutExitoso() {
        return "redirect:/login"; // O muestra una vista personalizada
    }
}
