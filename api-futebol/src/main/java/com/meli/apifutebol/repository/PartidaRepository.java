package com.meli.apifutebol.repository;

import com.meli.apifutebol.dto.PartidaDto;
import com.meli.apifutebol.enums.Estados;
import com.meli.apifutebol.model.Clube;
import com.meli.apifutebol.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, UUID> {
    Partida findByUuid(UUID uuid);


    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Partida p " +
            "WHERE (p.clubeCasa.uuid = :clubeId OR p.clubeVisitante.uuid = :clubeId) " +
            "AND p.dataHora BETWEEN :dataInicio AND :dataFim")
    boolean existsPartidaProxima(@Param("clubeId") UUID clubeId,
                                 @Param("dataInicio") LocalDateTime dataInicio,
                                 @Param("dataFim") LocalDateTime dataFim);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Partida p " +
            "WHERE p.estadio.uuid = :estadioId AND FUNCTION('DATE', p.dataHora) = :data")
    boolean existePartidaNoMesmoDia(@Param("estadioId") UUID estadioId,
                                    @Param("data") LocalDate data);

}