package rent.a.car.rentacar.business.dto.requests.creates;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import rent.a.car.rentacar.common.constants.Regex;
import rent.a.car.rentacar.common.utils.NotFutureYear;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest {
    private int modelId;

    @Pattern(regexp = Regex.Plate) // 01-81 A-AB-ABC 000-0000
    private String plate;

    @Min(1998)
    @NotFutureYear
    private int modelYear;

    @Min(1)
    private double dailyPrice;
}