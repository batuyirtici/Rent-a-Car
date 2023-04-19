package rent.a.car.rentacar.business.rules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rent.a.car.rentacar.common.constants.Messages;
import rent.a.car.rentacar.core.exceptions.BusinessException;
import rent.a.car.rentacar.entities.enums.State;
import rent.a.car.rentacar.repository.MaintenanceRepository;

@Service
@AllArgsConstructor
public class MaintenanceBusinessRules {
    private final MaintenanceRepository repository;

    public void checkIfMaintenanceExists(int id)
    { if (!repository.existsById(id)) throw new BusinessException(Messages.Maintenance.NotExists); }

    public void checkIfCarUnderMaintenance(int carId)
    { if (repository.existsByCarIdAndIsCompletedFalse(carId))
        throw new BusinessException(Messages.Maintenance.CarExists); }

    public void checkIfCarIsNotUnderMaintenance(int carId)
    { if (!repository.existsByCarIdAndIsCompletedFalse(carId))
        throw new BusinessException(Messages.Maintenance.CarNotExists); }

    public void checkCarAvailabilityForMaintenance(State state)
    { if (state.equals(State.RENTED)) throw new BusinessException(Messages.Maintenance.CarIsRented); }
}
