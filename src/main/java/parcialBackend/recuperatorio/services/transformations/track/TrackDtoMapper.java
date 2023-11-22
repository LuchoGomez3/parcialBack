package parcialBackend.recuperatorio.services.transformations.track;

import org.springframework.stereotype.Service;
import parcialBackend.recuperatorio.entities.Track;
import parcialBackend.recuperatorio.entities.dtos.TrackDto;

import java.util.function.Function;

@Service
public class TrackDtoMapper implements Function<Track, TrackDto> {
    @Override
    public TrackDto apply(Track track) {
        return new TrackDto(
                track.getTrackId(),
                track.getName(),
                track.getAlbum().getAlbumId(),
                track.getMediaType().getMediaTypeId(),
                track.getGenre().getGenreId(),
                track.getComposer(),
                track.getMillisecionds(),
                track.getBytes(),
                track.getUnitPrice()
        );
    }
}