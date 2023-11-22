package parcialBackend.recuperatorio.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parcialBackend.recuperatorio.entities.Invoice;
import parcialBackend.recuperatorio.entities.dtos.InvoiceDto;
import parcialBackend.recuperatorio.repositories.InvoiceRepository;
import parcialBackend.recuperatorio.services.InvoiceService;
import parcialBackend.recuperatorio.services.transformations.invoice.InvoiceDtoMapper;
import parcialBackend.recuperatorio.services.transformations.invoice.InvoiceMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceDtoMapper invoiceDtoMapper;
    private final InvoiceMapper invoiceMapper;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository,
                              InvoiceDtoMapper invoiceDtoMapper,
                              InvoiceMapper invoiceMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceDtoMapper = invoiceDtoMapper;
        this.invoiceMapper = invoiceMapper;
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
}