package com.meli.apifutebol.service;

import com.meli.apifutebol.dto.ClubeDto;
import com.meli.apifutebol.dto.PartidaDto;
import com.meli.apifutebol.model.Clube;
import com.meli.apifutebol.model.Partida;
import com.meli.apifutebol.repository.PartidaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Service
public class PartidaService {

    @Autowired
    PartidaRepository partidaRepository;

    public PartidaDto createPartida(PartidaDto partidaDto) {
        Partida partida = new Partida();
        BeanUtils.copyProperties(partidaDto, partida);
        partida = partidaRepository.save(partida);
        partidaDto.setUuid(partida.getUuid());
        return partidaDto;
    }

    public PartidaDto updatePartida(UUID uuid, PartidaDto partidaDto) {
        Partida partida = partidaRepository.findByUuid(uuid);
        BeanUtils.copyProperties(partidaDto, partida);
        partida = partidaRepository.save(partida);
        return partidaDto;
    }

    public boolean existsPartidaProxima(PartidaDto novaPartidaDto) {
        LocalDateTime novaDataHora = novaPartidaDto.getDataHora().atStartOfDay();

        // Verifica se existe alguma partida próxima para o clube da casa
        boolean existeParaClubeCasa = partidaRepository.existsPartidaProxima(
                novaPartidaDto.getClubeCasa().getUuid(),
                novaDataHora.minusHours(48),
                novaDataHora.plusHours(48));

        // Verifica se existe alguma partida próxima para o clube visitante
        boolean existeParaClubeVisitante = partidaRepository.existsPartidaProxima(
                novaPartidaDto.getClubeVisitante().getUuid(),
                novaDataHora.minusHours(48),
                novaDataHora.plusHours(48));

        // Retorna true se houver alguma partida próxima para qualquer um dos clubes
        return existeParaClubeCasa || existeParaClubeVisitante;
    }

    public boolean existePartidaNoMesmoDia(PartidaDto novaPartidaDto) {
        LocalDate novaData = novaPartidaDto.getDataHora();

        // Verifica se o estádio já possui partida marcada para o mesmo dia
        return partidaRepository.existePartidaNoMesmoDia(novaPartidaDto.getEstadio().getId(), novaData);
    }

}
