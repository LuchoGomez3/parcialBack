package parcialBackend.recuperatorio.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parcialBackend.recuperatorio.entities.Track;
import parcialBackend.recuperatorio.entities.dtos.TrackDto;
import parcialBackend.recuperatorio.repositories.TrackRepository;
import parcialBackend.recuperatorio.services.InvoiceItemService;
import parcialBackend.recuperatorio.services.TrackService;
import parcialBackend.recuperatorio.services.transformations.track.TrackDtoMapper;
import parcialBackend.recuperatorio.services.transformations.track.TrackMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class TrackServiceImpl implements TrackService {
    private final TrackRepository trackRepository;
    private final TrackMapper trackMapper;
    private final TrackDtoMapper trackDtoMapper;
    private final InvoiceItemService invoiceItemService;
    //private final TrackCustomerGenreMapper trackCustomerGenreMapper;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository,
                            TrackMapper trackMapper,
                            TrackDtoMapper trackDtoMapper,
                            InvoiceItemService invoiceItemService
                            //,TrackCustomerGenreMapper trackCustomerGenreMapper
                            ) {
        this.trackRepository = trackRepository;
        this.trackMapper = trackMapper;
        this.trackDtoMapper = trackDtoMapper;
        this.invoiceItemService = invoiceItemService;
        //this.trackCustomerGenreMapper = trackCustomerGenreMapper;
    }

    @Override
    public void add(TrackDto entity) {
        Track track = Optional.of(entity).map(trackMapper).get();
        trackRepository.save(track);
    }

    @Override
    public TrackDto getById(Integer id) {
        Optional<Track> optionalTrack = trackRepository.findById(id);
        return optionalTrack
                .map(trackDtoMapper)
                .orElseThrow();
    }

    @Override
    public List<TrackDto> getAll() {
        List<Track> tracks = trackRepository.findAll();
        return tracks
                .stream()
                .map(trackDtoMapper)
                .toList();
    }

    @Override
    public TrackDto delete(Integer id) {
        Optional<Track> optionalTrack = trackRepository.findById(id);
        optionalTrack.ifPresent(trackRepository::delete);

        return optionalTrack
                .map(trackDtoMapper)
                .orElseThrow();
    }

    @Override
    public void update(TrackDto entity) {
        Optional<Track> track = Stream.of(entity).map(trackMapper).findFirst();
        track.ifPresent(trackRepository::save);
    }

    /*@Override
    public List<TrackByCustomerGenre> getAllByCustomerGender(Integer customerId, Integer genreId) {
        List<InvoiceItem> invoiceItems = invoiceItemService.getByCustomer(customerId);
        List<Track> tracks = new ArrayList<>();

        for (InvoiceItem invoiceItem : invoiceItems) {
            Track track = invoiceItem.getTrack();
            if (track != null) {
                tracks.add(track);
            }
        }

        List<Track> tracksGenre = trackRepository.findByGenre_GenreId(genreId);
        return tracksGenre
                .stream()
                .map(trackCustomerGenreMapper)
                .toList();
    }*/
}
