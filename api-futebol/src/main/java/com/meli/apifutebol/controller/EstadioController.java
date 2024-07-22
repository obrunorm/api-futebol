package com.meli.apifutebol.controller;

import com.meli.apifutebol.dto.EstadioDto;
import com.meli.apifutebol.exceptions.DuplicateNameException;
import com.meli.apifutebol.exceptions.InvalidDataException;
import com.meli.apifutebol.exceptions.NotFoundException;
import com.meli.apifutebol.model.Estadio;
import com.meli.apifutebol.repository.EstadioRepository;
import com.meli.apifutebol.service.EstadioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("estadio")
public class EstadioController {

    @Autowired
    private EstadioService estadioService;

    @Autowired
    EstadioRepository estadioRepository;

//    @PostMapping
//    public ResponseEntity<EstadioDto> salvaEstadio(@RequestBody EstadioDto estadioDto) {
//        return new ResponseEntity<>(estadioService.createEstadio(estadioDto), HttpStatus.CREATED);
//    }

//    @PutMapping("/{uuid}")
//    public ResponseEntity atualizaEstadio(@PathVariable UUID uuid, @RequestBody EstadioDto estadioDto) {
//        return new ResponseEntity<>(estadioService.updateEstadio(uuid, estadioDto), HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<EstadioDto> salvaEstadio(@RequestBody EstadioDto estadioDto) {
        if (estadioDto.getNomeEstadio() == null || estadioDto.getNomeEstadio().length() < 3) {
            throw new InvalidDataException("O nome do estádio deve ter pelo menos 3 caracteres.");
        }

        if (estadioRepository.existsByNomeEstadio(estadioDto.getNomeEstadio())) {
            throw new DuplicateNameException("Já existe um estádio cadastrado com esse nome.");
        }

        return new ResponseEntity<>(estadioService.createEstadio(estadioDto), HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity atualizaEstadio(@PathVariable UUID uuid, @RequestBody EstadioDto estadioDto) {
        if (!estadioRepository.existsById(uuid)) {
            throw new NotFoundException("Estádio não encontrado com o UUID fornecido.");
        }

        if (estadioDto.getNomeEstadio() == null || estadioDto.getNomeEstadio().length() < 3) {
            throw new InvalidDataException("O nome do estádio deve ter pelo menos 3 caracteres.");
        }

        Estadio existingEstadio = estadioRepository.findByUuid(uuid);
        if (!existingEstadio.getNomeEstadio().equals(estadioDto.getNomeEstadio()) && estadioRepository.existsByNomeEstadio(estadioDto.getNomeEstadio())) {
            throw new DuplicateNameException("Já existe um estádio cadastrado com esse nome.");
        }

        return new ResponseEntity<>(estadioService.updateEstadio(uuid, estadioDto), HttpStatus.OK);
    }


    @GetMapping("/{uuid}")
    public ResponseEntity<Estadio> buscaEstadio(@PathVariable UUID uuid) {
        if (!estadioRepository.existsById(uuid)) {
            throw new NotFoundException("Esse estadio não existe!");
        }
        return ResponseEntity.ok(estadioRepository.findByUuid(uuid));
    }

    @GetMapping("/filter/custom")
    public ResponseEntity<List<Estadio>> listarEstadios(
            @RequestParam(value = "nomeEstadio", required = false) String nomeEstadio,
            @RequestParam (value = "page", required = false) int page,
            @RequestParam (value = "size", required = false) int size) {

        return ResponseEntity.ok(estadioService.getAll(nomeEstadio, page, size));
    }
}
