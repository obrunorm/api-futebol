package com.meli.apifutebol.service;

import com.meli.apifutebol.dto.EstadioDto;
import com.meli.apifutebol.model.Estadio;
import org.springframework.stereotype.Service;


@Service
public class EstadioService {

    public String salvar(EstadioDto estadioDto) {
//        estadioRepository.save(estadio);
        Estadio estadio = converter(estadioDto);
        return "Estadio cadastrado com sucesso!";
    }

    public String atualizarEstadio(EstadioDto estadioDto) {
        if (estadioDto.getNomeEstadio().equals(estadioDto)) {
            Estadio estadio = converter(estadioDto);
//          estadioRepository.save(estadio);
            return "Estadio Atualizado com sucesso!";
        } else {
            return "Estadio não encontrado";
        }
    }

//    public Page<Estatio> buscarEstadios(String nomeEstagio, Pageable pageable) {
//        if (nome == null) nomeEstadio = "";
//        return estadioRepository.findByNomeContainingAndEstadio(nomeEstadiostadio, pageable);
//    }

    private Estadio converter(EstadioDto estadioDto) {
        Estadio estadio = new Estadio();
        estadio.setNomeEstadio(estadio.getNomeEstadio());
        return estadio;
    }
}
