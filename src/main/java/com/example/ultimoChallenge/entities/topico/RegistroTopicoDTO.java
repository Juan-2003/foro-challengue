package com.example.ultimoChallenge.entities.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record RegistroTopicoDTO(
        @NotNull
        Long idUsuario,
        @NotNull
        Long idCurso,
        @NotBlank
        String mensaje,
        @NotBlank
        String titulo
) {
}
