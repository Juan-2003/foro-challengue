package com.example.ultimoChallenge.entities.topico;

import java.time.LocalDate;

public record MostrarTopicoDTO(
        Long id,
        String nombreUsuario,
        String nomobreCurso,
        String titulo,
        String mensaje,
        LocalDate fecha,
        boolean estatus

) {
    public MostrarTopicoDTO(Topico topico){
        this(topico.getId(), topico.getUsuario().getNombre(), topico.getCurso().getNombre(), topico.getTitulo(),
                topico.getMensaje(), topico.getFecha(), topico.isEstatus());
    }
}
