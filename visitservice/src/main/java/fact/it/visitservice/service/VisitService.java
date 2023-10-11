package fact.it.visitservice.service;

import fact.it.visitservice.dto.*;
import fact.it.visitservice.model.Visit;
import fact.it.visitservice.model.VisitorItem;
import fact.it.visitservice.repository.VisitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class VisitService {
    private final VisitRepository visitRepository;
    private final WebClient webClient;

    @Value("${tableservice.baseurl}")
    private String tableServiceBaseUrl;

    @Value("${dishservice.baseurl}")
    private String dishServiceBaseUrl;

    @Value("${waiterservice.baseurl}")
    private String waiterServiceBaseUrl;

    public boolean placeVisit(VisitRequest visitRequest){
        Visit visit = new Visit();
        visit.setVisitNumber(UUID.randomUUID().toString());

        List<VisitorItem> visitorItems = visitRequest.getVisitorItemDtoList()
                .stream()
                .map(this::mapToVisitorItem)
                .toList();

        visit.setVisitorItemList(visitorItems);

        TableResponse[] tableResponseArray = webClient.get()
                .uri("http://" + tableServiceBaseUrl + "api/table")
                .retrieve()
                .bodyToMono(TableResponse[].class)
                .block();

        DishResponse[] dishResponseArray = webClient.get()
                .uri("http://" + dishServiceBaseUrl + "api/dish")
                .retrieve()
                .bodyToMono(DishResponse[].class)
                .block();

        WaiterResponse[] waiterResponseArray = webClient.get()
                .uri("http://" + waiterServiceBaseUrl + "api/waiter")
                .retrieve()
                .bodyToMono(WaiterResponse[].class)
                .block();

        visit.getVisitorItemList().stream()
                .map(visitorItem -> {
                    DishResponse dish = Arrays.stream(dishResponseArray)
                            .findFirst()
                            .orElse(null);
                    if (dish != null){
                        visitorItem.setAmount(dish.getPrice());
                    }
                    return visitorItem;
                })
                .collect(Collectors.toList());

        visitRepository.save(visit);
        return true;
    }

    public List<VisitResponse> getAllVisits(){
        List<Visit> visits = visitRepository.findAll();

        return visits.stream()
                .map(visit -> new VisitResponse(
                        visit.getVisitNumber(),
                        mapToVisitorItemDto(visit.getVisitorItemList())
                ))
                .collect(Collectors.toList());
    }

    private List<VisitorItemDto> mapToVisitorItemDto(List<VisitorItem> visitorItems){
        return visitorItems.stream()
                .map(visitorItem -> new VisitorItemDto(
                        visitorItem.getId(),
                        visitorItem.getItemCode(),
                        visitorItem.getDate(),
                        visitorItem.isPaid(),
                        visitorItem.getQuantity()
                ))
                .collect(Collectors.toList());
    }

    private VisitorItem mapToVisitorItem(VisitorItemDto visitorItemDto){
        VisitorItem visitorItem = new VisitorItem();
        visitorItem.setDate(visitorItemDto.getDate());
        visitorItem.setPaid(visitorItemDto.isPaid());
        visitorItem.setQuantity(visitorItem.getQuantity());
        return visitorItem;
    }
}
