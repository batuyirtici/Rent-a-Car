package rent.a.car.rentacar.repository;


import rent.a.car.rentacar.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer>
{ boolean existsByNameIgnoreCase(String name); }