package rent.a.car.rentacar.api.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rent.a.car.rentacar.business.abstracts.RentalService;
import rent.a.car.rentacar.business.dto.requests.creates.CreateRentalRequest;
import rent.a.car.rentacar.business.dto.requests.updates.UpdateRentalRequest;
import rent.a.car.rentacar.business.dto.responses.creates.CreateRentalResponse;
import rent.a.car.rentacar.business.dto.responses.gets.rental.GetAllRentalsResponse;
import rent.a.car.rentacar.business.dto.responses.gets.rental.GetRentalResponse;
import rent.a.car.rentacar.business.dto.responses.updates.UpdateRentalResponse;

import java.util.List;

@RestController()
@RequestMapping("/api/renteds")
@AllArgsConstructor
public class RentalsController {
    private final RentalService service;

    @GetMapping
    public List<GetAllRentalsResponse> getAll()
    { return service.getAll(); }

    @GetMapping("/{id}")
    public GetRentalResponse getById(@PathVariable int id)
    { return service.getById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateRentalResponse add(@RequestBody CreateRentalRequest request)
    { return service.add(request); }

    @PutMapping("/{id}")
    public UpdateRentalResponse update(@PathVariable int id, @RequestBody UpdateRentalRequest request)
    { return service.update(id, request); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id)
    { service.delete(id); }
}