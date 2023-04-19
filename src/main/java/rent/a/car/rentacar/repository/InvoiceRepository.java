package rent.a.car.rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rent.a.car.rentacar.entities.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {}