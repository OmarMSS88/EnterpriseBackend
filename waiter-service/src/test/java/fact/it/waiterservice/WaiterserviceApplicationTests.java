package fact.it.waiterservice;

import fact.it.waiterservice.dto.WaiterRequest;
import fact.it.waiterservice.dto.WaiterResponse;
import fact.it.waiterservice.model.Waiter;
import fact.it.waiterservice.repository.WaiterRepository;
import fact.it.waiterservice.service.WaiterService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class WaiterserviceApplicationTests {

    @Test
    void createWaiter_shouldSaveWaiter() {
        // Arrange
        WaiterRepository waiterRepository = mock(WaiterRepository.class);
        WaiterService waiterService = new WaiterService(waiterRepository);

        WaiterRequest waiterRequest = new WaiterRequest("4XYZ", "John Doe", true);

        // Act
        waiterService.createWaiter(waiterRequest);

        // Assert
        ArgumentCaptor<Waiter> waiterCaptor = ArgumentCaptor.forClass(Waiter.class);
        verify(waiterRepository).save(waiterCaptor.capture());

        Waiter capturedWaiter = waiterCaptor.getValue();
        assertEquals("4XYZ", capturedWaiter.getWaiterCode());
        assertEquals("John Doe", capturedWaiter.getName());
        assertTrue(capturedWaiter.isReceivedTip());
        // Add additional assertions if needed
        // For example, assertEquals for each field in waiterResponse and capturedWaiter
    }

    @Test
    void getAllWaiters_shouldReturnWaiterResponses() {
        // Arrange
        WaiterRepository waiterRepository = mock(WaiterRepository.class);
        WaiterService waiterService = new WaiterService(waiterRepository);

        List<Waiter> mockWaiters = new ArrayList<>(Arrays.asList(
                Waiter.builder().waiterCode("1BAC").name("Ben Active Cal").receivedTip(false).build(),
                Waiter.builder().waiterCode("2JC").name("James Cameron").receivedTip(true).build(),
                Waiter.builder().waiterCode("2HF").name("Harry Fields").receivedTip(false).build()
        ));

        when(waiterRepository.findAll()).thenReturn(mockWaiters);

        // Act
        List<WaiterResponse> waiterResponses = waiterService.getAllWaiters();

        // Assert
        assertNotNull(waiterResponses);
        assertEquals(3, waiterResponses.size());

        // Add more specific assertions if needed
        assertEquals("1BAC", waiterResponses.get(0).getWaiterCode());
        assertEquals("Ben Active Cal", waiterResponses.get(0).getName());
        assertFalse(waiterResponses.get(0).isReceivedTip());
        // Repeat similar assertions for other waiters
    }

    @Test
    void updateWaiter_shouldUpdateExistingWaiter() {
        // Arrange
        WaiterRepository waiterRepository = mock(WaiterRepository.class);
        WaiterService waiterService = new WaiterService(waiterRepository);

        String waiterIdToUpdate = "2JC";
        WaiterRequest updatedWaiterRequest = new WaiterRequest("2JC", "James Cameron", false);

        Waiter existingWaiter = new Waiter("2", "2JC", "James Cameron", true);

        // Mock the behavior of the repository
        when(waiterRepository.findById(waiterIdToUpdate)).thenReturn(Optional.of(existingWaiter));

        // Act
        waiterService.updateWaiter(updatedWaiterRequest, waiterIdToUpdate);

        // Assert
        verify(waiterRepository).findById(waiterIdToUpdate); // Verify that findById was called with the correct ID
        verify(waiterRepository).save(any(Waiter.class)); // Verify that save was called with any Waiter instance

        // Alternatively, you can use ArgumentCaptor to capture the updated waiter and make assertions on its values
        ArgumentCaptor<Waiter> waiterCaptor = ArgumentCaptor.forClass(Waiter.class);
        verify(waiterRepository).save(waiterCaptor.capture());

        Waiter capturedWaiter = waiterCaptor.getValue();
        assertNotNull(capturedWaiter);
        assertEquals("2JC", capturedWaiter.getWaiterCode());
        assertEquals("James Cameron", capturedWaiter.getName());
        assertFalse(capturedWaiter.isReceivedTip());
    }

    @Test
    void deleteWaiter_shouldRemoveWaiter() {
        // Arrange
        WaiterRepository waiterRepository = mock(WaiterRepository.class);
        WaiterService waiterService = new WaiterService(waiterRepository);

        String waiterIdToDelete = "2JC";

        // Act
        waiterService.deleteWaiter(waiterIdToDelete);

        // Assert
        verify(waiterRepository).deleteById(waiterIdToDelete);
    }

}
