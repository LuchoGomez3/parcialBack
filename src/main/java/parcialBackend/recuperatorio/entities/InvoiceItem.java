package parcialBackend.recuperatorio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoice_items")
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableGenerator(name = "invoice_items", table = "sqlite_sequence",
            pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "invoice_line_id")
    @Column(name = "InvoiceLineId")
    private int invoiceLineId;

    @ManyToOne
    @JoinColumn(name = "InvoiceId")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "TrackId")
    private Track track;

    @Column(name = "UnitPrice")
    private double unitPrice;

    @Column(name = "Quantity")
    private int quantity;
}
