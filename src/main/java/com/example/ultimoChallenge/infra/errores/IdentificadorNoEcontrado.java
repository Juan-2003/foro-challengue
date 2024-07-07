package com.example.ultimoChallenge.infra.errores;

public class IdentificadorNoEcontrado extends RuntimeException{
    public IdentificadorNoEcontrado(String mensaje){
        super(mensaje);
    }
}
