package parcialBackend.recuperatorio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import parcialBackend.recuperatorio.entities.Track;

public interface TrackRepository extends JpaRepository<Track, Integer> {
}
