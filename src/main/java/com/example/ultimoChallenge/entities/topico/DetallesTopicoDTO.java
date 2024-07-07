package com.example.ultimoChallenge.entities.topico;

import java.time.LocalDate;

public record DetallesTopicoDTO(
        Long id,
        Long idUsuario,
        Long idCurso,
        String mensaje,
        String titulo,
        LocalDate fecha
) {
    public DetallesTopicoDTO(Topico topico, Long idUsuario, Long idCurso){
        this(topico.getId(), idUsuario, idCurso, topico.getMensaje(), topico.getTitulo(), topico.getFecha());
    }
}
