package rent.a.car.rentacar.repository;

import rent.a.car.rentacar.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {
    Maintenance findMaintenanceByCarIdAndIsCompletedFalse(int carId);
    boolean existsByCarIdAndIsCompletedFalse(int carId);
}