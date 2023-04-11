package rent.a.car.rentacar.business.abstracts;

import rent.a.car.rentacar.business.dto.requests.creates.CreateCarRequest;
import rent.a.car.rentacar.business.dto.requests.updates.UpdateCarRequest;
import rent.a.car.rentacar.business.dto.responses.gets.car.GetCarResponse;
import rent.a.car.rentacar.business.dto.responses.creates.CreateCarResponse;
import rent.a.car.rentacar.business.dto.responses.gets.car.GetAllCarsResponse;
import rent.a.car.rentacar.business.dto.responses.updates.UpdateCarResponse;

import java.util.List;

import rent.a.car.rentacar.entities.enums.State;

public interface CarService {
    void delete(int id);
    GetCarResponse getById(int id);
    void changeState(int carId, State state);
    CreateCarResponse add(CreateCarRequest request);
    UpdateCarResponse update(int id, UpdateCarRequest request);
    List<GetAllCarsResponse> getAll(boolean includeMaintenance);
}