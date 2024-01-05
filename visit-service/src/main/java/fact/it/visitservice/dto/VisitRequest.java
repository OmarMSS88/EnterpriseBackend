package fact.it.visitservice.dto;

import fact.it.visitservice.model.VisitorItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitRequest {
    private List<VisitorItem> visitorItemList;
}
