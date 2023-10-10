package fact.it.dishservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "dish")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Dish {
    private String id;
    private String dishNr;
    private String dishName;
    private double price;
}
