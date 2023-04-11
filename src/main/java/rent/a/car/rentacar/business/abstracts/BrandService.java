package rent.a.car.rentacar.business.abstracts;

import rent.a.car.rentacar.business.dto.requests.creates.CreateBrandRequest;
import rent.a.car.rentacar.business.dto.requests.updates.UpdateBrandRequest;
import rent.a.car.rentacar.business.dto.responses.gets.brand.GetBrandResponse;
import rent.a.car.rentacar.business.dto.responses.updates.UpdateBrandResponse;
import rent.a.car.rentacar.business.dto.responses.creates.CreateBrandResponse;
import rent.a.car.rentacar.business.dto.responses.gets.brand.GetAllBrandsResponse;

import java.util.List;

public interface BrandService {
    void delete(int id);
    GetBrandResponse getById(int id);
    List<GetAllBrandsResponse> getAll();
    CreateBrandResponse add(CreateBrandRequest request);
    UpdateBrandResponse update(int id, UpdateBrandRequest request);
}