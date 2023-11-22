package parcialBackend.recuperatorio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import parcialBackend.recuperatorio.entities.dtos.PlaylistDto;
import parcialBackend.recuperatorio.services.PlaylistService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {
    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping
    public ResponseEntity<List<PlaylistDto>> getAll(){
        List<PlaylistDto> playlists = playlistService.getAll();
        return ResponseEntity.ok(playlists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistDto> getById(@PathVariable("id") int id) {
        PlaylistDto playlist = playlistService.getById(id);
        return ResponseEntity.ok(playlist);
    }

    @PostMapping()
    public ResponseEntity<Void> add(@RequestBody PlaylistDto playlist) {
        try {
            playlistService.add(playlist);
            return  ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping()
    public ResponseEntity<Void> update(@RequestBody PlaylistDto playlist) {
        playlistService.update(playlist);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        playlistService.delete(id);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
}
