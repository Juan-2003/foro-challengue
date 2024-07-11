package com.example.ultimoChallenge.entities.respuesta;

import java.time.LocalDate;

public record DetallesRespuestaDTO(
        Long id,
        String mensaje,
        LocalDate fecha,
        Long idUsuario,
        Long idTopico
) {
    public DetallesRespuestaDTO(Respuesta respuesta, Long idUsuario, Long idTopico){
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getFecha(), idUsuario, idTopico);
    }
}
