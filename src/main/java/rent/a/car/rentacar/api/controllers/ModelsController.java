package rent.a.car.rentacar.api.controllers;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import rent.a.car.rentacar.business.abstracts.ModelService;
import rent.a.car.rentacar.business.dto.requests.creates.CreateModelRequest;
import rent.a.car.rentacar.business.dto.requests.updates.UpdateModelRequest;
import rent.a.car.rentacar.business.dto.responses.creates.CreateModelResponse;
import rent.a.car.rentacar.business.dto.responses.gets.model.GetModelResponse;
import rent.a.car.rentacar.business.dto.responses.updates.UpdateModelResponse;
import rent.a.car.rentacar.business.dto.responses.gets.model.GetAllModelsResponse;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/models")
public class ModelsController {
    private final ModelService service;

    @GetMapping
    public List<GetAllModelsResponse> getAll()
    { return service.getAll(); }

    @GetMapping("/{id}")
    public GetModelResponse getById(@PathVariable int id)
    { return service.getById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateModelResponse add(@RequestBody CreateModelRequest request)
    { return service.add(request); }

    @PutMapping("/{id}")
    public UpdateModelResponse update(@PathVariable int id, @RequestBody UpdateModelRequest request)
    { return service.update(id, request); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id)
    { service.delete(id); }
}