package com.example.ultimoChallenge.controllers;

import com.example.ultimoChallenge.entities.topico.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "auth")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DetallesTopicoDTO> altaTopico(@RequestBody @Valid RegistroTopicoDTO registroTopicoDTO, UriComponentsBuilder uriComponentsBuilder){
        DetallesTopicoDTO detallesTopicoDTO = topicoService.altaTopico(registroTopicoDTO);

        URI url = uriComponentsBuilder.path("/topicos/{id}")
                .buildAndExpand(detallesTopicoDTO.id())
                .toUri();

        return ResponseEntity.created(url).body(detallesTopicoDTO);
    }

    @GetMapping
    public ResponseEntity<Page<MostrarTopicoDTO>> mostrarTopicos(@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(topicoRepository.findAll(pageable).map(MostrarTopicoDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MostrarTopicoDTO> mostrarTopico(@PathVariable Long id){
        topicoService.mostrarTopico(id);
        return ResponseEntity.ok(new MostrarTopicoDTO(topicoRepository.getReferenceById(id)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<MostrarTopicoDTO> actualizarTopico(@RequestBody @Valid ActualizarTopicoDTO actualizarTopicoDTO){
        MostrarTopicoDTO mostrarTopicoDTO = topicoService.actualizarTopico(actualizarTopicoDTO);
        return ResponseEntity.ok(mostrarTopicoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        topicoService.eliminiarTopico(id);
        return ResponseEntity.noContent().build();
    }

}
