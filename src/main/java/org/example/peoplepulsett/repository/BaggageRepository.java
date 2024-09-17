package org.example.peoplepulsett.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BaggageRepository {
    private Map<Long, Long> baggages;

    public BaggageRepository() {
        baggages = new HashMap<>();
        baggages.put(1L, 2L);
        baggages.put(2L, 3L);
        baggages.put(3L, 4L);
    }

    public boolean isValidBaggage(Long baggageId, Long destinationId) {
        return baggages.containsKey(baggageId) && baggages.get(baggageId).equals(destinationId);
    }
}
