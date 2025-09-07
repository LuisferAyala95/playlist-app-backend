package org.example.service;

import org.example.model.Playlist;
import org.example.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public Playlist addPlaylist(Playlist playlist) {
       return this.playlistRepository.save(playlist);
    }

    public List<Playlist> getAllPlaylist() {
        return this.playlistRepository.findAll();
    }

    public Optional<Playlist> getByName(String name) {
        return this.playlistRepository.findByNombre(name);
    }

    public boolean deleteById(Long id) {
        if (this.playlistRepository.existsById(id)) {
            this.playlistRepository.deleteById((id));
            return true;
        }
        return false;
    }
}
