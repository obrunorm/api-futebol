package com.meli.apifutebol.respository;

import com.meli.apifutebol.model.Clube;
import com.meli.apifutebol.repository.ClubeRepository;
import com.meli.apifutebol.service.ClubeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClubeRepositoryTest {

    @Mock
    private ClubeRepository clubeRepository;

    @InjectMocks
    private ClubeService clubeService;

    @Test
    void testFindByUuid() {
        // Given
        UUID uuid = UUID.randomUUID();
        Clube clube = new Clube();
        clube.setId(uuid);
        when(clubeRepository.findByUuid(uuid)).thenReturn(clube);

        // When
        Clube found = clubeRepository.findByUuid(uuid);

        // Then
        Assertions.assertNotNull(found);
        Assertions.assertEquals(uuid, found.getId());
    }

    @Test
    void testFindAll() {
        // Given
        List<Clube> clubes = new ArrayList<>();
        clubes.add(new Clube());
        clubes.add(new Clube());
        Page<Clube> page = new PageImpl<>(clubes);
        Pageable pageable = Pageable.unpaged();
        when(clubeRepository.findAll(pageable)).thenReturn(page);

        // When
        Page<Clube> foundPage = clubeRepository.findAll(pageable);

        // Then
        Assertions.assertEquals(clubes.size(), foundPage.getContent().size());
        verify(clubeRepository, times(1)).findAll(pageable);
    }

    @Test
    void testExistsByNome() {
        // Given
        String nome = "Clube Teste";
        when(clubeRepository.existsByNome(nome)).thenReturn(true);

        // When
        boolean exists = clubeRepository.existsByNome(nome);

        // Then
        Assertions.assertTrue(exists);
        verify(clubeRepository, times(1)).existsByNome(nome);
    }
}
