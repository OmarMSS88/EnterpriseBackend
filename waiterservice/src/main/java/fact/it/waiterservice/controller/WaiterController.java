package fact.it.waiterservice.controller;

import fact.it.waiterservice.dto.WaiterResponse;
import fact.it.waiterservice.service.WaiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/waiter")
@RequiredArgsConstructor
public class WaiterController {
    private final WaiterService waiterService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<WaiterResponse> getAllWaiters(){
        return waiterService.getAllWaiters();
    }
}
