package fact.it.tableservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "table")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Table {
    private String id;
    private String tableNr;
    private int seatAmount;
}
