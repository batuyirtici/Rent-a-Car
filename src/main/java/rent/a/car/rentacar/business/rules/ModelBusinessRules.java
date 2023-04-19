package rent.a.car.rentacar.business.rules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rent.a.car.rentacar.common.constants.Messages;
import rent.a.car.rentacar.core.exceptions.BusinessException;
import rent.a.car.rentacar.repository.ModelRepository;

@Service
@AllArgsConstructor
public class ModelBusinessRules {
    private final ModelRepository repository;

   public void checkIfModelExists(int id)
    { if(!repository.existsById(id)) throw new BusinessException(Messages.Model.NotExists); }
}
