package com.example.ultimoChallenge.entities.respuesta;

import com.example.ultimoChallenge.entities.topico.MostrarTopicoDTO;

import java.time.LocalDate;

public record MostrarRespuestaDTO(
        Long id,
        String mensaje,
        String nombreUsuario,
        String nombreTopico,
        LocalDate fecha
) {
    public MostrarRespuestaDTO(Respuesta respuesta){
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getUsuario().getNombre(),
                respuesta.getTopico().getTitulo(), respuesta.getFecha());
    }
}
