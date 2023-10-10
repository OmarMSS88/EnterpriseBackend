package fact.it.visitservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "visitoritem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VisitorItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemCode;
    private LocalDate date;
    private boolean paid;
    private int quantity;
    private double amount;
}
