//package com.meli.apifutebol.service;
//
//import com.meli.apifutebol.dto.ClubeDto;
//import com.meli.apifutebol.enums.Estados;
//import com.meli.apifutebol.enums.StatusClube;
//import com.meli.apifutebol.exceptions.DuplicateNameException;
//import com.meli.apifutebol.exceptions.InvalidClubDataException;
//import com.meli.apifutebol.model.Clube;
//import com.meli.apifutebol.repository.ClubeRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.BeanUtils;
//
//import java.time.LocalDate;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class ClubeServiceTest {
//
//    @Mock
//    private ClubeRepository clubeRepository;
//
//    @InjectMocks
//    private ClubeService clubeService;
//
//    private Clube clubeMock;
//
//    @BeforeEach
//    void setUp() {
//        clubeMock = new Clube();
//        clubeMock.setId(UUID.randomUUID());
//        clubeMock.setNome("Clube Teste");
//        clubeMock.setEstados(Estados.SP);
//        clubeMock.setDataCriacao(LocalDate.now());
//        clubeMock.setAtivo(StatusClube.ATIVO);
//    }
//
//    @Test
//    void testCreateClube_Success() {
//        // Given
//        when(clubeRepository.existsByNome(anyString())).thenReturn(false);
//        when(clubeRepository.save(any(Clube.class))).thenReturn(clubeMock);
//
//        // When
//        Clube savedClube = clubeService.createClube(clubeMock);
//
//        // Then
//        assertEquals(clubeMock, savedClube);
//        verify(clubeRepository, times(1)).save(any(Clube.class));
//    }
//
//    @Test
//    void testCreateClube_DuplicateNameException() {
//        // Given
//        when(clubeRepository.existsByNome(anyString())).thenReturn(true);
//
//        // When / Then
//        assertThrows(DuplicateNameException.class, () -> clubeService.createClube(clubeMock));
//        verify(clubeRepository, never()).save(any(Clube.class));
//    }
//
//    @Test
//    void testCreateClube_InvalidClubDataException() {
//        // Given
//        clubeMock.setNome("A"); // Set invalid club name
//
//        // When / Then
//        assertThrows(InvalidClubDataException.class, () -> clubeService.createClube(clubeMock));
//        verify(clubeRepository, never()).save(any(Clube.class));
//    }
//
//    @Test
//    void testAtualizarClube_Success() {
//        // Given
//        UUID uuid = clubeMock.getId();
//        ClubeDto clubeDto = new ClubeDto();
//        clubeDto.setNome("Clube Atualizado");
//        clubeDto.setEstados(Estados.RJ);
//        clubeDto.setDataCriacao(LocalDate.now());
//        clubeDto.setAtivo(StatusClube.ATIVO);
//
//        when(clubeRepository.existsByNome(anyString())).thenReturn(false);
//        when(clubeRepository.findByUuid(eq(uuid))).thenReturn(clubeMock);
//        when(clubeRepository.save(any(Clube.class))).thenReturn(clubeMock);
//
//        // When
//        Clube updatedClube = clubeService.atualizarClube(uuid, clubeDto);
//
//        // Then
//        assertEquals(clubeDto.getNome(), updatedClube.getNome());
//        assertEquals(clubeDto.getEstados(), updatedClube.getEstados());
//        assertEquals(clubeDto.getDataCriacao(), updatedClube.getDataCriacao());
//        assertEquals(clubeDto.getAtivo(), updatedClube.getAtivo());
//        verify(clubeRepository, times(1)).save(any(Clube.class));
//    }
//
//    @Test
//    void testAtualizarClube_DuplicateNameException() {
//        // Given
//        UUID uuid = clubeMock.getId();
//        ClubeDto clubeDto = new ClubeDto();
//        clubeDto.setNome("Clube Atualizado");
//
//        when(clubeRepository.existsByNome(anyString())).thenReturn(true);
//
//        // When / Then
//        assertThrows(DuplicateNameException.class, () -> clubeService.atualizarClube(uuid, clubeDto));
//        verify(clubeRepository, never()).save(any(Clube.class));
//    }
//
//    @Test
//    void testAtualizarClube_InvalidClubDataException() {
//        // Given
//        UUID uuid = clubeMock.getId();
//        ClubeDto clubeDto = new ClubeDto();
//        clubeDto.setNome("A");
//
//        // When / Then
//        assertThrows(InvalidClubDataException.class, () -> clubeService.atualizarClube(uuid, clubeDto));
//        verify(clubeRepository, never()).save(any(Clube.class));
//    }
//
//    @Test
//    void testInativarClube_Success() {
//        // Given
//        UUID uuid = clubeMock.getId();
//        when(clubeRepository.findByUuid(eq(uuid))).thenReturn(clubeMock);
//
//        // When
//        clubeService.inativarClube(uuid);
//
//        // Then
//        assertEquals(StatusClube.INATIVO, clubeMock.getAtivo());
//        verify(clubeRepository, times(1)).save(any(Clube.class));
//    }
//
//    @Test
//    void testInativarClube_ClubeNaoEncontrado() {
//        // Given
//        UUID uuid = UUID.randomUUID();
//        when(clubeRepository.findByUuid(eq(uuid))).thenReturn(null);
//
//        // When / Then
//        assertThrows(NoSuchElementException.class, () -> clubeService.inativarClube(uuid));
//        verify(clubeRepository, never()).save(any(Clube.class));
//    }
//
//    @Test
//    void testBuscarClubePorId_Success() {
//        // Given
//        UUID uuid = clubeMock.getId();
//        when(clubeRepository.findByUuid(eq(uuid))).thenReturn(clubeMock);
//
//        // When
//        Clube foundClube = clubeService.buscarClubePorId(uuid);
//
//        // Then
//        assertEquals(clubeMock, foundClube);
//    }
//
//    @Test
//    void testBuscarClubePorId_ClubeNaoEncontrado() {
//        // Given
//        UUID uuid = UUID.randomUUID();
//        when(clubeRepository.findByUuid(eq(uuid))).thenReturn(null);
//
//        // When / Then
//        assertThrows(NoSuchElementException.class, () -> clubeService.buscarClubePorId(uuid));
//    }
//}
//
