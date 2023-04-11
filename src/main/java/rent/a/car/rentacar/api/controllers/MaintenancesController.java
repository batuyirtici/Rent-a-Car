package rent.a.car.rentacar.api.controllers;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import rent.a.car.rentacar.business.abstracts.MaintenanceService;
import rent.a.car.rentacar.business.dto.requests.creates.CreateMaintenanceRequest;
import rent.a.car.rentacar.business.dto.requests.updates.UpdateMaintenanceRequest;
import rent.a.car.rentacar.business.dto.responses.creates.CreateMaintenanceResponse;
import rent.a.car.rentacar.business.dto.responses.updates.UpdateMaintenanceResponse;
import rent.a.car.rentacar.business.dto.responses.gets.maintenance.GetMaintenanceResponse;
import rent.a.car.rentacar.business.dto.responses.gets.maintenance.GetAllMaintenancesResponse;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/maintenances")
public class MaintenancesController {
    private final MaintenanceService service;

    @GetMapping
    public List<GetAllMaintenancesResponse> getAll()
    { return service.getAll(); }

    @GetMapping("/{id}")
    public GetMaintenanceResponse getById(@PathVariable int id)
    { return service.getById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMaintenanceResponse add(@RequestBody CreateMaintenanceRequest request)
    { return service.add(request); }

    @PutMapping("/return")
    public GetMaintenanceResponse returnCarFromMaintenance(@RequestParam int carId)
    { return service.returnCarFromMaintenance(carId); }

    @PutMapping("/{id}")
    public UpdateMaintenanceResponse update(@PathVariable int id, @RequestBody UpdateMaintenanceRequest request)
    { return service.update(id, request); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id)
    { service.delete(id); }
}