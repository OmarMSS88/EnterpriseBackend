package fact.it.waiterservice.service;

import fact.it.waiterservice.dto.WaiterResponse;
import fact.it.waiterservice.model.Waiter;
import fact.it.waiterservice.repository.WaiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WaiterService {
    private final WaiterRepository waiterRepository;

    public void createWaiter(WaiterResponse waiterResponse){
        Waiter waiter = Waiter.builder()
                .waiterCode(waiterResponse.getWaiterCode())
                .name(waiterResponse.getName())
                .receivedTip(waiterResponse.isReceivedTip())
                .build();

        waiterRepository.save(waiter);
    }

    public List<WaiterResponse> getAllWaiters(){
        List<Waiter> waiters = waiterRepository.findAll();

        return waiters.stream().map(this::mapToWaiterResponse).toList();
    }

    private WaiterResponse mapToWaiterResponse(Waiter waiter){
        return WaiterResponse.builder()
                .id(waiter.getId())
                .waiterCode(waiter.getWaiterCode())
                .name(waiter.getName())
                .receivedTip(waiter.isReceivedTip())
                .build();
    }
}
