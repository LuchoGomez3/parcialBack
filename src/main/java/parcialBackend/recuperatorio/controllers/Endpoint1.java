package parcialBackend.recuperatorio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import parcialBackend.recuperatorio.entities.dtos.TrackEnunciado;
import parcialBackend.recuperatorio.services.TrackService;

import java.util.List;

@RestController
@RequestMapping("/endpoint1/tracks")
public class Endpoint1 {
    private final TrackService trackService;
@Autowired
    public Endpoint1(TrackService trackService) {
        this.trackService = trackService;
    }


    @GetMapping
    public ResponseEntity<List<TrackEnunciado>> getNuevosTracks(@RequestParam("artistId") Integer artistId){
        List<TrackEnunciado> nuevosTraks = trackService.getListaTracksByArtist(artistId);
        return ResponseEntity.ok(nuevosTraks);
    }
}
