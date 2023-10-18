package fact.it.dishservice.controller;

import fact.it.dishservice.service.DishService;
import fact.it.dishservice.dto.DishRequest;
import fact.it.dishservice.dto.DishResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dish")
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createDish
            (@RequestBody DishRequest dishRequest){
        dishService.createDish(dishRequest);
    }



    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<DishResponse> getAllDishess(){
        return dishService.getAllDishes();
    }
}
