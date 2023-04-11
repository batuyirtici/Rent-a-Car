package rent.a.car.rentacar.business.dto.requests.creates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rent.a.car.rentacar.business.dto.requests.PaymentRequest;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalRequest {
    private int carId;
    private int dailyPrice;
    private int rentalForDays;
    private LocalDate startDate;

    private PaymentRequest paymentRequest; // Card Information
}