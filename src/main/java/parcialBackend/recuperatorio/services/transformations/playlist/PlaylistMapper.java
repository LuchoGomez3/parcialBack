package parcialBackend.recuperatorio.services.transformations.playlist;

import org.springframework.stereotype.Service;
import parcialBackend.recuperatorio.entities.Playlist;
import parcialBackend.recuperatorio.entities.dtos.PlaylistDto;

import java.util.function.Function;

@Service
public class PlaylistMapper implements Function<PlaylistDto, Playlist> {
    @Override
    public Playlist apply(PlaylistDto playlistDto) {
        return new Playlist(
                playlistDto.getPlaylistId(),
                playlistDto.getName(),
                null
        );
    }
}
