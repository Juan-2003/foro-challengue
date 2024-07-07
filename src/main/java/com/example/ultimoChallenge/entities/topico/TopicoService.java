package com.example.ultimoChallenge.entities.topico;

import com.example.ultimoChallenge.entities.curso.Curso;
import com.example.ultimoChallenge.entities.curso.CursoRepository;
import com.example.ultimoChallenge.entities.usuario.Usuario;
import com.example.ultimoChallenge.entities.usuario.UsuarioRepository;
import com.example.ultimoChallenge.infra.errores.IdentificadorNoEcontrado;
import com.example.ultimoChallenge.infra.errores.IdentificadorNulo;
import com.example.ultimoChallenge.infra.errores.MensajeDuplicado;
import com.example.ultimoChallenge.infra.errores.TituloDuplicado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicoService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public DetallesTopicoDTO altaTopico(RegistroTopicoDTO registroTopicoDTO){
        if(topicoRepository.encontrarTitulosIguales(registroTopicoDTO.titulo())){
            throw new TituloDuplicado("Ya existe una publicacion con el titulo"+registroTopicoDTO.titulo()+".Elija otro.");
        }
        if(topicoRepository.encontrarMensajesIguales(registroTopicoDTO.mensaje())){
            throw new MensajeDuplicado("Ya existe una publicacion con el mensaje"+registroTopicoDTO.mensaje()+".Modifiquelo.");
        }

        Long idUsuario = registroTopicoDTO.idUsuario();
        Long idCurso = registroTopicoDTO.idCurso();

        Usuario usuario = usuarioRepository.getReferenceById(idUsuario);
        Curso curso = cursoRepository.getReferenceById(idCurso);

        Topico topico = new Topico(registroTopicoDTO, usuario, curso);
        topicoRepository.save(topico);

        return new DetallesTopicoDTO(topico, idUsuario, idCurso);
    }

    public void mostrarTopico(Long id){
        if(id == null){
            throw new IdentificadorNulo("El id es obligatorio");
        }

        Optional<Topico> topico = topicoRepository.findById(id);

        if(topico.isEmpty()){
            throw new IdentificadorNoEcontrado("No existe ese id: "+id);
        }
    }

    public void actualizarTopico(ActualizarTopicoDTO actualizarTopicoDTO){
        if(topicoRepository.encontrarTitulosIguales(actualizarTopicoDTO.titulo())){
            throw new TituloDuplicado("Ya existe una publicacion con el titulo: "+actualizarTopicoDTO.titulo()+".Elija otro.");
        }
        if(topicoRepository.encontrarMensajesIguales(actualizarTopicoDTO.mensaje())){
            throw new MensajeDuplicado("Ya existe una publicacion con el mensaje: "+actualizarTopicoDTO.mensaje()+".Modifiquelo.");
        }

        Topico topico = topicoRepository.getReferenceById(actualizarTopicoDTO.id());
        topico.actualizarTopico(actualizarTopicoDTO);
    }

    public void eliminiarTopico(Long id){
        if(id == null){
            throw new IdentificadorNulo("El id es obligatorio");
        }

        Optional<Topico> topico = topicoRepository.findById(id);

        if(topico.isEmpty()){
            throw new IdentificadorNoEcontrado("No existe ese id: "+id);
        }
    }
}
