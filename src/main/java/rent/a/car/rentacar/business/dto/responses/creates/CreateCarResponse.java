package rent.a.car.rentacar.business.dto.responses.creates;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import rent.a.car.rentacar.entities.enums.State;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarResponse {
    private int id;
    private int modelId;
    private State state;
    private String plate;
    private int modelYear;
    private double dailyPrice;
}