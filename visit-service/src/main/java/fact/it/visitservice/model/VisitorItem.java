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
    /*@ManyToOne
    private Visit visit;

    public VisitorItem(long id, String itemCode, LocalDate date, boolean paid, int quantity, int amount) {
        this.id = id;
        this.itemCode = itemCode;
        this.date = date;
        this.paid = paid;
        this.quantity = quantity;
        this.amount = amount;
    }*/
}
