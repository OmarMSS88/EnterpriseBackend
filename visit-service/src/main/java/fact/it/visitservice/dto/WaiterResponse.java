package fact.it.visitservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WaiterResponse {
    private String id;
    private String waiterCode;
    private String name;
    private boolean receivedTip;
}
