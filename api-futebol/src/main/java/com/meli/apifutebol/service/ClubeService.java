package com.meli.apifutebol.service;
import com.meli.apifutebol.exceptions.DuplicateNameException;
import com.meli.apifutebol.exceptions.InvalidClubDataException;
import com.meli.apifutebol.model.Clube;
import com.meli.apifutebol.dto.ClubeDto;

import com.meli.apifutebol.repository.ClubeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Service
public class ClubeService {


    @Autowired
    private ClubeRepository clubeRepository;

    public Clube createClube(Clube clube) {
        if (clubeRepository.existsByNome(clube.getNome())) {
            throw new DuplicateNameException();
        }
        if (clube.getNome() == null || clube.getNome().length() < 2) {
            throw new InvalidClubDataException("Nome do clube deve ter pelo menos duas letras e não deve ser nulo.");
        }
        if (clube.getDataCriacao() == null || clube.getDataCriacao().isAfter(LocalDate.now())) {
            throw new InvalidClubDataException("Data de criação do clube não pode ser no futuro e nem nula.");
        }
        return clubeRepository.save(clube);
    }

    public Clube atualizarClube(@NonNull UUID uuid, ClubeDto clubeDto) {
            Clube clube = clubeRepository.findByUuid(uuid);
            BeanUtils.copyProperties(clubeDto, clube);
            return clubeRepository.save(clube);
        }

    public List<Clube> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return clubeRepository.findAll(pageable).getContent();
    }
}
