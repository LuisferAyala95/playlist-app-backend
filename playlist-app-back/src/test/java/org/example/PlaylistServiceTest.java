package org.example;

import org.example.model.Playlist;
import org.example.repository.PlaylistRepository;
import org.example.service.PlaylistService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Service
public class PlaylistServiceTest {
    @Mock
    private PlaylistRepository playlistRepository;

    @InjectMocks
    private PlaylistService playlistService;

    public PlaylistServiceTest() {
        // Inicializa los mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cuandoExistePlaylistPorNombre_devuelvePlaylist() {
        Playlist playlist = new Playlist();
        playlist.setNombre("Rock");

        // Simulamos lo que devuelve el repositorio
        when(playlistRepository.findByNombre("Rock"))
                .thenReturn(Optional.of(playlist));

        Optional<Playlist> resultado = playlistService.getByName("Rock");

        // Verificamos el resultado
        assertTrue(resultado.isPresent());
        assertEquals("Rock", resultado.get().getNombre());
    }

    @Test
    void cuandoNoExistePlaylistPorNombre_devuelveVacio() {
        when(playlistRepository.findByNombre("Pop"))
                .thenReturn(Optional.empty());

        Optional<Playlist> resultado = playlistService.getByName("Pop");

        assertFalse(resultado.isPresent());
    }
}
