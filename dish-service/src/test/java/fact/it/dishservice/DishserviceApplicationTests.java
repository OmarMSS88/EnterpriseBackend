package fact.it.dishservice;

import fact.it.dishservice.dto.DishRequest;
import fact.it.dishservice.dto.DishResponse;
import fact.it.dishservice.model.Dish;
import fact.it.dishservice.repository.DishRepository;
import fact.it.dishservice.service.DishService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DishserviceApplicationTests {

    @Mock
    private DishRepository dishRepository;

    @InjectMocks
    private DishService dishService;



    @Test
    void createDish_shouldSaveDish() {
        // Arrange
        DishRepository dishRepository = mock(DishRepository.class);
        DishService dishService = new DishService(dishRepository);

        DishRequest dishRequest = new DishRequest("4", "Lasagna", 14.99);

        // Act
        dishService.createDish(dishRequest);

        // Assert
        ArgumentCaptor<Dish> dishCaptor = ArgumentCaptor.forClass(Dish.class);
        verify(dishRepository).save(dishCaptor.capture());

        Dish capturedDish = dishCaptor.getValue();
        assertEquals("4", capturedDish.getDishNr());
        assertEquals("Lasagna", capturedDish.getDishName());
        assertEquals(14.99, capturedDish.getPrice());
        // Add additional assertions if needed
        // For example, assertEquals for each field in dishRequest and capturedDish
    }

    @Test
    void getAllDishes_shouldReturnDishResponses() {
        // Arrange
        DishRepository dishRepository = mock(DishRepository.class);
        DishService dishService = new DishService(dishRepository);

        List<Dish> mockDishes = new ArrayList<>(Arrays.asList(
                Dish.builder().id("1L").dishNr("1").dishName("Pizza Margherita").price(10.99).build(),
                Dish.builder().id("2L").dishNr("2").dishName("Spaghetti Carbonara").price(12.99).build(),
                Dish.builder().id("3L").dishNr("3").dishName("Tiramisu").price(6.99).build()
        ));

        when(dishRepository.findAll()).thenReturn(mockDishes);

        // Act
        List<DishResponse> dishResponses = dishService.getAllDishes();

        // Assert
        assertNotNull(dishResponses);
        assertEquals(3, dishResponses.size());

        // Add more specific assertions if needed
        assertEquals("1", dishResponses.get(0).getDishNr());
        assertEquals("Pizza Margherita", dishResponses.get(0).getDishName());
        assertEquals(10.99, dishResponses.get(0).getPrice());
        // Assert
        // Add assertions to verify that the mapping from Dish to DishResponse is correct
        // You can compare the expected dishResponses with the actual dishResponses
    }

    @Test
    void updateDish_shouldUpdateExistingDish() {
        // Arrange
        DishRepository dishRepository = mock(DishRepository.class);
        DishService dishService = new DishService(dishRepository);

        String dishIdToUpdate = "2";
        DishRequest updatedDishRequest = new DishRequest("2", "Spaghetti Bolognese", 14.99);

        Dish existingDish = new Dish("2L", "2", "Spaghetti Carbonara", 12.99);

        // Mock the behavior of the repository
        when(dishRepository.findById(dishIdToUpdate)).thenReturn(Optional.of(existingDish));

        // Act
        dishService.updateDish(updatedDishRequest, dishIdToUpdate);

        // Assert
        verify(dishRepository).findById(dishIdToUpdate); // Verify that findById was called with the correct ID

        // Use ArgumentCaptor to capture the updated dish and verify its values
        ArgumentCaptor<Dish> dishCaptor = ArgumentCaptor.forClass(Dish.class);
        verify(dishRepository).save(dishCaptor.capture());

        Dish capturedDish = dishCaptor.getValue();
        assertNotNull(capturedDish);

        // Verify that the updated dish has the expected values
        assertEquals(updatedDishRequest.getDishNr(), capturedDish.getDishNr());
        assertEquals(updatedDishRequest.getDishName(), capturedDish.getDishName());
        assertEquals(updatedDishRequest.getPrice(), capturedDish.getPrice(), 0.001); // Adjust the delta based on your requirements
    }

    @Test
    void deleteDish_shouldRemoveDish() {
        // Arrange
        DishRepository dishRepository = mock(DishRepository.class);
        DishService dishService = new DishService(dishRepository);

        String dishIdToDelete = "2";

        // Act
        dishService.deleteDish(dishIdToDelete);

        // Assert
        verify(dishRepository).deleteById(dishIdToDelete);
        // Add assertions to verify that the dish has been removed correctly
        // You may want to use ArgumentCaptor to capture the removed dish and verify its values
    }

}
