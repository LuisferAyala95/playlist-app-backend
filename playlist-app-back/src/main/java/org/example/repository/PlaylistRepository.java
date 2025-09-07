package org.example.repository;

import org.example.PlaylistApiApplication;
import org.example.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Optional<Playlist> findByNombre(String nombre);
    boolean existsByNombre(String nombre);

    void deleteByNombre(String nombre);
}
