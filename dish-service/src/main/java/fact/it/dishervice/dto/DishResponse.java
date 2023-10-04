package fact.it.dishervice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishResponse {
    private String id;
    private String dishNr;
    private String dishName;
    private double price;
}
