package com.example.ultimoChallenge.entities.usuario;

public record MostrarUsuarioDTO(
        Long id,
        String nombre,
        String correo
) {
    public MostrarUsuarioDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getCorreo());
    }

}
