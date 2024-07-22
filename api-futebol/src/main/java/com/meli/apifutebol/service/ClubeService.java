package com.meli.apifutebol.service;

import com.meli.apifutebol.enums.StatusClube;
import com.meli.apifutebol.model.Clube;
import com.meli.apifutebol.dto.ClubeDto;

import com.meli.apifutebol.repository.ClubeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;


@Service
public class ClubeService {


    @Autowired
    private ClubeRepository clubeRepository;

    //Não está retornando o id, fazer um toString?
    public ClubeDto createClub(ClubeDto clubeDto) {
        Clube clube = new Clube();
        BeanUtils.copyProperties(clubeDto, clube);
        clube = clubeRepository.save(clube);
        clubeDto.setId(clube.getUuid());
        System.out.println(clube.getUuid());
        return clubeDto;
    }

    public ClubeDto updateClub(UUID uuid, ClubeDto clubeDto) {
        Clube clube = clubeRepository.findByUuid(uuid);
        BeanUtils.copyProperties(clubeDto, clube);
        clube = clubeRepository.save(clube);
        return clubeDto;
    }

    public void inativarClube(UUID uuid) {
        Clube clube = clubeRepository.findByUuid(uuid);
        if (clube == null) {
            throw new NoSuchElementException("Clube não encontrado");
        }

        clube.setAtivo(StatusClube.INATIVO);
        clubeRepository.save(clube);
    }

    public Clube buscarClubePorId(UUID uuid) {
        Clube clube = clubeRepository.findByUuid(uuid);
        if (clube == null) {
            throw new NoSuchElementException("Clube não encontrado");
        }
        return clube;
    }

//    public List<Clube> findAll(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        return clubeRepository.findAll(pageable).getContent();
//    }
}
