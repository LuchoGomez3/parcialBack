package parcialBackend.recuperatorio.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import parcialBackend.recuperatorio.entities.dtos.InvoiceDto;
import parcialBackend.recuperatorio.services.InvoiceService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDto>> getAll(){
        List<InvoiceDto> invoices = invoiceService.getAll();
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDto> getById(@PathVariable("id") int id) {
        InvoiceDto invoice = invoiceService.getById(id);
        return ResponseEntity.ok(invoice);
    }

    @PostMapping()
    public ResponseEntity<Void> add(@RequestBody InvoiceDto invoice) {
        try {
            invoiceService.add(invoice);
            return  ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping()
    public ResponseEntity<Void> update(@RequestBody InvoiceDto invoice) {
        invoiceService.update(invoice);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        invoiceService.delete(id);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }


}
