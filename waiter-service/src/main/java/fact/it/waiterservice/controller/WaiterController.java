package fact.it.waiterservice.controller;

import fact.it.waiterservice.dto.WaiterRequest;
import fact.it.waiterservice.dto.WaiterResponse;
import fact.it.waiterservice.service.WaiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/waiter/{id}")
    public void updateWaiter(@RequestBody WaiterRequest waiterRequest, @PathVariable String id) {
        waiterService.updateWaiter(waiterRequest, id);
    }

    @DeleteMapping("/waiters/{id}")
    public void deleteWaiter(@PathVariable String id) {
        waiterService.deleteWaiter(id);
    }
}
