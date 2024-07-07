package com.example.ultimoChallenge.entities.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    @Query("""
            SELECT EXISTS(
                SELECT t
                FROM Topico t
                WHERE LOWER(t.titulo) = LOWER(:titulo)
            ) 
    """)
    public Boolean encontrarTitulosIguales(String titulo);

    @Query("""
    SELECT EXISTS(
        SELECT t
        FROM Topico t
        WHERE LOWER(t.mensaje) = LOWER(:mensaje)
    ) 
    """)
    public Boolean encontrarMensajesIguales(String mensaje);
}
