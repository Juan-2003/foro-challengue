package com.example.ultimoChallenge.controllers;


import com.example.ultimoChallenge.entities.usuario.AutenticarUsuarioDTO;
import com.example.ultimoChallenge.entities.usuario.Usuario;
import com.example.ultimoChallenge.infra.config.DatosJWTtoken;
import com.example.ultimoChallenge.infra.config.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@SecurityRequirement(name = "auth")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid AutenticarUsuarioDTO autenticarUsuarioDTO){
        Authentication infoUsuario = new UsernamePasswordAuthenticationToken(
                autenticarUsuarioDTO.correo(), autenticarUsuarioDTO.contrasena());
        var usuarioAutenticado = authenticationManager.authenticate(infoUsuario);
        String JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
    }
}
