package fact.it.tableservice.service;

import fact.it.tableservice.dto.TableRequest;
import fact.it.tableservice.dto.TableResponse;
import fact.it.tableservice.model.Table;
import fact.it.tableservice.repository.TableRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TableService {
    @Autowired
    private final TableRepository tableRepository;

    private List<TableRequest> tables = new ArrayList<>(Arrays.asList(
            new TableRequest("A1", 2),
            new TableRequest("B1", 4),
            new TableRequest("C4", 8)
    ));

    @PostConstruct
    private void postConstruct() {
        Table table = new Table("A1", "A1", 2);
        Table B1 = new Table("A1", "B2", 4);
        Table C4 = new Table("A1", "C4", 8);
        tableRepository.save(table);
        tableRepository.save(B1);
        tableRepository.save(C4);
    }

    public void createTable(TableRequest tableRequest){
        Table table = Table.builder()
                .tableNr(tableRequest.getTableNr())
                .seatAmount(tableRequest.getSeatAmount())
                .build();

        tableRepository.save(table);
    }

    public List<TableResponse> getAllTables(){
        List<Table> tables = tableRepository.findAll();

        return tables.stream().map(this::mapToTableResponse).toList();
    }

    public void updateTable(TableRequest tableRequest, String id) {
        Table tableToUpdate = tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found"));

        tableToUpdate.setTableNr(tableRequest.getTableNr());
        tableToUpdate.setSeatAmount(tableRequest.getSeatAmount());

        tableRepository.save(tableToUpdate);
    }

    public void deleteTable(String id) {
        tableRepository.deleteById(id);
    }

    private TableResponse mapToTableResponse(Table table){
        return TableResponse.builder()
                .id(table.getId())
                .tableNr(table.getTableNr())
                .seatAmount(table.getSeatAmount())
                .build();
    }
}
