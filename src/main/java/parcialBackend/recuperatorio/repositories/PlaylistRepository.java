package parcialBackend.recuperatorio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import parcialBackend.recuperatorio.entities.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
}
