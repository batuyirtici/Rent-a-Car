package rent.a.car.rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rent.a.car.rentacar.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment findByCardNumber(String cardNumber);
    boolean existsByCardNumber(String cardNumber);
    boolean existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv
            (String cardNumber, String cardHolder, int cardExpirationYear, int cardExpirationMonth, String cardCvv);
}