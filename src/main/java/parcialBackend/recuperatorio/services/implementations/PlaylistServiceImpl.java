package parcialBackend.recuperatorio.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parcialBackend.recuperatorio.entities.Playlist;
import parcialBackend.recuperatorio.entities.dtos.PlaylistDto;
import parcialBackend.recuperatorio.repositories.PlaylistRepository;
import parcialBackend.recuperatorio.repositories.TrackRepository;
import parcialBackend.recuperatorio.services.InvoiceItemService;
import parcialBackend.recuperatorio.services.PlaylistService;
import parcialBackend.recuperatorio.services.transformations.playlist.PlaylistDtoMapper;
import parcialBackend.recuperatorio.services.transformations.playlist.PlaylistMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final PlaylistMapper playlistMapper;
    private final PlaylistDtoMapper playlistDtoMapper;

    private final TrackRepository trackRepository;

    private final InvoiceItemService invoiceItemService;

    /*private final PlaylistConsignaMapper playlistConsignaMapper;*/

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository playlistRepository,
                               PlaylistMapper playlistMapper,
                               PlaylistDtoMapper playlistDtoMapper,
                               TrackRepository trackRepository,
                               InvoiceItemService invoiceItemService
                               //,PlaylistConsignaMapper playlistConsignaMapper
                               ) {
        this.playlistRepository = playlistRepository;
        this.playlistMapper = playlistMapper;
        this.playlistDtoMapper = playlistDtoMapper;
        this.trackRepository = trackRepository;
        this.invoiceItemService = invoiceItemService;
        //this.playlistConsignaMapper = playlistConsignaMapper;
    }

    @Override
    public void add(PlaylistDto entity) {
        Playlist playlist = Optional.of(entity).map(playlistMapper).get();
        playlistRepository.save(playlist);

    }

    @Override
    public PlaylistDto getById(Integer id) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        return optionalPlaylist.map(playlistDtoMapper).orElseThrow();
    }

    @Override
    public List<PlaylistDto> getAll() {
        List<Playlist> playlists = playlistRepository.findAll();
        return playlists
                .stream()
                .map(playlistDtoMapper)
                .toList();
    }

    @Override
    public PlaylistDto delete(Integer id) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        optionalPlaylist.ifPresent(playlistRepository::delete);

        return optionalPlaylist
                .map(playlistDtoMapper)
                .orElseThrow();
    }

    @Override
    public void update(PlaylistDto entity) {
        Playlist playlist = Optional.of(entity).map(playlistMapper).orElseThrow();
        playlistRepository.save(playlist);
    }

    /*@Override
    public void addPlayListConsigna(String nombrePlaylist,
                                    Integer customerId,
                                    Integer genreId,
                                    String composerFilter) {
        Playlist playlist = new Playlist();
        playlist.setName(nombrePlaylist);

        List<InvoiceItem> invoiceItems = invoiceItemService.getByCustomer(customerId);
        List<Track> trackArrayList = new ArrayList<>();

        for (InvoiceItem invoiceItem : invoiceItems) {
            Track track = invoiceItem.getTrack();
            if (track != null) {
                trackArrayList.add(track);
            }
        }

        List<Track> tracksGenre = trackRepository.findByGenre_GenreId(genreId);
        trackArrayList.addAll(tracksGenre);

        playlist.setTracks(trackArrayList);

        playlistRepository.save(playlist);

    }*/
}
