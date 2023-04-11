package rent.a.car.rentacar.business.dto.responses.gets.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetModelResponse {
    private int id;
    private int brandId;
    private String name;
}