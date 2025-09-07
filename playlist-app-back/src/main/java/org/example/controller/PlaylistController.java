package org.example.controller;

import org.example.model.Playlist;
import org.example.service.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/lists")
public class PlaylistController {
    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity<?> addPlaylist(@RequestBody Playlist playlist) {
        if (playlist.getNombre() ==  null || playlist.getNombre().isEmpty()) {
           ResponseEntity.badRequest().body("El nombre de la lista no puede ser nulo");
        }
        Playlist newPlaylist = playlistService.addPlaylist(playlist);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPlaylist);
    }

    @GetMapping
    public List<Playlist> getPlaylist() {
        return this.playlistService.getAllPlaylist();
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<Playlist> getPlaylistByName(@PathVariable String nombre) {
        Playlist playlist = playlistService.getByName(nombre)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No se encontró la playlist con nombre: " + nombre
                ));
        return ResponseEntity.ok(playlist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        boolean deleted = playlistService.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No existe la playlist con Id: " + id);
    }


}
