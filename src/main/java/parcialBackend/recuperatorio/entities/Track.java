package parcialBackend.recuperatorio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tracks")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "tracks", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "track_id")
    @Column(name = "TrackId")
    private int trackId;

    @Column(name = "Name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "AlbumId")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "MediaTypeId")
    private MediaType mediaType;

    @ManyToOne
    @JoinColumn(name = "GenreId")
    private Genre genre;

    @Column(name = "Composer")
    private String composer;

    @Column(name = "Milliseconds")
    private int millisecionds;

    @Column(name = "Bytes")
    private int bytes;

    @Column(name = "UnitPrice")
    private double UnitPrice;

    @OneToMany(mappedBy = "track")
    private List<InvoiceItem> invoiceItems;

    @ManyToMany(mappedBy = "tracks")
    private List<Playlist> playlists;
}
