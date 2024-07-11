package com.example.ultimoChallenge.entities.respuesta;

import com.example.ultimoChallenge.entities.topico.MostrarTopicoDTO;
import com.example.ultimoChallenge.entities.topico.Topico;
import com.example.ultimoChallenge.entities.topico.TopicoRepository;
import com.example.ultimoChallenge.entities.usuario.Usuario;
import com.example.ultimoChallenge.entities.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.context.support.UiApplicationContextUtils;

@Service
public class RespuestaService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    public DetallesRespuestaDTO altaRespuesta(RegistroRespuestaDTO registroRespuestaDTO){
        Long idUsuario = registroRespuestaDTO.idUsuario();
        Long idTopico = registroRespuestaDTO.idTopico();

        Usuario usuario = usuarioRepository.getReferenceById(idUsuario);
        Topico topico = topicoRepository.getReferenceById(idTopico);

        Respuesta respuesta = new Respuesta(registroRespuestaDTO, usuario, topico);

        respuestaRepository.save(respuesta);
        return new DetallesRespuestaDTO(respuesta, idUsuario, idTopico);
    }

    public MostrarRespuestaDTO actualizarRespuesta(ActualizarRespuestaDTO actualizarRespuestaDTO){
        Respuesta respuesta = respuestaRepository.getReferenceById(actualizarRespuestaDTO.id());
        respuesta.actualizarRespuesta(actualizarRespuestaDTO);

        return new MostrarRespuestaDTO(respuesta);
    }

    public void eliminarRespuesta(Long id){
        respuestaRepository.delete(respuestaRepository.getReferenceById(id));
    }

}
