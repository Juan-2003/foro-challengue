package com.example.ultimoChallenge.entities.usuario;

public record DetallesUsuarioDTO(
        Long id,
        String nombre,
        String correo,
        String constrasena
) {
    public DetallesUsuarioDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getCorreo(), usuario.getContrasena());
    }
}
