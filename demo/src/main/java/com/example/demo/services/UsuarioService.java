package com.example.demo.services;

import com.example.demo.models.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @PersistenceContext
    private EntityManager entityManager;

    public Usuario loginUsuario(String username, String password) {
        // Consulta insegura vulnerable a SQL Injection
        String sql = "SELECT * FROM usuario WHERE username = '" + username + "' AND password = '" + password + "'";
        Query query = entityManager.createNativeQuery(sql, Usuario.class);
        try {
            return (Usuario) query.getSingleResult();
        } catch (Exception e) {
            return null; // Si no se encuentra el usuario
        }
    }
}
