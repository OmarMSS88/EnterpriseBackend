package fact.it.visitservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableResponse {
    private String id;
    private String tableNr;
    private int seatAmount;
}
