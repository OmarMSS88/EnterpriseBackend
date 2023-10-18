package fact.it.waiterservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "waiter")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Waiter {
    private String id;
    private String waiterCode;
    private String name;
    private boolean receivedTip;
}
