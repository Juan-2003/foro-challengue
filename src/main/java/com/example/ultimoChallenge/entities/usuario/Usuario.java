package com.example.ultimoChallenge.entities.usuario;

import com.example.ultimoChallenge.entities.respuesta.Respuesta;
import com.example.ultimoChallenge.entities.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.UserDatabase;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    private String nombre;

    @Column(unique = true)
    private String correo;

    private String contrasena;

    @OneToMany(mappedBy = "usuario")
    private List<Topico> topicos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Respuesta> respuestas = new ArrayList<>();


    public Usuario(RegistroUsuarioDTO registroUsuarioDTO){
        this.nombre = registroUsuarioDTO.nombre();
        this.correo = registroUsuarioDTO.correo();
        this.contrasena = registroUsuarioDTO.contrasena();
    }

    public void actualizarDatos(ActualizarUsuarioDTO actualizarUsuarioDTO){
        if(actualizarUsuarioDTO.nombre() != null){
            this.nombre = actualizarUsuarioDTO.nombre();
        }
        if(actualizarUsuarioDTO.correo() != null){
            this.correo = actualizarUsuarioDTO.correo();
        }
        if(actualizarUsuarioDTO.constrasena() != null){
            this.contrasena = actualizarUsuarioDTO.constrasena();
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
