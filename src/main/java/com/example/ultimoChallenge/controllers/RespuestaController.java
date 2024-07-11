package com.example.ultimoChallenge.controllers;

import com.example.ultimoChallenge.entities.respuesta.*;
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
@RequestMapping("/respuesta")
public class RespuestaController {
    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private RespuestaService respuestaService;

    @PostMapping
    public ResponseEntity<DetallesRespuestaDTO> altaRespuesta(@RequestBody @Valid RegistroRespuestaDTO registroRespuestaDTO, UriComponentsBuilder uriComponentsBuilder){
        DetallesRespuestaDTO detallesRespuestaDTO = respuestaService.altaRespuesta(registroRespuestaDTO);

        URI url = uriComponentsBuilder.path("/respuesta/{id}")
                .buildAndExpand(detallesRespuestaDTO.id())
                .toUri();


        return ResponseEntity.created(url).body(detallesRespuestaDTO);
    }

    @GetMapping
    public ResponseEntity<Page<MostrarRespuestaDTO>> mostrarRespuestas(Pageable pageable){
        return ResponseEntity.ok(respuestaRepository.findAll(pageable).map(MostrarRespuestaDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MostrarRespuestaDTO> mostrarRespuesta(@PathVariable Long id){
        return ResponseEntity.ok(new MostrarRespuestaDTO(respuestaRepository.getReferenceById(id)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<MostrarRespuestaDTO> actualizarRespuesta(@RequestBody @Valid ActualizarRespuestaDTO actualizarRespuestaDTO){
        MostrarRespuestaDTO mostrarRespuestaDTO = respuestaService.actualizarRespuesta(actualizarRespuestaDTO);
        return ResponseEntity.ok(mostrarRespuestaDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        respuestaService.eliminarRespuesta(id);
        return ResponseEntity.noContent().build();
    }

}
