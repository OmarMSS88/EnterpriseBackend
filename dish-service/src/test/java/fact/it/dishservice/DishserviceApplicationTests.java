package fact.it.dishservice;

import fact.it.dishservice.dto.DishResponse;
import fact.it.dishservice.model.Dish;
import fact.it.dishservice.repository.DishRepository;
import fact.it.dishservice.service.DishService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class DishserviceApplicationTests {

    @Mock
    private DishRepository dishRepository;

    @InjectMocks
    private DishService dishService;

    private List<Dish> dishes = new ArrayList<>(Arrays.asList(
            Dish.builder().id("1L").dishNr("1").dishName("Pizza Margherita").price(10.99).build(),
            Dish.builder().id("2L").dishNr("2").dishName("Spaghetti Carbonara").price(12.99).build(),
            Dish.builder().id("3L").dishNr("3").dishName("Tiramisu").price(6.99).build()
    ));

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllDishes() {
        when(dishRepository.findAll()).thenReturn(dishes);

        List<DishResponse> dishResponses = dishService.getAllDishes();

        assertEquals(dishes.size(), dishResponses.size());
        assertEquals(dishes.get(0).getDishName(), dishResponses.get(0).getDishName());
        assertEquals(dishes.get(1).getDishName(), dishResponses.get(1).getDishName());
        assertEquals(dishes.get(2).getDishName(), dishResponses.get(2).getDishName());
    }

}
