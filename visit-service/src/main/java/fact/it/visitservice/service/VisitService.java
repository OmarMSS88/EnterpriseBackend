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

import java.time.LocalDate;
import java.util.*;
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

    private List<VisitorItemDto> visits = new ArrayList<>(Arrays.asList(
            new VisitorItemDto(1L, "Test", LocalDate.now(), false, 4),
            new VisitorItemDto(2L, "John Snow", LocalDate.now(), true, 8),
            new VisitorItemDto(3L, "Internet Explorer", LocalDate.now(), false, 0)
    ));

    public boolean placeVisit(VisitRequest visitRequest){
        Visit visit = new Visit();
        visit.setVisitNumber(UUID.randomUUID().toString());

        List<VisitorItem> visitorItems = visitRequest.getVisitorItemDtoList()
                .stream()
                .map(this::mapToVisitorItem)
                .toList();

        visit.setVisitorItemList(visitorItems);

        // Fetch data from external services
        TableResponse[] tableResponses = webClient.get()
                .uri("/tables")  // Adjust the URI according to your API
                .retrieve()
                .bodyToMono(TableResponse[].class)
                .block();

        DishResponse[] dishResponses = webClient.get()
                .uri("/dishes")  // Adjust the URI according to your API
                .retrieve()
                .bodyToMono(DishResponse[].class)
                .block();

        WaiterResponse[] waiterResponses = webClient.get()
                .uri("/waiters")  // Adjust the URI according to your API
                .retrieve()
                .bodyToMono(WaiterResponse[].class)
                .block();
        

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
                .orElseThrow(() -> new RuntimeException("Dish not found"));

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
