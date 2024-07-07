package com.example.ultimoChallenge.entities.usuario;

import jakarta.validation.constraints.NotBlank;

public record AutenticarUsuarioDTO(
        @NotBlank
        String correo,
        @NotBlank
        String contrasena
) {
}
