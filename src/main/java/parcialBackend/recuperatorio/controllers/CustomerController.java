package parcialBackend.recuperatorio.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import parcialBackend.recuperatorio.entities.dtos.CustomerDto;
import parcialBackend.recuperatorio.services.CustomerService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll(){
        List<CustomerDto> customers = customerService.getAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable("id") int id) {
        CustomerDto customer = customerService.getById(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping()
    public ResponseEntity<Void> add(@RequestBody CustomerDto customer) {
        try {
            customerService.add(customer);
            return  ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping()
    public ResponseEntity<Void> update(@RequestBody CustomerDto customer) {
        customerService.update(customer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        customerService.delete(id);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
}