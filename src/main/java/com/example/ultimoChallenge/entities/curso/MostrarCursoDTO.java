package com.example.ultimoChallenge.entities.curso;

public record MostrarCursoDTO(
        Long id,
        String nombre,
        String categoria
) {
    public MostrarCursoDTO(Curso curso){
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
