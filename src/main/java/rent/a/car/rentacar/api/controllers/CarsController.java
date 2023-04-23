package rent.a.car.rentacar.api.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import rent.a.car.rentacar.business.abstracts.CarService;
import rent.a.car.rentacar.business.dto.requests.creates.CreateCarRequest;
import rent.a.car.rentacar.business.dto.requests.updates.UpdateCarRequest;
import rent.a.car.rentacar.business.dto.responses.gets.car.GetCarResponse;
import rent.a.car.rentacar.business.dto.responses.creates.CreateCarResponse;
import rent.a.car.rentacar.business.dto.responses.updates.UpdateCarResponse;
import rent.a.car.rentacar.business.dto.responses.gets.car.GetAllCarsResponse;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cars")
public class CarsController {
    private final CarService service;

    @GetMapping
    public List<GetAllCarsResponse> getAll(@RequestParam(defaultValue = "true") boolean includeMaintenance)
    { return service.getAll(includeMaintenance); }

    @GetMapping("/{id}")
    public GetCarResponse getById(@PathVariable int id)
    { return service.getById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCarResponse add(@Valid @RequestBody CreateCarRequest request)
    { return service.add(request); }

    @PutMapping("/{id}")
    public UpdateCarResponse update(@PathVariable int id, @RequestBody UpdateCarRequest request)
    { return service.update(id, request); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id)
    { service.delete(id); }
}