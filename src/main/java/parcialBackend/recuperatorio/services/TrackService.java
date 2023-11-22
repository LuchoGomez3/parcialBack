package parcialBackend.recuperatorio.services;

import parcialBackend.recuperatorio.entities.dtos.TrackDto;
import parcialBackend.recuperatorio.entities.dtos.TrackEnunciado;

import java.util.List;

public interface TrackService extends Service<TrackDto, Integer>{
    public List<TrackEnunciado> getListaTracksByArtist(Integer id);

}
