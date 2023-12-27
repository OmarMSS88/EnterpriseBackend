package fact.it.dishservice.service;

import fact.it.dishservice.repository.DishRepository;
import fact.it.dishservice.dto.DishRequest;
import fact.it.dishservice.dto.DishResponse;
import fact.it.dishservice.model.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;

    private List<DishRequest> dishes = new ArrayList<>(Arrays.asList(
            new DishRequest("1",  "Pizza Margherita", 10.99),
            new DishRequest("2", "Spaghetti Carbonara", 12.99),
            new DishRequest("3", "Tiramisu", 6.99)
    ));

    public void createDish(DishRequest dishRequest){
        Dish dish = Dish.builder()
                .dishNr(dishRequest.getDishNr())
                .dishName(dishRequest.getDishName())
                .price(dishRequest.getPrice())
                .build();

        dishRepository.save(dish);
    }

    public List<DishResponse> getAllDishes(){
        List<Dish> dishes = new ArrayList<>(Arrays.asList(
                new Dish("1", "1",  "Pizza Margherita", 10.99),
                new Dish("2", "2", "Spaghetti Carbonara", 12.99),
                new Dish("3", "3", "Tiramisu", 6.99)
        ));

//        dishes = dishRepository.findAll();

        return dishes.stream().map(this::mapToDishResponse).toList();
    }

    public void updateDish(DishRequest dishRequest, String id) {
        Dish dishToUpdate = dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dish not found"));

        dishToUpdate.setDishNr(dishRequest.getDishNr());
        dishToUpdate.setDishName(dishRequest.getDishName());
        dishToUpdate.setPrice(dishRequest.getPrice());

        dishRepository.save(dishToUpdate);
    }

    public void deleteDish(String id) {
        dishRepository.deleteById(id);
    }

    private DishResponse mapToDishResponse(Dish dish){
        return DishResponse.builder()
                .id(dish.getId())
                .dishNr(dish.getDishNr())
                .dishName(dish.getDishName())
                .price(dish.getPrice())
                .build();
    }
}
