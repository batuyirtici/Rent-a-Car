package rent.a.car.rentacar.business.dto.responses.updates;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBrandResponse {
    private int id;
    private String name;
}