package com.example.ultimoChallenge.entities.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActualizarRespuestaDTO(
        @NotNull
        Long id,
        @NotBlank
        String mensaje
) {
}
