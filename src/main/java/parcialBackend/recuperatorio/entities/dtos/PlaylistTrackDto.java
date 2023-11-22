package parcialBackend.recuperatorio.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistTrackDto {
    private int playlistId;
    private int trackId;
}