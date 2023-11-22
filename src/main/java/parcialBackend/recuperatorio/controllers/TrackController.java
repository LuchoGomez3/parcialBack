package parcialBackend.recuperatorio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import parcialBackend.recuperatorio.entities.dtos.TrackDto;
import parcialBackend.recuperatorio.repositories.CustomerRepository;
import parcialBackend.recuperatorio.services.TrackService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/tracks")
public class TrackController {
    private final TrackService trackService;
    private final CustomerRepository customerRepository;

    @Autowired
    public TrackController(TrackService trackService, CustomerRepository customerRepository) {
        this.trackService = trackService;
        this.customerRepository = customerRepository;
    }
    @GetMapping
    public ResponseEntity<List<TrackDto>> getAll(){
        List<TrackDto> tracks = trackService.getAll();
        return ResponseEntity.ok(tracks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackDto> getById(@PathVariable("id") int id) {
        TrackDto track = trackService.getById(id);
        return ResponseEntity.ok(track);
    }

    @PostMapping()
    public ResponseEntity<Void> add(@RequestBody TrackDto track) {
        try {
            trackService.add(track);
            return  ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping()
    public ResponseEntity<Void> update(@RequestBody TrackDto track) {
        trackService.update(track);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        trackService.delete(id);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    /*
    @GetMapping("/consigna")
    public ResponseEntity<List<TrackByCustomerGenre>> getAllByCustomer(@RequestParam("customerId") int customerId,
                                                                       @RequestParam("genreId") int genreId) {

        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer.isPresent()) {
            List<TrackByCustomerGenre> trackByCustomerGenres = trackService.getAllByCustomerGender(customerId, genreId);
            if (trackByCustomerGenres.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(trackByCustomerGenres);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
}
