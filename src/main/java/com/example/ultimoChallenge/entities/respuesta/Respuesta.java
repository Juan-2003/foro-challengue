package com.example.ultimoChallenge.entities.respuesta;

import com.example.ultimoChallenge.entities.topico.Topico;
import com.example.ultimoChallenge.entities.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "Respuesta")
@Table(name = "respuestas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    @Column(name = "fecha_respuesta")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "topico_id", referencedColumnName = "id")
    private Topico topico;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    public Respuesta(RegistroRespuestaDTO registroRespuestaDTO, Usuario usuario, Topico topico){
        this.mensaje = registroRespuestaDTO.mensaje();
        this.usuario = usuario;
        this.topico = topico;
        this.fecha = LocalDate.now();
    }

    public void actualizarRespuesta(ActualizarRespuestaDTO actualizarRespuestaDTO){
        if(actualizarRespuestaDTO.mensaje() != null){
            this.mensaje = actualizarRespuestaDTO.mensaje();
            this.fecha = LocalDate.now();
        }
    }
}
