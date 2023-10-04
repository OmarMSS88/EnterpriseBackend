package fact.it.tableservice.controller;

import fact.it.tableservice.dto.TableRequest;
import fact.it.tableservice.dto.TableResponse;
import fact.it.tableservice.model.Table;
import fact.it.tableservice.repository.TableRepository;
import fact.it.tableservice.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/table")
@RequiredArgsConstructor
public class TableController {
    private final TableService tableService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createTable
            (@RequestBody TableRequest tableRequest){
        tableService.createTable(tableRequest);
    }



    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<TableResponse> getAllTables(){
        return tableService.getAllTables();
    }
}
