package fact.it.visitservice.controller;

import fact.it.visitservice.dto.VisitRequest;
import fact.it.visitservice.dto.VisitResponse;
import fact.it.visitservice.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visit")
@RequiredArgsConstructor
public class VisitController {
    private final VisitService visitService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String placeOrder(@RequestBody VisitRequest visitRequest){
        boolean result = visitService.placeVisit(visitRequest);
        return (result ? "Visit placed successfully" : "Visit placement failed");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VisitResponse> getAllVisits(){
        return visitService.getAllVisits();
    }
}
