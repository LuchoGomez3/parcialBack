package parcialBackend.recuperatorio.services.implementations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parcialBackend.recuperatorio.entities.PlaylistTrack;
import parcialBackend.recuperatorio.entities.dtos.PlaylistTrackDto;
import parcialBackend.recuperatorio.repositories.PlaylistTrackRepository;
import parcialBackend.recuperatorio.services.PlaylistTrackService;
import parcialBackend.recuperatorio.services.transformations.playlistTrack.PlaylistTrackDtoMapper;
import parcialBackend.recuperatorio.services.transformations.playlistTrack.PlaylistTrackMapper;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistTrackServiceImpl implements PlaylistTrackService {
    private final PlaylistTrackRepository playlistTrackRepository;
    private final PlaylistTrackMapper playlistTrackMapper;
    private final PlaylistTrackDtoMapper playlistTrackDtoMapper;

    @Autowired
    public PlaylistTrackServiceImpl(PlaylistTrackRepository playlistTrackRepository,
                                    PlaylistTrackMapper playlistTrackMapper,
                                    PlaylistTrackDtoMapper playlistTrackDtoMapper) {
        this.playlistTrackRepository = playlistTrackRepository;
        this.playlistTrackMapper = playlistTrackMapper;
        this.playlistTrackDtoMapper = playlistTrackDtoMapper;
    }

    @Override
    public void add(PlaylistTrackDto entity) {
        PlaylistTrack playlistTrack = Optional.of(entity).map(playlistTrackMapper).get();
        playlistTrackRepository.save(playlistTrack);
    }

    @Override
    public PlaylistTrackDto getById(Integer id) {
        Optional<PlaylistTrack> optionalPlaylistTrack = playlistTrackRepository.findById(id);
        return optionalPlaylistTrack.map(playlistTrackDtoMapper).orElseThrow();
    }

    @Override
    public List<PlaylistTrackDto> getAll() {
        List<PlaylistTrack> playlistTracks = playlistTrackRepository.findAll();
        return playlistTracks
                .stream()
                .map(playlistTrackDtoMapper)
                .toList();
    }

    @Override
    public PlaylistTrackDto delete(Integer id) {
        Optional<PlaylistTrack> optionalPlaylistTrack = playlistTrackRepository.findById(id);
        optionalPlaylistTrack.ifPresent(playlistTrackRepository::delete);
        return optionalPlaylistTrack
                .map(playlistTrackDtoMapper)
                .orElseThrow();
    }

    @Override
    public void update(PlaylistTrackDto entity) {
        PlaylistTrack playlistTrack = Optional.of(entity).map(playlistTrackMapper).orElseThrow();
        playlistTrackRepository.save(playlistTrack);
    }
}
