package rent.a.car.rentacar.business.abstracts;

import rent.a.car.rentacar.business.dto.requests.creates.CreateRentalRequest;
import rent.a.car.rentacar.business.dto.requests.updates.UpdateRentalRequest;
import rent.a.car.rentacar.business.dto.responses.creates.CreateRentalResponse;
import rent.a.car.rentacar.business.dto.responses.gets.rental.GetAllRentalsResponse;
import rent.a.car.rentacar.business.dto.responses.gets.rental.GetRentalResponse;
import rent.a.car.rentacar.business.dto.responses.updates.UpdateRentalResponse;

import java.util.List;

public interface RentalService {
    List<GetAllRentalsResponse> getAll();
    GetRentalResponse getById(int id);
    CreateRentalResponse add (CreateRentalRequest request);
    UpdateRentalResponse update(int id, UpdateRentalRequest request);
    void delete(int id);
}