package parcialBackend.recuperatorio.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceEnunciado {
    private int invoiceId;
    private int customerId;
    private LocalDateTime invoiceDate;
    private double total;
}
