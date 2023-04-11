package rent.a.car.rentacar.business.concretes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import org.modelmapper.ModelMapper;
import rent.a.car.rentacar.entities.Brand;
import rent.a.car.rentacar.repository.BrandRepository;
import rent.a.car.rentacar.business.abstracts.BrandService;
import rent.a.car.rentacar.business.dto.requests.creates.CreateBrandRequest;
import rent.a.car.rentacar.business.dto.requests.updates.UpdateBrandRequest;
import rent.a.car.rentacar.business.dto.responses.creates.CreateBrandResponse;
import rent.a.car.rentacar.business.dto.responses.gets.brand.GetBrandResponse;
import rent.a.car.rentacar.business.dto.responses.updates.UpdateBrandResponse;
import rent.a.car.rentacar.business.dto.responses.gets.brand.GetAllBrandsResponse;

import java.util.List;


@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final ModelMapper mapper;
    private final BrandRepository repository;

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = repository.findAll();
        List<GetAllBrandsResponse> response = brands
                .stream()
                .map(brand -> mapper.map(brand, GetAllBrandsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetBrandResponse getById(int id) {
        checkIfBrandExists(id);
        Brand brand = repository.findById(id).orElseThrow();
        GetBrandResponse response = mapper.map(brand, GetBrandResponse.class);

        return response;
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest request) {
        Brand brand = mapper.map(request, Brand.class);
        brand.setId(0);
        repository.save(brand);
        CreateBrandResponse response = mapper.map(brand, CreateBrandResponse.class);

        return response;
    }

    @Override
    public UpdateBrandResponse update(int id, UpdateBrandRequest request) {
        checkIfBrandExists(id);
        Brand brand = mapper.map(request, Brand.class);
        brand.setId(id);
        repository.save(brand);
        UpdateBrandResponse response = mapper.map(brand, UpdateBrandResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        checkIfBrandExists(id);
        repository.deleteById(id);
    }

    // Business Rules
    private void checkIfBrandExists(int id)
    { if (!repository.existsById(id)) throw new RuntimeException("Brand is not exist!"); }
}