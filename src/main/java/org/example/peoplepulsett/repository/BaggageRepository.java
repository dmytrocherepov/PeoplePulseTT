package org.example.peoplepulsett.repository;

import org.example.peoplepulsett.model.Baggage;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BaggageRepository {
    private final Map<Long, Baggage> baggages;

    public BaggageRepository() {
        baggages = new HashMap<>();
        baggages.put(1L, new Baggage(1L , 1L) );
        baggages.put(2L, new Baggage(2L , 3L));
        baggages.put(3L, new Baggage(4L , 2L));
    }

    public boolean isValidBaggage(Long baggageId, Long destinationId) {
        for (Baggage baggage : baggages.values()) {
            if (baggage.getId().equals(baggageId)) {
                return baggage.getDestinationId().equals(destinationId);
            }
        }
        return false;
    }
}
