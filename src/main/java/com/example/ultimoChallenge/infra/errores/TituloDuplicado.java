package com.example.ultimoChallenge.infra.errores;

public class TituloDuplicado extends RuntimeException {
    public TituloDuplicado(String mensaje){
        super(mensaje);
    }
}
