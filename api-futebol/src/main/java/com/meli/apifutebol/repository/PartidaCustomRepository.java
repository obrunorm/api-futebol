package com.meli.apifutebol.repository;


import com.meli.apifutebol.model.Clube;
import com.meli.apifutebol.model.Estadio;
import com.meli.apifutebol.model.Partida;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PartidaCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Partida> findAll(Clube clubeCasa, Clube clubeVisitante, Estadio estadio,Integer page, Integer size){

        String query = "SELECT p FROM Partida p ";
        String condicao = "where ";

        if(clubeCasa != null){
            query += condicao + "p.clube_casa_id = :clubeCasa";
            condicao = " and ";
        }

        if(clubeVisitante != null){
            query += condicao + "p.clube_visitante_id = :clubeVisitante";
            condicao = " and ";
        }

        if(estadio != null){
            query += condicao + "p.estadio_id = :estadio";
            condicao = " and ";
        }

        var q = entityManager.createQuery(query, Partida.class);

        if(clubeCasa != null){
            q.setParameter("clubeCasa", clubeCasa);
        }

        if(clubeVisitante != null){
            q.setParameter("clubeVisitante", clubeVisitante);
        }

        if(estadio != null){
            q.setParameter("estadio", estadio);
        }

        if (page != null && size != null) {
            q.setFirstResult(page * size);
            q.setMaxResults(size);
        }

        return q.getResultList();
    }

}
