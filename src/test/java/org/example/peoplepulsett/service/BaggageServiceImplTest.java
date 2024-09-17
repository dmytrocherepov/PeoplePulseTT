package org.example.peoplepulsett.service;

import org.example.peoplepulsett.exception.EntityNotFoundException;
import org.example.peoplepulsett.repository.BaggageRepository;
import org.example.peoplepulsett.service.impl.BaggageServiceImpl;
import org.example.peoplepulsett.util.Cache;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BaggageServiceImplTest {

    @Mock
    private BaggageRepository baggageRepository;

    @Mock
    private Cache cache;

    @InjectMocks
    private BaggageServiceImpl baggageService;

    @Test
    void testIsValidBaggageWithValidBaggage() {
        Long baggageId = 1L;
        Long destinationId = 100L;
        String cacheKey = "baggage_" + baggageId + destinationId;

        when(baggageRepository.isValidBaggage(baggageId, destinationId)).thenReturn(true);
        when(cache.getCache()).thenReturn(new HashMap<>());

        Boolean result = baggageService.isValidBaggage(baggageId, destinationId);

        assertTrue(result);
    }

    @Test
    void testIsValidBaggageWithInvalidBaggage() {
        Long baggageId = 2L;
        Long destinationId = 200L;
        String cacheKey = "baggage_" + baggageId + destinationId;

        when(baggageRepository.isValidBaggage(baggageId, destinationId)).thenReturn(false);
        when(cache.getCache()).thenReturn(new HashMap<>());

        assertThatThrownBy(() -> baggageService.isValidBaggage(baggageId, destinationId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("No such baggage with id : " + baggageId + " and destination with id : " + destinationId);
    }

}
