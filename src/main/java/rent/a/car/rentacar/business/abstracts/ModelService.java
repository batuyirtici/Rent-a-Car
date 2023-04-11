package rent.a.car.rentacar.business.abstracts;

import rent.a.car.rentacar.business.dto.requests.creates.CreateModelRequest;
import rent.a.car.rentacar.business.dto.requests.updates.UpdateModelRequest;
import rent.a.car.rentacar.business.dto.responses.creates.CreateModelResponse;
import rent.a.car.rentacar.business.dto.responses.gets.model.GetModelResponse;
import rent.a.car.rentacar.business.dto.responses.updates.UpdateModelResponse;
import rent.a.car.rentacar.business.dto.responses.gets.model.GetAllModelsResponse;

import java.util.List;

public interface ModelService {
    void delete(int id);
    GetModelResponse getById(int id);
    List<GetAllModelsResponse> getAll();
    CreateModelResponse add(CreateModelRequest request);
    UpdateModelResponse update(int id, UpdateModelRequest request);
}