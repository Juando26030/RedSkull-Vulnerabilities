package com.example.demo.controllers;

import com.example.demo.DTOs.UsuarioDTO;
import com.example.demo.models.Usuario;
import com.example.demo.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {
    // Añade este logger al principio de tu controlador
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "index"; // Muestra la página de login
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsuarioDTO usuarioDTO, Model model) {
        // Validar usuario y contraseña con el servicio
        Usuario usuario = usuarioService.loginUsuario(usuarioDTO.getUsername(), usuarioDTO.getPassword());

        if (usuario != null) {
            // Autenticación exitosa
            model.addAttribute("username", usuario.getUsername());

            // Verificar si el usuario es "redskull"
            if ("agapito".equals(usuario.getUsername())) {
                model.addAttribute("recordatorio", "Recordatorio! La contraseña de redskull es: redskull666");
                logger.info("Recordatorio añadido para el usuario redskull");
            }

            return "bienvenida"; // Mostrar la página de bienvenida directamente
        } else {
            // Autenticación fallida
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "index"; // Redirigir de nuevo al formulario con error
        }
    }

    @GetMapping("/welcome")
    public String mostrarPaginaBienvenida(@RequestParam("username") String username, Model model) {

        logger.info("Atributos del modelo: " + model.asMap()); // Añadir traza para ver los atributos
        model.addAttribute("username", username);

        if ("redskull".equals(username)) {
            model.addAttribute("recordatorio", "Recordatorio! La contraseña de redskull es: redskull666");
        }

        return "bienvenida";  // Muestra la página de bienvenida
    }

}
