package parcialBackend.recuperatorio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import parcialBackend.recuperatorio.entities.PlaylistTrack;

public interface PlaylistTrackRepository extends JpaRepository<PlaylistTrack, Integer> {
}
