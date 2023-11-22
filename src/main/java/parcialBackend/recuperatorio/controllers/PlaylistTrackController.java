package parcialBackend.recuperatorio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import parcialBackend.recuperatorio.entities.dtos.PlaylistTrackDto;
import parcialBackend.recuperatorio.services.PlaylistTrackService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/playlist-track")
public class PlaylistTrackController {
    private final PlaylistTrackService playlistTrackService;

    @Autowired
    public PlaylistTrackController(PlaylistTrackService playlistTrackService) {
        this.playlistTrackService = playlistTrackService;
    }

    @GetMapping
    public ResponseEntity<List<PlaylistTrackDto>> getAll(){
        List<PlaylistTrackDto> playlistTracks = playlistTrackService.getAll();
        return ResponseEntity.ok(playlistTracks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistTrackDto> getById(@PathVariable("id") int id) {
        PlaylistTrackDto playlistTrack = playlistTrackService.getById(id);
        return ResponseEntity.ok(playlistTrack);
    }

    @PostMapping()
    public ResponseEntity<Void> add(@RequestBody PlaylistTrackDto playlistTrack) {
        try {
            playlistTrackService.add(playlistTrack);
            return  ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping()
    public ResponseEntity<Void> update(@RequestBody PlaylistTrackDto playlistTrack) {
        playlistTrackService.update(playlistTrack);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        playlistTrackService.delete(id);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
}