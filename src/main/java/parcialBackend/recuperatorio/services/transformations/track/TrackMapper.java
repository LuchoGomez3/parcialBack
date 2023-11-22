package parcialBackend.recuperatorio.services.transformations.track;

import org.springframework.stereotype.Service;
import parcialBackend.recuperatorio.entities.Track;
import parcialBackend.recuperatorio.entities.dtos.TrackDto;
import parcialBackend.recuperatorio.repositories.AlbumRepository;
import parcialBackend.recuperatorio.repositories.GenreRepository;
import parcialBackend.recuperatorio.repositories.MediaTypeRepository;

import java.util.function.Function;

@Service
public class TrackMapper implements Function<TrackDto, Track> {
    private final AlbumRepository albumRepository;
    private final GenreRepository genreRepository;
    private final MediaTypeRepository mediaTypeRepository;

    public TrackMapper(AlbumRepository albumRepository,
                       GenreRepository genreRepository,
                       MediaTypeRepository mediaTypeRepository) {
        this.albumRepository = albumRepository;
        this.genreRepository = genreRepository;
        this.mediaTypeRepository = mediaTypeRepository;
    }

    @Override
    public Track apply(TrackDto trackDto) {
        return new Track(
                trackDto.getTrackId(),
                trackDto.getName(),
                albumRepository.getReferenceById(trackDto.getAlbumId()),
                mediaTypeRepository.getReferenceById(trackDto.getMediaTypeId()),
                genreRepository.getReferenceById(trackDto.getGenreId()),
                trackDto.getComposer(),
                trackDto.getMilliseconds(),
                trackDto.getBytes(),
                trackDto.getUnitPrice(),
                null,
                null
        );
    }
}