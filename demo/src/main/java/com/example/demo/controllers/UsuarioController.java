package com.example.demo.controllers;

import com.example.demo.DTOs.UsuarioDTO;
import com.example.demo.models.Usuario;
import com.example.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "index"; // nombre del archivo HTML del formulario
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsuarioDTO usuarioDTO, Model model) {
        Usuario usuario = usuarioService.loginUsuario(usuarioDTO.getUsername(), usuarioDTO.getPassword());

        if (usuario != null && usuario.getPassword().equals(usuarioDTO.getPassword())) {
            // Simulación de autenticación exitosa
            model.addAttribute("username", usuario.getUsername());
            return "welcome"; // una página de bienvenida si el login tiene éxito
        } else {
            // Si falla la autenticación
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "index";
        }
    }
}
