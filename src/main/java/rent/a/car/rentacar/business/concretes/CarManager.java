package rent.a.car.rentacar.business.concretes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import org.modelmapper.ModelMapper;
import rent.a.car.rentacar.business.rules.CarBusinessRules;
import rent.a.car.rentacar.entities.Car;
import rent.a.car.rentacar.entities.enums.State;
import rent.a.car.rentacar.repository.CarRepository;
import rent.a.car.rentacar.business.abstracts.CarService;
import rent.a.car.rentacar.business.dto.requests.creates.CreateCarRequest;
import rent.a.car.rentacar.business.dto.requests.updates.UpdateCarRequest;
import rent.a.car.rentacar.business.dto.responses.gets.car.GetCarResponse;
import rent.a.car.rentacar.business.dto.responses.creates.CreateCarResponse;
import rent.a.car.rentacar.business.dto.responses.updates.UpdateCarResponse;
import rent.a.car.rentacar.business.dto.responses.gets.car.GetAllCarsResponse;

import java.util.List;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final ModelMapper mapper;
    private final CarRepository repository;
    private final CarBusinessRules rules;

    @Override
    public List<GetAllCarsResponse> getAll(boolean includeMaintenance) {
        List<Car> cars = filterCarsByMaintenanceState(includeMaintenance);
        List<GetAllCarsResponse> response = cars
                .stream()
                .map(car -> mapper.map(car, GetAllCarsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetCarResponse getById(int id) {
        rules.checkIfCarExists(id);
        Car car = repository.findById(id).orElseThrow();
        GetCarResponse response = mapper.map(car, GetCarResponse.class);

        return response;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        Car car = mapper.map(request, Car.class);
        car.setId(0);
        car.setState(State.AVAILABLE);
        repository.save(car);
        CreateCarResponse response = mapper.map(car, CreateCarResponse.class);

        return response;
    }

    @Override
    public UpdateCarResponse update(int id, UpdateCarRequest request) {
        rules.checkIfCarExists(id);
        Car car = mapper.map(request, Car.class);
        car.setId(id);
        repository.save(car);
        UpdateCarResponse response = mapper.map(car, UpdateCarResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfCarExists(id);
        repository.deleteById(id);
    }

    @Override
    public void changeState(int carId, State state) {
        Car car = repository.findById(carId).orElseThrow();
        car.setState(state);
        repository.save(car);
    }



    private List<Car> filterCarsByMaintenanceState(boolean includeMaintenance) {
        if (includeMaintenance) { return repository.findAll(); }

        return repository.findAllByStateIsNot(State.MAINTENANCE);
    }
}