package parcialBackend.recuperatorio.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import parcialBackend.recuperatorio.entities.dtos.InvoiceItemDto;
import parcialBackend.recuperatorio.services.InvoiceItemService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/invoice-items")
public class InvoiceItemController {
    private final InvoiceItemService invoiceItemService;

    @Autowired
    public InvoiceItemController(InvoiceItemService invoiceItemService) {
        this.invoiceItemService = invoiceItemService;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceItemDto>> getAll(){
        List<InvoiceItemDto> invoiceItems = invoiceItemService.getAll();
        return ResponseEntity.ok(invoiceItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceItemDto> getById(@PathVariable("id") int id) {
        InvoiceItemDto invoiceItem = invoiceItemService.getById(id);
        return ResponseEntity.ok(invoiceItem);
    }

    @PostMapping()
    public ResponseEntity<Void> add(@RequestBody InvoiceItemDto invoiceItem) {
        try {
            invoiceItemService.add(invoiceItem);
            return  ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping()
    public ResponseEntity<Void> update(@RequestBody InvoiceItemDto invoiceItem) {
        invoiceItemService.update(invoiceItem);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        invoiceItemService.delete(id);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
}
