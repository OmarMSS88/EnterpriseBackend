package fact.it.waiterservice.service;

import fact.it.waiterservice.dto.WaiterRequest;
import fact.it.waiterservice.dto.WaiterResponse;
import fact.it.waiterservice.model.Waiter;
import fact.it.waiterservice.repository.WaiterRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WaiterService {
    @Autowired
    private final WaiterRepository waiterRepository;

    private List<WaiterRequest> waiters = new ArrayList<>(Arrays.asList(
            new WaiterRequest("1BAC",  "Ben Active Cal", false),
            new WaiterRequest("2JC", "James Cameron", true),
            new WaiterRequest("3HF", "Harry Fields", false)
    ));

    @PostConstruct
    private void postConstruct() {
        Waiter waiterBen = new Waiter("1", "1BAC", "Ben Active Cal", false);
        Waiter waiterJames = new Waiter("2", "2JC", "James Cameron", true);
        Waiter waiterHarry = new Waiter("3", "3HF", "Harry Fields", false);

        waiterRepository.save(waiterBen);
    }

    public void createWaiter(WaiterRequest waiterRequest){
        Waiter waiter = Waiter.builder()
                .waiterCode(waiterRequest.getWaiterCode())
                .name(waiterRequest.getName())
                .receivedTip(waiterRequest.isReceivedTip())
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

    public void updateWaiter(WaiterRequest waiterRequest, String id) {
        Waiter waiterToUpdate = waiterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Waiter not found"));

        waiterToUpdate.setWaiterCode(waiterRequest.getWaiterCode());
        waiterToUpdate.setName(waiterRequest.getName());
        waiterToUpdate.setReceivedTip(waiterRequest.isReceivedTip());

        waiterRepository.save(waiterToUpdate);
    }

    public void deleteWaiter(String id) {
        waiterRepository.deleteById(id);
    }
}
