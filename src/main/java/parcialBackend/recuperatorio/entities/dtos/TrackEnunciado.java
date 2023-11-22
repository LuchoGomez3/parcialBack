package parcialBackend.recuperatorio.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackEnunciado {
    private int trackId;
    private String trackName;
    private String albumName;
    private int segundos;
    private double unitPrice;
}
