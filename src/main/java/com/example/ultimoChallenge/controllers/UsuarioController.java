package com.example.ultimoChallenge.controllers;

import com.example.ultimoChallenge.entities.usuario.*;
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
@RequestMapping("/usuarios")
@SecurityRequirement(name = "auth")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    //201
    @PostMapping
    public ResponseEntity<DetallesUsuarioDTO> altaUsuario(@RequestBody @Valid RegistroUsuarioDTO registroUsuarioDTO, UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario =  usuarioRepository.save(new Usuario(registroUsuarioDTO));
        DetallesUsuarioDTO detallesUsuarioDTO = new DetallesUsuarioDTO(usuario);
        System.out.println(detallesUsuarioDTO);
        URI url = uriComponentsBuilder.path("/usuarios/{id}")
                .buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(url).body(detallesUsuarioDTO);
    }

    //200
    @GetMapping
    public ResponseEntity<Page<MostrarUsuarioDTO>> mostrarUsuarios(Pageable pageable){
        return ResponseEntity.ok(usuarioRepository.findAll(pageable).map(MostrarUsuarioDTO::new));
    }

    //200
    @GetMapping("/{id}")
    public ResponseEntity<MostrarUsuarioDTO> mostrarUsuario(@PathVariable Long id){
        return ResponseEntity.ok(new MostrarUsuarioDTO(usuarioRepository.getReferenceById(id)));
    }

    @PutMapping()
    @Transactional
    public ResponseEntity actualizarUsuario(@RequestBody @Valid ActualizarUsuarioDTO actualizarUsuarioDTO){
        Usuario usuario = usuarioRepository.getReferenceById(actualizarUsuarioDTO.id());
        usuario.actualizarDatos(actualizarUsuarioDTO);
        return ResponseEntity.ok(new DetallesUsuarioDTO(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);
        return ResponseEntity.noContent().build();
    }
}
