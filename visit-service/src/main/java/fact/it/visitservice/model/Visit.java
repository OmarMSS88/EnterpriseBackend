package fact.it.visitservice.model;


import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "visit")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String visitNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "visit")
    private List<VisitorItem> visitorItemList;

}
