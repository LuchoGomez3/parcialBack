package parcialBackend.recuperatorio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import parcialBackend.recuperatorio.entities.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Integer>{
}
