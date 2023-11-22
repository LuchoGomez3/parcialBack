package parcialBackend.recuperatorio.services.transformations.playlistTrack;

import org.springframework.stereotype.Service;
import parcialBackend.recuperatorio.entities.PlaylistTrack;
import parcialBackend.recuperatorio.entities.dtos.PlaylistTrackDto;

import java.util.function.Function;

@Service
public class PlaylistTrackMapper implements Function<PlaylistTrackDto, PlaylistTrack> {
    @Override
    public PlaylistTrack apply(PlaylistTrackDto playlistTrackDto) {
        return new PlaylistTrack(
                playlistTrackDto.getPlaylistId(),
                playlistTrackDto.getTrackId()
        );
    }
}
