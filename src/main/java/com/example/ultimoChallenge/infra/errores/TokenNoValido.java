package com.example.ultimoChallenge.infra.errores;

public class TokenNoValido extends RuntimeException{
    public TokenNoValido(String mensaje){
        super(mensaje);
    }
}
