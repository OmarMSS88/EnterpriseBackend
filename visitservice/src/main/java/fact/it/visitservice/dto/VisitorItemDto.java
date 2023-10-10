package fact.it.visitservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitorItemDto {
    private Long id;
    private String itemCode;
    private LocalDate date;
    private boolean paid;
    private int quantity;
}
