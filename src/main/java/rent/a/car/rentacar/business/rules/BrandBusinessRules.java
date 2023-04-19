package rent.a.car.rentacar.business.rules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rent.a.car.rentacar.common.constants.Messages;
import rent.a.car.rentacar.core.exceptions.BusinessException;
import rent.a.car.rentacar.repository.BrandRepository;

@Service
@AllArgsConstructor
public class BrandBusinessRules {
    private final BrandRepository repository;

    public void checkIfBrandExists(int id)
    { if (!repository.existsById(id)) throw new BusinessException(Messages.Brand.NotExists); }

    public void checkIfBrandExistsByName(String name)
    { if (repository.existsByNameIgnoreCase(name)) throw new BusinessException(Messages.Brand.Exists); }
}
