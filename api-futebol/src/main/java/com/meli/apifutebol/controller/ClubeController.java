package com.meli.apifutebol.controller;

import com.meli.apifutebol.dto.ClubeDto;
import com.meli.apifutebol.enums.Estados;
import com.meli.apifutebol.enums.StatusClube;
import com.meli.apifutebol.exceptions.NotFoundException;
import com.meli.apifutebol.model.Clube;
import com.meli.apifutebol.repository.ClubeCustomRepository;
import com.meli.apifutebol.repository.ClubeRepository;
import com.meli.apifutebol.service.ClubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("clube")
public class ClubeController {


    @Autowired
    private ClubeService clubeService;

    @Autowired
    private ClubeCustomRepository clubeCustomRespository;
    @Autowired
    private ClubeRepository clubeRepository;

    @PostMapping
    public ResponseEntity<ClubeDto> salvaClube(@RequestBody ClubeDto clubeDto) {
        if (clubeDto.getNome() == null || clubeDto.getNome().length() < 2 ||
                clubeDto.getEstados() == null || !Estados.contains(clubeDto.getEstados().toString()) ||
                clubeDto.getDataCriacao() == null || clubeDto.getDataCriacao().isAfter(LocalDate.now())) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados inválidos para cadastro de clube.");
        }

        if (clubeRepository.existsByNomeAndEstado(clubeDto.getNome(), Estados.valueOf(clubeDto.getEstados().toString()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um clube com esse nome no mesmo estado.");
        }

        ClubeDto createdClube = clubeService.createClub(clubeDto);
        return new ResponseEntity<>(createdClube, HttpStatus.CREATED);
    }


    @PutMapping("/{uuid}")
    public ResponseEntity<ClubeDto> atualizaClube(@PathVariable UUID uuid, @RequestBody ClubeDto clubeDto) {
        // Verifica se o clube com o UUID especificado existe
        if (!clubeRepository.existsById(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Clube não encontrado.");
        }

        if (clubeDto.getNome() == null || clubeDto.getNome().length() < 2 ||
                clubeDto.getEstados() == null || !Estados.contains(clubeDto.getEstados().toString()) ||
                clubeDto.getDataCriacao() == null || clubeDto.getDataCriacao().isAfter(LocalDate.now())) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados inválidos para atualização de clube.");
        }

        Clube existingClube = clubeRepository.findById(uuid).orElse(null);
        if (existingClube != null &&
                clubeRepository.existsByNomeAndEstado(clubeDto.getNome(), Estados.valueOf(clubeDto.getEstados().toString())) &&
                !existingClube.getNome().equals(clubeDto.getNome())) {

            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um clube com esse nome no mesmo estado.");
        }

        ClubeDto updatedClube = clubeService.updateClub(uuid, clubeDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedClube);
    }


//    @PostMapping
//    public ResponseEntity<ClubeDto> salvaClube(@RequestBody ClubeDto clubeDto) {
//        return new ResponseEntity<>(clubeService.createClub(clubeDto), HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{uuid}")
//    public ResponseEntity atualizaClube(@PathVariable UUID uuid, @RequestBody ClubeDto clubeDto) {
//        return ResponseEntity.status(HttpStatus.OK).body(clubeService.updateClub(uuid, clubeDto));
//    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> inativaClube(@PathVariable UUID uuid) {
        if(!clubeRepository.existsById(uuid)) {
            throw new NotFoundException("Esse clube não existe!");
        }
            clubeService.inativarClube(uuid);
            return ResponseEntity.noContent().build();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Clube> getById(@PathVariable UUID uuid) {
        if(!clubeRepository.existsById(uuid)) {
            throw new NotFoundException("Esse clube não existe!");
        }
            Clube clube = clubeService.buscarClubePorId(uuid);
            return ResponseEntity.ok(clube);
    }

    //Esse metodo é apenas para paginação, deve juntar no outro metodo
//    @GetMapping("/page")
//    public ResponseEntity<List<Clube>> getAll(@RequestParam int page, @RequestParam int size) {
//        return ResponseEntity.ok(clubeService.findAll(page, size));
//    }

    @GetMapping("/filter/custom")
    public ResponseEntity<List<Clube>> findClubeByCustom(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "estados", required = false) Estados estados,
            @RequestParam(value = "ativo", required = false) StatusClube ativo,
            @RequestParam (value = "page", required = false) int page,
            @RequestParam (value = "size", required = false) int size)
    {
        return ResponseEntity.ok(clubeCustomRespository.findAll(nome, estados, ativo, page, size));
    }
}