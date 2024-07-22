package com.meli.apifutebol.service;

import com.meli.apifutebol.dto.EstadioDto;
import com.meli.apifutebol.model.Estadio;
import com.meli.apifutebol.repository.EstadioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class EstadioService {

    @Autowired
    private EstadioRepository estadioRepository;

    public EstadioDto createEstadio(EstadioDto estadioDto) {
        Estadio estadio = new Estadio();
        BeanUtils.copyProperties(estadioDto, estadio);
        estadio = estadioRepository.save(estadio);
        estadioDto.setId(estadio.getId());
        return estadioDto;
    }

    public EstadioDto updateEstadio(UUID uuid, EstadioDto estadioDto) {
        Estadio estadio = estadioRepository.findByUuid(uuid);
        BeanUtils.copyProperties(estadioDto, estadio);
        estadio = estadioRepository.save(estadio);
        return estadioDto;
    }

    public List<Estadio> getAll(String nomeEstadio, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return estadioRepository.findByNomeEstadioContaining(nomeEstadio, pageable);
    }


}
