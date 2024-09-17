package org.example.peoplepulsett.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.peoplepulsett.service.BaggageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/baggages")
@RequiredArgsConstructor
public class BaggageController {
    private final BaggageService baggageService;


    @GetMapping("/check/{baggageId}/{destinationId}")
    public Boolean check(
            @PathVariable("baggageId") @Positive Long baggageId,
            @PathVariable("destinationId") @Positive Long destinationId
    ) {
        return baggageService.isValidBaggage(baggageId, destinationId);
    }
}
