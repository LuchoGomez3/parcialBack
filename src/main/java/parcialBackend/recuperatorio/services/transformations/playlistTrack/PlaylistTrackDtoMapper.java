package parcialBackend.recuperatorio.services.transformations.playlistTrack;

import org.springframework.stereotype.Service;
import parcialBackend.recuperatorio.entities.PlaylistTrack;
import parcialBackend.recuperatorio.entities.dtos.PlaylistTrackDto;

import java.util.function.Function;

@Service
public class PlaylistTrackDtoMapper implements Function<PlaylistTrack, PlaylistTrackDto> {
    @Override
    public PlaylistTrackDto apply(PlaylistTrack playlistTrack) {
        return new PlaylistTrackDto(
                playlistTrack.getPlaylistId(),
                playlistTrack.getTrackId()
        );
    }
}