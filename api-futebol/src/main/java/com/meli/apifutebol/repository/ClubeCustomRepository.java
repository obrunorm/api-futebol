package com.meli.apifutebol.repository;

import com.meli.apifutebol.enums.Estados;
import com.meli.apifutebol.enums.StatusClube;
import com.meli.apifutebol.model.Clube;
import org.springframework.stereotype.Repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ClubeCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Clube> findAll(String nome, Estados estados, StatusClube ativo){

        String query = "SELECT c FROM Clube c ";
        String condicao = "where ";

        if(nome != null){
            query += condicao + "c.nome = :nome";
            condicao = " and ";
        }

        if(estados != null){
            query += condicao + "c.siglaEstado = :estadosiglaEstado";
            condicao = " and ";
        }
        if(ativo != null){
            query += condicao + "c.ativo = :ativo";
            condicao = " and ";
        }

        var q = entityManager.createQuery(query, Clube.class);

        if(nome != null){
            q.setParameter("nome", nome);
        }

        if(estados != null){
            q.setParameter("siglaEstado", estados);
        }
        if(ativo != null){
            q.setParameter("ativo", ativo);
        }

        return q.getResultList();
    }

}