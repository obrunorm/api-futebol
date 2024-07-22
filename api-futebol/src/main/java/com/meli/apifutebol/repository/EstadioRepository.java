package com.meli.apifutebol.repository;

import com.meli.apifutebol.model.Estadio;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EstadioRepository extends JpaRepository<Estadio, UUID> {
    Estadio findByUuid(UUID uuid);

    List<Estadio> findByNomeEstadioContaining(String nomeEstadio, Pageable pageable);

    boolean existsByNomeEstadio(String nomeEstadio);
}
