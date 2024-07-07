package com.example.ultimoChallenge.infra.errores;

public class MensajeDuplicado extends RuntimeException{
    public MensajeDuplicado(String mensaje){
        super(mensaje);
    }
}
