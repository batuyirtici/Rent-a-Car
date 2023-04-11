package rent.a.car.rentacar.business.dto.responses.gets.rental;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllRentalsResponse {
    private int id;
    private int carId;
    private int dailyPrice;
    private int rentalForDays;
    private int totalPrice;
    private LocalDate startDate;
}

