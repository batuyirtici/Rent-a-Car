package rent.a.car.rentacar.repository;

import rent.a.car.rentacar.entities.Car;
import rent.a.car.rentacar.entities.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer>
{ List<Car> findAllByStateIsNot(State state); }