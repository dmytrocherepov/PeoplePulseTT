package org.example.peoplepulsett.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.peoplepulsett.service.BaggageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Baggage management", description = "endpoint for checking the baggage")
@RestController
@RequestMapping("/baggages")
@RequiredArgsConstructor
public class BaggageController {
    private final BaggageService baggageService;


    @GetMapping("/{baggageId}/{destinationId}")
    @Operation(
            summary = "Check-in creation ",
            description = "Returns true if check-in is success"
    )
    public Boolean check(
            @PathVariable("baggageId") @Positive Long baggageId,
            @PathVariable("destinationId") @Positive Long destinationId
    ) {
        return baggageService.isValidBaggage(baggageId, destinationId);
    }
}
