package fact.it.visitservice.service;

import fact.it.visitservice.dto.*;
import fact.it.visitservice.model.Visit;
import fact.it.visitservice.model.VisitorItem;
import fact.it.visitservice.repository.VisitRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class VisitService {
    @Autowired
    private final VisitRepository visitRepository;
    private final WebClient webClient;

    @Value("${tableservice.baseurl}")
    private String tableServiceBaseUrl;

    @Value("${dishservice.baseurl}")
    private String dishServiceBaseUrl;

    @Value("${waiterservice.baseurl}")
    private String waiterServiceBaseUrl;

    @PostConstruct
    private void postConstruct() {
        List<VisitorItem> visitorItems = Arrays.asList(
                new VisitorItem(1, "2AS", LocalDate.now(), false, 5, 10)
        );
        VisitorItem visitorItem = new VisitorItem(1, "2AS", LocalDate.now(), false, 5, 10);
        Visit visit = new Visit(2L, "SA10", Arrays.asList(visitorItem));
        visitRepository.save(visit);
    }
    

    public boolean placeVisit(VisitRequest visitRequest){
        Visit visit = new Visit();
        visit.setVisitNumber(UUID.randomUUID().toString());

        List<VisitorItem> visitorItems = visitRequest.getVisitorItemDtoList()
                .stream()
                .map(this::mapToVisitorItem)
                .toList();

        visit.setVisitorItemList(visitorItems);

        // Fetch data from external services
        TableResponse[] tableResponseArray = webClient.get()
                .uri("http://" + tableServiceBaseUrl + "/api/table")  // Adjust the URI according to your API
                .retrieve()
                .bodyToMono(TableResponse[].class)
                .block();

        DishResponse[] dishResponses = webClient.get()
                .uri("http://" + dishServiceBaseUrl + "/api/dish")  // Adjust the URI according to your API
                .retrieve()
                .bodyToMono(DishResponse[].class)
                .block();

        WaiterResponse[] waiterResponses = webClient.get()
                .uri("http://" + waiterServiceBaseUrl + "/api/waiter")  // Adjust the URI according to your API
                .retrieve()
                .bodyToMono(WaiterResponse[].class)
                .block();

        visit.getVisitorItemList().stream()
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

    public void updateVisit(VisitorItemDto visitRequest, String id) {
        Visit visitToUpdate = visitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visit not found"));

        visitToUpdate.setVisitNumber(visitRequest.getId().toString());

        visitRepository.save(visitToUpdate);
    }

    public void deleteVisit(String id) {
        visitRepository.deleteById(id);
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
