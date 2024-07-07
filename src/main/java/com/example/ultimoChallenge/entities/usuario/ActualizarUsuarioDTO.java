package com.example.ultimoChallenge.entities.usuario;

import jakarta.validation.constraints.NotNull;

public record ActualizarUsuarioDTO(
        @NotNull
        Long id,
        String nombre,
        String correo,
        String constrasena
) {
    public ActualizarUsuarioDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getCorreo(), usuario.getContrasena());
    }
}
