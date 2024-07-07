package com.example.ultimoChallenge.entities.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegistroUsuarioDTO(
        @NotBlank
        String nombre,
        @Email
        String correo,
        @NotBlank
        String contrasena
) {
}
