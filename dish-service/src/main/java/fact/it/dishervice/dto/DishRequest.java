package fact.it.dishervice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishRequest {
    private String dishNr;
    private String dishName;
    private double price;
}
