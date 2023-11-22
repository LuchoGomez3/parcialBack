package parcialBackend.recuperatorio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import parcialBackend.recuperatorio.entities.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
