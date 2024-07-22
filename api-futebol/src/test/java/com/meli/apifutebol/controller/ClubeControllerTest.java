//package com.meli.apifutebol.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.meli.apifutebol.dto.ClubeDto;
//import com.meli.apifutebol.enums.Estados;
//import com.meli.apifutebol.enums.StatusClube;
//import com.meli.apifutebol.model.Clube;
//import com.meli.apifutebol.service.ClubeService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.time.LocalDate;
//import java.util.*;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class ClubeControllerTest {
//
//    @Mock
//    private ClubeService clubeService;
//
//    @InjectMocks
//    private ClubeController clubeController;
//
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    void setUp() {
//        objectMapper = new ObjectMapper();
//    }
//
//    @Test
//    void testSalvaClube() {
//        // Given
//        ClubeDto clubeDto = new ClubeDto();
//        clubeDto.setNome("Clube Teste");
//        clubeDto.setEstados(Estados.SP);
//        clubeDto.setDataCriacao(LocalDate.now());
//        clubeDto.setAtivo(StatusClube.ATIVO);
//
//        Clube clubeSalvo = new Clube();
//        clubeSalvo.setUuid(UUID.randomUUID());
//        clubeSalvo.setNome(clubeDto.getNome());
//        clubeSalvo.setEstados(clubeDto.getEstados());
//        clubeSalvo.setDataCriacao(clubeDto.getDataCriacao());
//        clubeSalvo.setAtivo(clubeDto.getAtivo());
//
//        when(clubeService.createClube(any(Clube.class))).thenReturn(clubeSalvo);
//
//        // When
////        ResponseEntity<Clube> responseEntity = clubeController.salvaClube(clubeDto);
//
//        // Then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        assertThat(responseEntity.getBody()).isEqualTo(clubeSalvo);
//        verify(clubeService, times(1)).createClube(any(Clube.class));
//    }
//
//    @Test
//    void testAtualizaClube() {
//        // Given
//        UUID uuid = UUID.randomUUID();
//        ClubeDto clubeDto = new ClubeDto();
//        clubeDto.setNome("Clube Atualizado");
//        clubeDto.setEstados(Estados.RJ);
//        clubeDto.setDataCriacao(LocalDate.now());
//        clubeDto.setAtivo(StatusClube.ATIVO);
//
//        Clube clubeAtualizado = new Clube();
//        clubeAtualizado.setUuid(uuid);
//        clubeAtualizado.setNome(clubeDto.getNome());
//        clubeAtualizado.setEstados(clubeDto.getEstados());
//        clubeAtualizado.setDataCriacao(clubeDto.getDataCriacao());
//        clubeAtualizado.setAtivo(clubeDto.getAtivo());
//
//        when(clubeService.atualizarClube(eq(uuid), any(ClubeDto.class))).thenReturn(clubeAtualizado);
//
//        // When
//        ResponseEntity responseEntity = clubeController.atualizaClube(uuid, clubeDto);
//
//        // Then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isEqualTo(clubeAtualizado);
//        verify(clubeService, times(1)).atualizarClube(eq(uuid), any(ClubeDto.class));
//    }
//
//    @Test
//    void testInativaClube_Success() {
//        // Given
//        UUID uuid = UUID.randomUUID();
//
//        // When
//        ResponseEntity<Void> responseEntity = clubeController.inativaClube(uuid);
//
//        // Then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
//        verify(clubeService, times(1)).inativarClube(eq(uuid));
//    }
//
//
//    @Test
//    void testInativaClube_ClubeNaoEncontrado() {
//        // Given
//        UUID uuid = UUID.randomUUID();
//        doThrow(new NoSuchElementException("Clube não encontrado")).when(clubeService).inativarClube(eq(uuid));
//
//        // When
//        ResponseEntity<Void> responseEntity = clubeController.inativaClube(uuid);
//
//        // Then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//        verify(clubeService, times(1)).inativarClube(eq(uuid));
//    }
//
//
//    @Test
//    void testGetById_Success() {
//        // Given
//        UUID uuid = UUID.randomUUID();
//        Clube clube = new Clube();
//        clube.setUuid(uuid);
//        clube.setNome("Clube Teste");
//        clube.setEstados(Estados.SP);
//        clube.setDataCriacao(LocalDate.now());
//        clube.setAtivo(StatusClube.ATIVO);
//
//        when(clubeService.buscarClubePorId(eq(uuid))).thenReturn(clube);
//
//        // When
//        ResponseEntity<Clube> responseEntity = clubeController.getById(uuid);
//
//        // Then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isEqualTo(clube);
//        verify(clubeService, times(1)).buscarClubePorId(eq(uuid));
//    }
//
//    @Test
//    void testGetById_ClubeNaoEncontrado() {
//        // Given
//        UUID uuid = UUID.randomUUID();
//        when(clubeService.buscarClubePorId(eq(uuid))).thenThrow(new NoSuchElementException("Clube não encontrado"));
//
//        // When
//        ResponseEntity<Clube> responseEntity = clubeController.getById(uuid);
//
//        // Then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//        verify(clubeService, times(1)).buscarClubePorId(eq(uuid));
//    }
//
////    @Test
////    void testGetAll() {
////        // Given
////        int page = 0;
////        int size = 10;
////        List<Clube> clubes = Arrays.asList(
////                new Clube(UUID.randomUUID(), "Clube 1", Estados.SP, LocalDate.now(), StatusClube.ATIVO),
////                new Clube(UUID.randomUUID(), "Clube 2", Estados.RJ, LocalDate.now(), StatusClube.ATIVO)
////        );
////
////        when(clubeService.findAll(eq(page), eq(size))).thenReturn(clubes);
////
////        // When
////        ResponseEntity<List<Clube>> responseEntity = clubeController.getAll(page, size);
////
////        // Then
////        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
////        assertThat(responseEntity.getBody()).isEqualTo(clubes);
////        verify(clubeService, times(1)).findAll(eq(page), eq(size));
////    }
//}
