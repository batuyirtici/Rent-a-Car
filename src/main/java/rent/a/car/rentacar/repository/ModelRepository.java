package rent.a.car.rentacar.repository;

import rent.a.car.rentacar.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Integer>{}