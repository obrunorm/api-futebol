package com.meli.apifutebol.controller;

import com.meli.apifutebol.dto.ClubeDto;
import com.meli.apifutebol.enums.Estados;
import com.meli.apifutebol.enums.StatusClube;
import com.meli.apifutebol.model.Clube;
import com.meli.apifutebol.repository.ClubeCustomRepository;
import com.meli.apifutebol.repository.ClubeRepository;
import com.meli.apifutebol.service.ClubeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("clube")
public class ClubeController {

    @Autowired
    private ClubeRepository clubeRespository;

    @Autowired
    private ClubeService clubeService;

    @Autowired
    private ClubeCustomRepository clubeCustomRespository;


    @PostMapping
    public ResponseEntity<Clube> salvaClube(@RequestBody ClubeDto clubeDto) {
        Clube clube = new Clube();
        BeanUtils.copyProperties(clubeDto, clube);
        return ResponseEntity.status(HttpStatus.CREATED).body(clubeService.createClube(clube));
    }


    @PutMapping("/{uuid}")
    public ResponseEntity atualizaClube(@PathVariable UUID uuid, @RequestBody ClubeDto clubeDto) {
        return ResponseEntity.status(HttpStatus.OK).body(clubeService.atualizarClube(uuid, clubeDto));
    }

    @DeleteMapping("/{uuid}")
    public void inativaClube(@PathVariable UUID uuid) {
        Clube clube = clubeRespository.findByUuid(uuid);
        clubeRespository.delete(clube);
    }

    @GetMapping("/{uuid}")
    public Clube getById(@PathVariable UUID uuid) {
        Clube clube = clubeRespository.findByUuid(uuid);
        return clube;
    }

    //Esse metodo é apenas para paginação, deve juntar no outro metodo
    @GetMapping("/page")
    public ResponseEntity<List<Clube>> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(clubeService.findAll(page, size));
    }

    @GetMapping("/filter/custom")
    public ResponseEntity<List<Clube>> findClubeByCustom(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "siglaEstado", required = false) Estados estados,
            @RequestParam(value = "ativo", required = false) StatusClube ativo
    ){
        return ResponseEntity.ok(clubeCustomRespository.findAll(nome, estados, ativo));
    }
}
