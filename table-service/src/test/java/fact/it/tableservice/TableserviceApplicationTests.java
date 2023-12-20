package fact.it.tableservice;

import fact.it.tableservice.dto.TableRequest;
import fact.it.tableservice.dto.TableResponse;
import fact.it.tableservice.model.Table;
import fact.it.tableservice.repository.TableRepository;
import fact.it.tableservice.service.TableService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TableserviceApplicationTests {

    @Test
    void createTable_shouldSaveTable() {
        // Arrange
        TableRepository tableRepository = mock(TableRepository.class);
        TableService tableService = new TableService(tableRepository);

        TableRequest tableRequest = new TableRequest("D1", 6);

        // Act
        tableService.createTable(tableRequest);

        // Assert
        ArgumentCaptor<Table> tableCaptor = ArgumentCaptor.forClass(Table.class);
        verify(tableRepository).save(tableCaptor.capture());

        Table capturedTable = tableCaptor.getValue();
        assertEquals("D1", capturedTable.getTableNr());
        assertEquals(6, capturedTable.getSeatAmount());
        // Add additional assertions if needed
        // For example, assertEquals for each field in tableRequest and capturedTable
    }

    @Test
    void getAllTables_shouldReturnTableResponses() {
        // Arrange
        TableRepository tableRepository = mock(TableRepository.class);
        TableService tableService = new TableService(tableRepository);

        List<Table> mockTables = new ArrayList<>(Arrays.asList(
                Table.builder().id("1L").tableNr("K5").seatAmount(5).build(),
                Table.builder().id("2L").tableNr("O9").seatAmount(4).build(),
                Table.builder().id("3L").tableNr("M6").seatAmount(6).build()
        ));

        when(tableRepository.findAll()).thenReturn(mockTables);

        // Act
        List<TableResponse> tableResponses = tableService.getAllTables();

        // Assert
        assertNotNull(tableResponses);
        assertEquals(3, tableResponses.size());

        // Add more specific assertions if needed
        assertEquals("K5", tableResponses.get(0).getTableNr());
        assertEquals(5, tableResponses.get(0).getSeatAmount());
        // Add assertions to verify that the mapping from Table to TableResponse is correct
        // You can compare the expected tableResponses with the actual tableResponses
    }

    @Test
    void updateTable_shouldUpdateExistingTable() {
        // Arrange
        TableRepository tableRepository = mock(TableRepository.class);
        TableService tableService = new TableService(tableRepository);

        String tableIdToUpdate = "B1";
        TableRequest updatedTableRequest = new TableRequest("B1", 6);

        // Mock the behavior of the repository
        Table existingTable = new Table("2L", "B1", 4);
        when(tableRepository.findById(tableIdToUpdate)).thenReturn(Optional.of(existingTable));

        // Act
        tableService.updateTable(updatedTableRequest, tableIdToUpdate);

        // Use ArgumentCaptor to capture the updated table and verify its values
        ArgumentCaptor<Table> tableCaptor = ArgumentCaptor.forClass(Table.class);
        verify(tableRepository).save(tableCaptor.capture());

        // Assert
        verify(tableRepository).findById(tableIdToUpdate); // Verify that findById was called with the correct ID

        Table capturedTable = tableCaptor.getValue();
        assertNotNull(capturedTable);

        // Verify that the updated table has the expected values
        assertEquals(updatedTableRequest.getTableNr(), capturedTable.getTableNr());
        assertEquals(updatedTableRequest.getSeatAmount(), capturedTable.getSeatAmount());
        // Add more assertions to verify other properties if needed
    }

    @Test
    void deleteTable_shouldRemoveTable() {
        // Arrange
        TableRepository tableRepository = mock(TableRepository.class);
        TableService tableService = new TableService(tableRepository);

        String tableIdToDelete = "B1";

        // Act
        tableService.deleteTable(tableIdToDelete);

        // Assert
        verify(tableRepository).deleteById(tableIdToDelete);
        // Add assertions to verify that the table has been removed correctly
        // You may want to use ArgumentCaptor to capture the removed table and verify its values
    }
}
