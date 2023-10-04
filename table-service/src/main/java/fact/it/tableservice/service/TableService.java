package fact.it.tableservice.service;

import fact.it.tableservice.dto.TableRequest;
import fact.it.tableservice.dto.TableResponse;
import fact.it.tableservice.model.Table;
import fact.it.tableservice.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TableService {
    private final TableRepository tableRepository;

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

    private TableResponse mapToTableResponse(Table table){
        return TableResponse.builder()
                .id(table.getId())
                .tableNr(table.getTableNr())
                .seatAmount(table.getSeatAmount())
                .build();
    }
}
