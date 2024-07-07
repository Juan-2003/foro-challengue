package com.example.ultimoChallenge.entities.curso;

import com.example.ultimoChallenge.entities.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Curso")
@Table(name = "cursos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String nombre;
    private String categoria;

    @OneToMany(mappedBy = "curso")
    private List<Topico> topicos = new ArrayList<>();

    public Curso(RegistroCursoDTO registroCursoDTO) {
        this.nombre = registroCursoDTO.nombre();
        this.categoria = registroCursoDTO.categoria();
    }

    public void actualizarCurso(ActualizarCursoDTO actualizarCursoDTO){
        if(actualizarCursoDTO.nombre() != null){
            this.nombre = actualizarCursoDTO.nombre();
        }
        if(actualizarCursoDTO.categoria() != null){
            this.categoria = actualizarCursoDTO.categoria();
        }
    }
}
