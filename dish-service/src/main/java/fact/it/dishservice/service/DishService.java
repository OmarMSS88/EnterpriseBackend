package fact.it.dishservice.service;

import fact.it.dishservice.repository.DishRepository;
import fact.it.dishservice.dto.DishRequest;
import fact.it.dishservice.dto.DishResponse;
import fact.it.dishservice.model.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;

    public void createDish(DishRequest dishRequest){
        Dish dish = Dish.builder()
                .dishNr(dishRequest.getDishNr())
                .dishName(dishRequest.getDishName())
                .price(dishRequest.getPrice())
                .build();

        dishRepository.save(dish);
    }

    public List<DishResponse> getAllDishes(){
        List<Dish> dishes = dishRepository.findAll();

        return dishes.stream().map(this::mapToDishResponse).toList();
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