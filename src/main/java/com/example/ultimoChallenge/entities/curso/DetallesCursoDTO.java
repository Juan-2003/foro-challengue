package com.example.ultimoChallenge.entities.curso;

public record DetallesCursoDTO(
        Long id,
        String nombre,
        String categoria
) {
    public DetallesCursoDTO(Curso curso){
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
