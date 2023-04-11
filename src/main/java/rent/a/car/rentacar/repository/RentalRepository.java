package rent.a.car.rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rent.a.car.rentacar.entities.Rental;

public interface RentalRepository extends JpaRepository<Rental, Integer> {}