package org.example;

import org.example.controller.PlaylistController;
import org.example.model.Playlist;
import org.example.service.PlaylistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;




import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PlaylistController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)

public class PlaylistControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlaylistService playlistService;

    @Test
    void testObtenerPorNombre_Existe() throws Exception {
        Playlist playlist = new Playlist();
        playlist.setNombre("Rock");

        when(playlistService.getByName("Rock"))
                .thenReturn(Optional.of(playlist));

        mockMvc.perform(get("/lists/Rock"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Rock"));
    }

    @Test
    void testObtenerPorNombre_NoExiste() throws Exception {
        when(playlistService.getByName("Pop"))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/lists/Pop"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCrearPlaylist() throws Exception {
        Playlist playlist = new Playlist();
        playlist.setNombre("Jazz");

        when(playlistService.addPlaylist(any(Playlist.class))).thenReturn(playlist);

        mockMvc.perform(post("/lists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Jazz\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Jazz"));
    }

    @Test
    void testEliminarPlaylist_Existente() throws Exception {
        Long idPlaylist = 1L;
        when(playlistService.deleteById(idPlaylist)).thenReturn(true);

        mockMvc.perform(delete("/lists/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testEliminarPlaylist_NoExistente() throws Exception {
        when(playlistService.deleteById(2L)).thenReturn(false);

        mockMvc.perform(delete("/lists/2"))
                .andExpect(status().isNotFound());
    }
}
