package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", unique = true)  // Cambiamos aquí para mapear correctamente la columna 'nombre'
    private String username;

    @Column(name = "contrasena")  // Asegúrate de que coincida con el nombre de la columna de la tabla
    private String password;
}

