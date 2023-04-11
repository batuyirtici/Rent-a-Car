package rent.a.car.rentacar.business.dto.responses.gets.brand;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetBrandResponse {
    private int id;
    private String name;
}