package fact.it.customerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(value = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Customer {
    private String id;
    private String customerNr;
    private LocalDate date;
    private int customerAmount;
    private boolean paid;
}
