package parcialBackend.recuperatorio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import parcialBackend.recuperatorio.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
