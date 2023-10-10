package fact.it.visitservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitResponse {
    private String visitNumber;
    private List<VisitorItemDto> visitorItemList;
}
