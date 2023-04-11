package rent.a.car.rentacar.business.dto.requests.creates;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest {
    private int modelId;
    private String plate;
    private int modelYear;
    private double dailyPrice;
}