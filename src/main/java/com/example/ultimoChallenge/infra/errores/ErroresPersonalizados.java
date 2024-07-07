package com.example.ultimoChallenge.infra.errores;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErroresPersonalizados {
    @ExceptionHandler(TituloDuplicado.class)
    public ResponseEntity<DatosErrorValidacion> tratarTituloDuplicado(TituloDuplicado e){
        DatosErrorValidacion datosErrorValidacion = new DatosErrorValidacion(
                "Titulo Duplicado", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(datosErrorValidacion);
    }

    @ExceptionHandler(MensajeDuplicado.class)
    public ResponseEntity<DatosErrorValidacion> tratarMensajeDuplicado(MensajeDuplicado e){
        DatosErrorValidacion datosErrorValidacion = new DatosErrorValidacion(
                "Mensaje Duplicado", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(datosErrorValidacion);
    }

    @ExceptionHandler(IdentificadorNulo.class)
    public ResponseEntity<DatosErrorValidacion> tratarIdentificadorNulo(IdentificadorNulo e){
        DatosErrorValidacion datosErrorValidacion = new DatosErrorValidacion(
                "Id sin especificar", e.getMessage());
        return ResponseEntity.badRequest().body(datosErrorValidacion);
    }

    @ExceptionHandler(IdentificadorNoEcontrado.class)
    public ResponseEntity<DatosErrorValidacion> tratarIdentificadorNoEcontrado(IdentificadorNoEcontrado e){
        DatosErrorValidacion datosErrorValidacion = new DatosErrorValidacion(
                "Id no encontrado", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(datosErrorValidacion);

    }

    @ExceptionHandler(TokenNoValido.class)
    public ResponseEntity<DatosErrorValidacion> tratarErrorNoValido(TokenNoValido e){
        DatosErrorValidacion datosErrorValidacion = new DatosErrorValidacion(
                "Token Invalido", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(datosErrorValidacion);
    }



    private record DatosErrorValidacion(
            String mensaje,
            String detalle
    ){
        public DatosErrorValidacion(String mensaje, String detalle){
            this.mensaje = mensaje;
            this.detalle = detalle;
        }
    }
}
