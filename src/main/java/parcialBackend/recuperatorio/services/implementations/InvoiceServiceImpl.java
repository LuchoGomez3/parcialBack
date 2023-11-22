package parcialBackend.recuperatorio.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parcialBackend.recuperatorio.entities.Invoice;
import parcialBackend.recuperatorio.entities.dtos.InvoiceDto;
import parcialBackend.recuperatorio.entities.dtos.InvoiceEnunciado;
import parcialBackend.recuperatorio.entities.dtos.InvoiceItemDto;
import parcialBackend.recuperatorio.entities.dtos.TrackEnunciado;
import parcialBackend.recuperatorio.repositories.InvoiceItemRepository;
import parcialBackend.recuperatorio.repositories.InvoiceRepository;
import parcialBackend.recuperatorio.services.InvoiceItemService;
import parcialBackend.recuperatorio.services.InvoiceService;
import parcialBackend.recuperatorio.services.TrackService;
import parcialBackend.recuperatorio.services.transformations.invoice.InvoiceDtoMapper;
import parcialBackend.recuperatorio.services.transformations.invoice.InvoiceMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceDtoMapper invoiceDtoMapper;
    private final InvoiceMapper invoiceMapper;
    private final TrackService trackService;

    private final InvoiceItemService invoiceItemService;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository,
                              InvoiceDtoMapper invoiceDtoMapper,
                              InvoiceMapper invoiceMapper, TrackService trackService, InvoiceItemRepository invoiceItemRepository, InvoiceItemService invoiceItemService) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceDtoMapper = invoiceDtoMapper;
        this.invoiceMapper = invoiceMapper;
        this.trackService = trackService;
        this.invoiceItemService = invoiceItemService;
    }

    @Override
    public void add(InvoiceDto entity) {
        Invoice invoice = Optional.of(entity).map(invoiceMapper).orElseThrow();
        invoiceRepository.save(invoice);

    }

    @Override
    public InvoiceDto getById(Integer id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        return  optionalInvoice
                .map(invoiceDtoMapper)
                .orElseThrow();
    }

    @Override
    public List<InvoiceDto> getAll() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return  invoices
                .stream()
                .map(invoiceDtoMapper)
                .toList();
    }

    @Override
    public InvoiceDto delete(Integer id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        optionalInvoice.ifPresent(invoiceRepository::delete);

        return optionalInvoice
                .map(invoiceDtoMapper)
                .orElseThrow();
    }

    @Override
    public void update(InvoiceDto entity) {
        Optional<Invoice> optionalInvoice = Stream.of(entity).map(invoiceMapper).findFirst();
        optionalInvoice.ifPresent(invoiceRepository::save);
    }

    @Override
    public void addNewInvoiceByAM(Integer idArtista, Integer idCliente, Double montoMax){
        Double monto = 0.0;
        List<TrackEnunciado> tracksDelArtista = trackService.getListaTracksByArtist(idArtista);
        List<TrackEnunciado> tracksComprados = new ArrayList<>();
        for (TrackEnunciado track : tracksDelArtista) {
            while (monto < montoMax){
                monto += track.getUnitPrice();
                if (monto > montoMax) {
                    monto -= track.getUnitPrice();
                    break;
                }
                tracksComprados.add(track);
            }
        }
        InvoiceEnunciado newInvoice = new InvoiceEnunciado();
        newInvoice.setCustomerId(idCliente);
        newInvoice.setInvoiceDate(LocalDateTime.now());
        newInvoice.setTotal(0);

        for (TrackEnunciado track :tracksComprados){
            InvoiceItemDto invoiceItem = new InvoiceItemDto();
            invoiceItem.setInvoiceId(newInvoice.getInvoiceId());
            invoiceItem.setTrackId(track.getTrackId());
            invoiceItem.setUnitPrice(track.getUnitPrice());
            invoiceItem.setQuantity(1);

            invoiceItemService.add(invoiceItem);
        }
    }}
