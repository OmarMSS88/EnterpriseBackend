package fact.it.customerservice.service;

import fact.it.customerservice.dto.CustomerRequest;
import fact.it.customerservice.dto.CustomerResponse;
import fact.it.customerservice.model.Customer;
import fact.it.customerservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void createCustomer(CustomerRequest customerRequest){
        Customer customer = Customer.builder()
                .customerNr(customerRequest.getCustomerNr())
                .date(customerRequest.getDate())
                .customerAmount(customerRequest.getCustomerAmount())
                .paid(customerRequest.isPaid())
                .build();

        orderRepository.save(customer);
    }

    public List<CustomerResponse> getAllCustomers(){
        List<Customer> customers = orderRepository.findAll();

        return customers.stream().map(this::mapToCustomerResponse).toList();
    }

    private CustomerResponse mapToCustomerResponse(Customer customer){
        return CustomerResponse.builder()
                .id(customer.getId())
                .customerNr(customer.getCustomerNr())
                .date(customer.getDate())
                .customerAmount(customer.getCustomerAmount())
                .paid(customer.isPaid())
                .build();
    }
}
