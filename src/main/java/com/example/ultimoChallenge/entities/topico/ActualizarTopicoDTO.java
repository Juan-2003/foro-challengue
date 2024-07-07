package com.example.ultimoChallenge.entities.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActualizarTopicoDTO(
        @NotNull
        Long id,
        @NotBlank
        String mensaje,
        @NotBlank
        String titulo
) {
}
