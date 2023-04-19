package rent.a.car.rentacar.business.concretes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import org.modelmapper.ModelMapper;
import rent.a.car.rentacar.business.rules.MaintenanceBusinessRules;
import rent.a.car.rentacar.entities.Maintenance;
import rent.a.car.rentacar.entities.enums.State;
import rent.a.car.rentacar.business.abstracts.CarService;
import rent.a.car.rentacar.repository.MaintenanceRepository;
import rent.a.car.rentacar.business.abstracts.MaintenanceService;
import rent.a.car.rentacar.business.dto.requests.creates.CreateMaintenanceRequest;
import rent.a.car.rentacar.business.dto.requests.updates.UpdateMaintenanceRequest;
import rent.a.car.rentacar.business.dto.responses.creates.CreateMaintenanceResponse;
import rent.a.car.rentacar.business.dto.responses.updates.UpdateMaintenanceResponse;
import rent.a.car.rentacar.business.dto.responses.gets.maintenance.GetMaintenanceResponse;
import rent.a.car.rentacar.business.dto.responses.gets.maintenance.GetAllMaintenancesResponse;

import java.util.List;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private final ModelMapper mapper;
    private final CarService carService;
    private final MaintenanceRepository repository;
    private final MaintenanceBusinessRules rules;

    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> maintenances = repository.findAll();
        List<GetAllMaintenancesResponse> response = maintenances
                .stream()
                .map(maintenance -> mapper.map(maintenance, GetAllMaintenancesResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetMaintenanceResponse getById(int id) {
        Maintenance maintenance = repository.findById(id).orElseThrow();
        GetMaintenanceResponse response = mapper.map(maintenance, GetMaintenanceResponse.class);

        return response;
    }

    @Override
    public GetMaintenanceResponse returnCarFromMaintenance(int carId) {
        rules.checkIfCarIsNotUnderMaintenance(carId);
        Maintenance maintenance = repository.findMaintenanceByCarIdAndIsCompletedFalse(carId);
        maintenance.setCompleted(true);
        maintenance.setEndDate(LocalDateTime.now());
        repository.save(maintenance);
        carService.changeState(carId, State.AVAILABLE);

        GetMaintenanceResponse response = mapper.map(maintenance, GetMaintenanceResponse.class);

        return response;
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        rules.checkCarAvailabilityForMaintenance(carService.getById(request.getCarId()).getState());
        rules.checkIfCarUnderMaintenance(request.getCarId());
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(0);
        maintenance.setCompleted(false);
        maintenance.setStartDate(LocalDateTime.now());
        maintenance.setEndDate(null);
        carService.changeState(request.getCarId(), State.MAINTENANCE);
        repository.save(maintenance);

        CreateMaintenanceResponse response = mapper.map(maintenance, CreateMaintenanceResponse.class);

        return response;
    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(id);
        repository.save(maintenance);

        UpdateMaintenanceResponse response = mapper.map(maintenance, UpdateMaintenanceResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        int carId= repository.findById(id).get().getCar().getId();
        carService.changeState(carId, State.AVAILABLE);
        repository.deleteById(id); }
}