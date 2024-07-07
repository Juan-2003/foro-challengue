package com.example.ultimoChallenge.entities.curso;

import jakarta.validation.constraints.NotNull;

public record ActualizarCursoDTO(
        @NotNull
        Long id,
        String nombre,
        String categoria
) {
}
