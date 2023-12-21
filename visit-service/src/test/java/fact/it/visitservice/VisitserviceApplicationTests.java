package fact.it.visitservice;

import fact.it.visitservice.dto.*;
import fact.it.visitservice.model.Visit;
import fact.it.visitservice.model.VisitorItem;
import fact.it.visitservice.repository.VisitRepository;
import fact.it.visitservice.service.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static javax.management.Query.eq;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = VisitserviceApplicationTests.class)
@ExtendWith(MockitoExtension.class)
class VisitserviceApplicationTests {

    @Mock
    private VisitRepository visitRepository;

    @Mock
    private WebClient webClient;

    @InjectMocks
    private VisitService visitService;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void placeVisit_shouldCreateVisitAndSaveToRepository() {
        // Arrange

        VisitorItem visitorItem1 = new VisitorItem(1L, "2C", LocalDate.now(), false, 4, 5);

        VisitRequest visitRequest = new VisitRequest();
        visitRequest.setVisitorItemDtoList(new ArrayList<>());
        // populate orderRequest with test data
        VisitorItemDto visitorItemDto = new VisitorItemDto();
        visitorItemDto.setId(1L);
        visitorItemDto.setItemCode("2ZE");
        visitorItemDto.setDate(LocalDate.now());
        visitorItemDto.setPaid(false);
        visitorItemDto.setQuantity(4);

        visitRequest.setVisitorItemDtoList(Collections.singletonList(visitorItemDto));

        TableResponse tableResponse = new TableResponse();
        // populate inventoryResponse with test data
        tableResponse.setId("1");
        tableResponse.setTableNr("2S");
        tableResponse.setSeatAmount(5);

        DishResponse dishResponse = new DishResponse();
        // populate productResponse with test data
        dishResponse.setId("1");
        dishResponse.setDishName("Macaroni and Cheese");
        dishResponse.setPrice(14.99);

        WaiterResponse waiterResponse = new WaiterResponse();
        waiterResponse.setId("1");
        waiterResponse.setWaiterCode("2JB");
        waiterResponse.setName("Justin Bieber");
        waiterResponse.setReceivedTip(false);

        Visit visit = new Visit();
        visit.setId("1L");
        visit.setVisitNumber("2SA");
        visit.setVisitorItemList(Collections.singletonList(visitorItem1));

        when(visitRepository.save(any(Visit.class))).thenReturn(visit);

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), Optional.ofNullable(any()))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(TableResponse[].class)).thenReturn(Mono.just(new TableResponse[]{tableResponse}));
        when(responseSpec.bodyToMono(DishResponse[].class)).thenReturn(Mono.just(new DishResponse[]{dishResponse}));
        when(responseSpec.bodyToMono(WaiterResponse[].class)).thenReturn(Mono.just(new WaiterResponse[]{waiterResponse}));

        // Act
        boolean result = visitService.placeVisit(visitRequest);

        // Assert
        assertTrue(result);

        verify(visitRepository, times(1)).save(any(Visit.class));
    }


    @Test
    void getAllVisits_shouldReturnListOfVisitResponses() {
        // Arrange
        VisitRepository visitRepository = mock(VisitRepository.class);
        WebClient webClient = mock(WebClient.class);
        VisitService visitService = new VisitService(visitRepository, webClient);

        VisitorItem visitorItem1 = new VisitorItem(1L, "2C", LocalDate.now(), false, 4, 5);
        VisitorItem visitorItem2 = new VisitorItem(2L, "5CA", LocalDate.now(), true, 16, 20);

        List<Visit> mockvisits = new ArrayList<>(Arrays.asList(
                Visit.builder().id("1").visitNumber("1").visitorItemList(Arrays.asList(visitorItem1)).build(),
                Visit.builder().id("2").visitNumber("2").visitorItemList(Arrays.asList(visitorItem2)).build()
        ));


        when(visitRepository.findAll()).thenReturn(mockvisits);

        // Act
        List<VisitResponse> visitResponses = visitService.getAllVisits();


        // Assert

        verify(visitRepository, times(1)).findAll();
        assertEquals(2, visitResponses.size());

        // Perform assertions on visitResponses based on the expected behavior
        // For example:
        assertNotNull(visitResponses);
        assertEquals(2, visitResponses.size());
        assertEquals("1", visitResponses.get(0).getVisitNumber());

        // Add more specific assertions if needed
        verify(visitRepository, times(1)).findAll();
    }


    @Test
    void updateVisit_shouldUpdateVisitInRepository() {
        // Arrange
        VisitRepository visitRepository = mock(VisitRepository.class);
        WebClient webClient = mock(WebClient.class);
        VisitService visitService = new VisitService(visitRepository, webClient);

        List<VisitorItemDto> visitorItems = Arrays.asList(
                new VisitorItemDto(1L, "2C", LocalDate.now(), false, 5),
                new VisitorItemDto(2L, "5CA", LocalDate.now(), true, 20)
        );

        String visitIdToUpdate = "2";
        VisitRequest updatedVisitRequest = new VisitRequest(visitorItems);

        VisitorItem visitorItem = new VisitorItem(2L, "3ZQ", LocalDate.now(), false, 10, 20);

        Visit existingVisit = new Visit("2L", "2", Arrays.asList(visitorItem));

        // Mock the behavior of the repository
        when(visitRepository.findById(visitIdToUpdate)).thenReturn(Optional.of(existingVisit));

        // Act
        visitService.updateVisit(visitorItems.get(0), visitIdToUpdate);

        // Assert
        verify(visitRepository).findById(visitIdToUpdate); // Verify that findById was called with the correct ID

        // Use ArgumentCaptor to capture the updated visit and verify its values
        ArgumentCaptor<Visit> visitCaptor = ArgumentCaptor.forClass(Visit.class);
        verify(visitRepository).save(visitCaptor.capture());

        Visit capturedVisit = visitCaptor.getValue();
        assertNotNull(capturedVisit);

        // Verify that the updated visit has the expected values
        List<VisitorItemDto> expectedVisitorItems = updatedVisitRequest.getVisitorItemDtoList();
        List<VisitorItem> actualVisitorItems = capturedVisit.getVisitorItemList();

        // Convert VisitorItem instances to VisitorItemDto instances for comparison
        List<VisitorItemDto> actualVisitorItemsDto = actualVisitorItems.stream()
                .map(visitorItemEntity -> new VisitorItemDto(
                        visitorItemEntity.getId(),
                        visitorItemEntity.getItemCode(),
                        visitorItemEntity.getDate(),
                        visitorItemEntity.isPaid(),
                        visitorItemEntity.getQuantity()
                ))
                .collect(Collectors.toList());


        // Assuming expectedVisitorItemDto is the expected value
        VisitorItemDto expectedVisitorItemDto = new VisitorItemDto(2L, "3ZQ", LocalDate.now(), false, 10);

        // Extract the single actual VisitorItemDto from the list
        VisitorItemDto actualVisitorItemDto = actualVisitorItemsDto.get(0);

        // Compare the single expected VisitorItemDto to the single actual VisitorItemDto
        assertEquals(expectedVisitorItemDto, actualVisitorItemDto);
    }



    @Test
    void deleteVisit_shouldDeleteVisitFromRepository() {
        // Arrange
        VisitRepository visitRepository = mock(VisitRepository.class);
        WebClient webClient = mock(WebClient.class);
        VisitService visitService = new VisitService(visitRepository, webClient);

        String visitIdToDelete = "2";

        // Act
        visitService.deleteVisit(visitIdToDelete);

        // Assert
        verify(visitRepository).deleteById(visitIdToDelete);
        // Add assertions to verify that the dish has been removed correctly
        // You may want to use ArgumentCaptor to capture the removed dish and verify its values
    }

    // ...

    private void assertVisitResponseEqualsVisit(Visit visit, VisitResponse visitResponse) {
        assertEquals(visit.getVisitNumber(), visitResponse.getVisitNumber());
        // Add more assertions based on your mapping logic and expected behavior
    }

}
