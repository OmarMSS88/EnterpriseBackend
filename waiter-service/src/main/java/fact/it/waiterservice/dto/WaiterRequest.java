package fact.it.waiterservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WaiterRequest {
    private String waiterCode;
    private String name;
    private boolean receivedTip;
}
