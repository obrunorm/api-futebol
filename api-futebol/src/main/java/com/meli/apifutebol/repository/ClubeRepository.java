package com.meli.apifutebol.repository;

import com.meli.apifutebol.enums.Estados;
import com.meli.apifutebol.enums.StatusClube;
import com.meli.apifutebol.model.Clube;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClubeRepository extends JpaRepository<Clube, UUID> {

    Clube findByUuid(UUID uuid);

//    Page<Clube> findAll(Pageable pageable);

    boolean existsByNome(String nome);

    boolean existsByNomeAndEstados(String nome, Estados estados);

}
