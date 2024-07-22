package com.meli.apifutebol.controller;

import com.meli.apifutebol.dto.PartidaDto;
import com.meli.apifutebol.exceptions.DuplicateNameException;
import com.meli.apifutebol.exceptions.InvalidDataException;
import com.meli.apifutebol.exceptions.NotFoundException;
import com.meli.apifutebol.model.Clube;
import com.meli.apifutebol.model.Estadio;
import com.meli.apifutebol.model.Partida;
import com.meli.apifutebol.repository.EstadioRepository;
import com.meli.apifutebol.repository.PartidaCustomRepository;
import com.meli.apifutebol.repository.PartidaRepository;
import com.meli.apifutebol.service.PartidaService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("partida")
public class PartidaController {

    @Autowired
    private PartidaService partidaService;

    @Autowired
    PartidaRepository partidaRepository;

    @Autowired
    PartidaCustomRepository partidaCustomRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    EstadioRepository estadioRepository;

//    @PostMapping
//    public ResponseEntity<PartidaDto> salvaPartida(@RequestBody PartidaDto partidaDto) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(partidaService.createPartida(partidaDto));
//    }
//
//    @PutMapping("/{uuid}")
//    public ResponseEntity<PartidaDto> atualizaPartida(@RequestBody PartidaDto partidaDto, @PathVariable UUID uuid) {
//        return ResponseEntity.status(HttpStatus.OK).body(partidaService.updatePartida(uuid, partidaDto));
//    }
//
//    @DeleteMapping("/{uuid}")
//    public void deletaPartida(@PathVariable UUID uuid) {
//        partidaRepository.deleteById(uuid);
//    }
//
//    @GetMapping("/{uuid}")
//    public ResponseEntity<Partida> buscaPartida(@PathVariable UUID uuid) {
//        return ResponseEntity.ok(partidaRepository.findByUuid(uuid));
//    }

    @PostMapping
    public ResponseEntity<PartidaDto> salvaPartida(@RequestBody PartidaDto partidaDto) {
        if (partidaDto.getClubeCasa() == null || partidaDto.getClubeVisitante() == null ||
                partidaDto.getEstadio() == null || partidaDto.getDataHora() == null || partidaDto.getResultadoClubeCasa() < 0 || partidaDto.getResultadoClubeVisitante() < 0) {
            throw new InvalidDataException("Todos os dados mínimos da partida devem ser fornecidos.");
        }

        if (partidaDto.getClubeCasa().equals(partidaDto.getClubeVisitante())) {
            throw new InvalidDataException("Os clubes da partida não podem ser iguais.");
        }

        if (!estadioRepository.existsById(partidaDto.getEstadio().getId())) {
            throw new NotFoundException("Estádio não encontrado.");
        }

        if (partidaDto.getDataHora().isAfter(LocalDate.now())) {
            throw new InvalidDataException("A data e hora da partida não podem estar no futuro.");
        }

        if (partidaService.existsPartidaProxima(partidaDto)) {
            throw new DuplicateNameException("Um dos clubes já possui outra partida marcada com menos de 48 horas de diferença.");
        }

        if (partidaService.existePartidaNoMesmoDia(partidaDto)) {
            throw new DuplicateNameException("O estádio já possui outra partida marcada para o mesmo dia.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(partidaService.createPartida(partidaDto));
    }


    @PutMapping("/{uuid}")
    public ResponseEntity<PartidaDto> atualizaPartida(@RequestBody PartidaDto partidaDto, @PathVariable UUID uuid) {
        if (!partidaRepository.existsById(uuid)) {
            throw new NotFoundException("Partida não encontrada com o UUID fornecido.");
        }

        if (partidaDto.getClubeCasa().equals(partidaDto.getClubeVisitante())) {
            throw new InvalidDataException("Os clubes da partida não podem ser iguais.");
        }

        if (!estadioRepository.existsById(partidaDto.getEstadio().getId())) {
            throw new NotFoundException("Estádio não encontrado.");
        }

        if (partidaDto.getResultadoClubeVisitante() < 0 || partidaDto.getResultadoClubeCasa() < 0) {
            throw new InvalidDataException("O resultado da partida não pode ter número negativo de gols.");
        }

        if (partidaDto.getDataHora().isAfter(LocalDate.now())) {
            throw new InvalidDataException("A data e hora da partida não podem estar no futuro.");
        }

        if (partidaService.existsPartidaProxima(partidaDto)) {
            throw new DuplicateNameException("Um dos clubes já possui outra partida marcada com menos de 48 horas de diferença.");
        }

        if (partidaService.existePartidaNoMesmoDia(partidaDto)) {
            throw new InvalidDataException("O estádio já possui outra partida marcada para o mesmo dia.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(partidaService.updatePartida(uuid, partidaDto));
    }

    @DeleteMapping("/{uuid}")
    public void deletaPartida(@PathVariable UUID uuid) {
        if (!partidaRepository.existsById(uuid)) {
            throw new NotFoundException("Partida não encontrada com o UUID fornecido.");
        }

        partidaRepository.deleteById(uuid);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Partida> buscaPartida(@PathVariable UUID uuid) {
        Partida partida = partidaRepository.findByUuid(uuid);

        if (partida == null) {
            throw new NotFoundException(
                    "Partida não encontrada com o UUID fornecido.");
        }

        return ResponseEntity.ok(partida);
    }


    @GetMapping("/filter/custom")
    public ResponseEntity<List<Partida>> findClubeByCustom(
            @RequestParam(value = "clubeCasa", required = false) Clube clubeCasa,
            @RequestParam(value = "clubeVisitante", required = false) Clube clubeVisitante,
            @RequestParam(value = "estadio", required = false) Estadio estadio,
            @RequestParam (value = "page", required = false) int page,
            @RequestParam (value = "size", required = false) int size)
    {
        return ResponseEntity.ok(partidaCustomRepository.findAll(clubeCasa, clubeVisitante, estadio, page, size));
    }
}
