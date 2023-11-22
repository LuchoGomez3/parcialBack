package parcialBackend.recuperatorio.services;

import parcialBackend.recuperatorio.entities.dtos.InvoiceDto;
import parcialBackend.recuperatorio.entities.dtos.InvoiceEnunciado;

public interface InvoiceService extends Service<InvoiceDto, Integer>{
    public void addNewInvoiceByAM(Integer idArtista, Integer idCliente, Double montoMax);
}
