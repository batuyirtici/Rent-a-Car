package rent.a.car.rentacar.business.abstracts;

import rent.a.car.rentacar.business.dto.requests.creates.CreateMaintenanceRequest;
import rent.a.car.rentacar.business.dto.requests.updates.UpdateMaintenanceRequest;
import rent.a.car.rentacar.business.dto.responses.creates.CreateMaintenanceResponse;
import rent.a.car.rentacar.business.dto.responses.updates.UpdateMaintenanceResponse;
import rent.a.car.rentacar.business.dto.responses.gets.maintenance.GetMaintenanceResponse;
import rent.a.car.rentacar.business.dto.responses.gets.maintenance.GetAllMaintenancesResponse;

import java.util.List;

public interface MaintenanceService {
    void delete(int id);
    GetMaintenanceResponse getById(int id);
    List<GetAllMaintenancesResponse> getAll();
    GetMaintenanceResponse returnCarFromMaintenance(int carId);
    CreateMaintenanceResponse add(CreateMaintenanceRequest request);
    UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request);
}