package rent.a.car.rentacar.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rent.a.car.rentacar.business.abstracts.InvoiceService;
import rent.a.car.rentacar.business.dto.requests.creates.CreateInvoiceRequest;
import rent.a.car.rentacar.business.dto.requests.updates.UpdateInvoiceRequest;
import rent.a.car.rentacar.business.dto.responses.creates.CreateInvoiceResponse;
import rent.a.car.rentacar.business.dto.responses.gets.invoice.GetAllInvoicesResponse;
import rent.a.car.rentacar.business.dto.responses.gets.invoice.GetInvoiceResponse;
import rent.a.car.rentacar.business.dto.responses.updates.UpdateInvoiceResponse;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping ("/invoices")
public class InvoicesController {
    private final InvoiceService service;

    @GetMapping
    public List<GetAllInvoicesResponse> getAll()
    { return service.getAll(); }

    @GetMapping ("/{id}")
    public GetInvoiceResponse getById(@PathVariable int id)
    { return service.getById(id); }
    
    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public CreateInvoiceResponse add(@RequestBody CreateInvoiceRequest request)
    { return service.add(request); }
    
    @PutMapping ("/{id}")
    public UpdateInvoiceResponse update (@PathVariable int id, @RequestBody UpdateInvoiceRequest request)
    { return service.update(id, request); }

    @DeleteMapping ("/{id}")
    public void delete(@PathVariable int id)
    { service.delete(id); }
}