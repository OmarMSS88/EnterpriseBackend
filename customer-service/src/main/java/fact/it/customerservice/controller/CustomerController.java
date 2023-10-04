package fact.it.customerservice.controller;

import fact.it.customerservice.dto.CustomerRequest;
import fact.it.customerservice.dto.CustomerResponse;
import fact.it.customerservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createCustomer
            (@RequestBody CustomerRequest customerRequest){
        orderService.createOrder(customerRequest);
    }



    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> getAllCustomers(){
        return orderService.getAllDishes();
    }
}
