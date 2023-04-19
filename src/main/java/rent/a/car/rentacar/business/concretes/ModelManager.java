package rent.a.car.rentacar.business.concretes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import org.modelmapper.ModelMapper;
import rent.a.car.rentacar.business.rules.ModelBusinessRules;
import rent.a.car.rentacar.entities.Model;
import rent.a.car.rentacar.repository.ModelRepository;
import rent.a.car.rentacar.business.abstracts.ModelService;
import rent.a.car.rentacar.business.dto.requests.creates.CreateModelRequest;
import rent.a.car.rentacar.business.dto.requests.updates.UpdateModelRequest;
import rent.a.car.rentacar.business.dto.responses.creates.CreateModelResponse;
import rent.a.car.rentacar.business.dto.responses.gets.model.GetModelResponse;
import rent.a.car.rentacar.business.dto.responses.updates.UpdateModelResponse;
import rent.a.car.rentacar.business.dto.responses.gets.model.GetAllModelsResponse;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private final ModelMapper mapper;
    private final ModelRepository repository;
    private final ModelBusinessRules rules;

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = repository.findAll();
        List<GetAllModelsResponse> response = models
                .stream()
                .map(model -> mapper.map(model, GetAllModelsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetModelResponse getById(int id) {
        rules.checkIfModelExists(id);
        Model model = repository.findById(id).orElseThrow();

        GetModelResponse response = mapper.map(model, GetModelResponse.class);

        return response;
    }

    @Override
    public CreateModelResponse add(CreateModelRequest request) {
        Model model = mapper.map(request, Model.class);
        model.setId(0);
        repository.save(model);

        CreateModelResponse response = mapper.map(model, CreateModelResponse.class);

        return response;
    }

    @Override
    public UpdateModelResponse update(int id, UpdateModelRequest request) {
        rules.checkIfModelExists(id);
        Model model = mapper.map(request, Model.class);
        model.setId(id);
        repository.save(model);

        UpdateModelResponse response = mapper.map(model, UpdateModelResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfModelExists(id);
        repository.deleteById(id);
    }
}