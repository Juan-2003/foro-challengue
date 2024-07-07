package com.example.ultimoChallenge.controllers;


import com.example.ultimoChallenge.entities.curso.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "auth")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping()
    public ResponseEntity<DetallesCursoDTO> altaCurso(@RequestBody @Valid RegistroCursoDTO registroCursoDTO, UriComponentsBuilder uriComponentsBuilder){
        Curso curso = new Curso(registroCursoDTO);
        cursoRepository.save(curso);

        DetallesCursoDTO detallesCursoDTO = new DetallesCursoDTO(curso);
        URI url = uriComponentsBuilder.path("/cursos/{id}")
                .buildAndExpand(curso.getId()).toUri();

        return ResponseEntity.created(url).body(detallesCursoDTO);
    }

    @GetMapping
    public ResponseEntity<Page<MostrarCursoDTO>> mostrarCursos(Pageable pageable){
        return ResponseEntity.ok(cursoRepository.findAll(pageable).map(MostrarCursoDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity mostrarCurso(@PathVariable Long id){
        return ResponseEntity.ok(new MostrarCursoDTO(cursoRepository.getReferenceById(id)));
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<DetallesCursoDTO> actualizarCurso(@RequestBody @Valid ActualizarCursoDTO actualizarCursoDTO){
        Curso curso = cursoRepository.getReferenceById(actualizarCursoDTO.id());
        curso.actualizarCurso(actualizarCursoDTO);

        return ResponseEntity.ok(new DetallesCursoDTO(curso));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarCurso(@PathVariable Long id){
        Curso curso = cursoRepository.getReferenceById(id);
        cursoRepository.delete(curso);
        return ResponseEntity.noContent().build();
    }
}
