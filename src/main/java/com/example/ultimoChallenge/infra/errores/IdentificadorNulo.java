package com.example.ultimoChallenge.infra.errores;

public class IdentificadorNulo extends RuntimeException{
    public IdentificadorNulo(String mensaje){
        super(mensaje);
    }
}
