package parcialBackend.recuperatorio.services.implementations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parcialBackend.recuperatorio.entities.InvoiceItem;
import parcialBackend.recuperatorio.entities.dtos.InvoiceItemDto;
import parcialBackend.recuperatorio.repositories.InvoiceItemRepository;
import parcialBackend.recuperatorio.services.InvoiceItemService;
import parcialBackend.recuperatorio.services.transformations.invoiceItem.InvoiceItemDtoMapper;
import parcialBackend.recuperatorio.services.transformations.invoiceItem.InvoiceItemMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class InvoiceItemServiceImpl implements InvoiceItemService {
    private final InvoiceItemRepository invoiceItemRepository;
    private final InvoiceItemMapper invoiceItemMapper;
    private final InvoiceItemDtoMapper invoiceItemDtoMapper;

    @Autowired
    public InvoiceItemServiceImpl(
            InvoiceItemRepository invoiceItemRepository,
            InvoiceItemMapper invoiceItemMapper,
            InvoiceItemDtoMapper invoiceItemDtoMapper) {
        this.invoiceItemRepository = invoiceItemRepository;
        this.invoiceItemMapper = invoiceItemMapper;
        this.invoiceItemDtoMapper = invoiceItemDtoMapper;
    }

    @Override
    public void add(InvoiceItemDto entity) {
        InvoiceItem invoiceItem = Optional.of(entity).map(invoiceItemMapper).get();
        invoiceItemRepository.save(invoiceItem);
    }

    @Override
    public InvoiceItemDto getById(Integer id) {
        Optional<InvoiceItem> optionalInvoiceItem = invoiceItemRepository.findById(id);
        return optionalInvoiceItem
                .map(invoiceItemDtoMapper)
                .orElseThrow();
    }

    @Override
    public List<InvoiceItemDto> getAll() {
        List<InvoiceItem> invoiceItems = invoiceItemRepository.findAll();
        return invoiceItems
                .stream()
                .map(invoiceItemDtoMapper)
                .toList();
    }

    @Override
    public InvoiceItemDto delete(Integer id) {
        Optional<InvoiceItem> optionalInvoiceItem = invoiceItemRepository.findById(id);
        optionalInvoiceItem.ifPresent(invoiceItemRepository::delete);

        return optionalInvoiceItem
                .map(invoiceItemDtoMapper)
                .orElseThrow();

    }

    @Override
    public void update(InvoiceItemDto entity) {
        Optional<InvoiceItem> invoiceItem = Stream.of(entity).map(invoiceItemMapper).findFirst();
        invoiceItem.ifPresent(invoiceItemRepository::save);
    }

    /*@Override
    public List<InvoiceItem> getByCustomer(Integer customerId) {
        return invoiceItemRepository.findByInvoice_CustomerCustomerID(customerId);
    }*/
}
