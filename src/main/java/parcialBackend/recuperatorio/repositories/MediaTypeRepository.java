package parcialBackend.recuperatorio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import parcialBackend.recuperatorio.entities.MediaType;

public interface MediaTypeRepository extends JpaRepository<MediaType, Integer> {
}
