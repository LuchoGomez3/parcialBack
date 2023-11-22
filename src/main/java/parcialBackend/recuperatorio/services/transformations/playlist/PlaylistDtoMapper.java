package parcialBackend.recuperatorio.services.transformations.playlist;

import org.springframework.stereotype.Service;
import parcialBackend.recuperatorio.entities.Playlist;
import parcialBackend.recuperatorio.entities.dtos.PlaylistDto;

import java.util.function.Function;

@Service
public class PlaylistDtoMapper implements Function<Playlist, PlaylistDto> {
    @Override
    public PlaylistDto apply(Playlist playlist) {
        return new PlaylistDto(
                playlist.getPlaylistId(),
                playlist.getName()
        );
    }
}