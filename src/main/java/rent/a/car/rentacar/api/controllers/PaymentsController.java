package rent.a.car.rentacar.api.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rent.a.car.rentacar.business.abstracts.PaymentService;
import rent.a.car.rentacar.business.dto.requests.creates.CreatePaymentRequest;
import rent.a.car.rentacar.business.dto.requests.updates.UpdatePaymentRequest;
import rent.a.car.rentacar.business.dto.responses.creates.CreatePaymentResponse;
import rent.a.car.rentacar.business.dto.responses.gets.payment.GetAllPaymentsResponse;
import rent.a.car.rentacar.business.dto.responses.gets.payment.GetPaymentResponse;
import rent.a.car.rentacar.business.dto.responses.updates.UpdatePaymentResponse;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/payments")
public class PaymentsController {
    private final PaymentService service;

    @GetMapping
    public List<GetAllPaymentsResponse> getAll()
    { return service.getAll(); }

    @GetMapping("/{id}")
    public GetPaymentResponse getById(@PathVariable int id)
    { return service.getById(id); }

    @PostMapping
    public CreatePaymentResponse add(@Valid @RequestBody CreatePaymentRequest request)
    { return service.add(request); }

    @PutMapping("/{id}")
    public UpdatePaymentResponse update(@PathVariable int id, @Valid @RequestBody UpdatePaymentRequest request)
    { return service.update(id, request); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id)
    { service.delete(id); }
}