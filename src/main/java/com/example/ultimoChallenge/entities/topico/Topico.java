package com.example.ultimoChallenge.entities.topico;

import com.example.ultimoChallenge.entities.curso.Curso;
import com.example.ultimoChallenge.entities.respuesta.Respuesta;
import com.example.ultimoChallenge.entities.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity(name = "Topico")
@Table(name = "topicos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "titulo_publicacion")
    private String titulo;
    private String mensaje;

    @Column(name = "fecha_publicacion")
    private LocalDate fecha;
    private boolean estatus;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name= "curso_id", referencedColumnName = "id")
    private Curso curso;

    @OneToMany(mappedBy = "topico")
    private List<Respuesta> respuestas = new ArrayList<>();

    public Topico(RegistroTopicoDTO registroTopicoDTO, Usuario usuario, Curso curso){
        this.titulo = registroTopicoDTO.titulo();
        this.mensaje = registroTopicoDTO.mensaje();
        this.fecha = LocalDate.now();
        this.usuario = usuario;
        this.curso = curso;
        this.estatus = true;
    }

    public void actualizarTopico(ActualizarTopicoDTO actualizarTopicoDTO) {
        if(actualizarTopicoDTO.titulo() != null){
            this.titulo = actualizarTopicoDTO.titulo();
        }
        if(actualizarTopicoDTO.mensaje() != null) {
            this.mensaje = actualizarTopicoDTO.mensaje();
        }
    }

}
