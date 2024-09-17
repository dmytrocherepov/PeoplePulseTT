package org.example.peoplepulsett.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.peoplepulsett.exception.EntityNotFoundException;
import org.example.peoplepulsett.repository.BaggageRepository;
import org.example.peoplepulsett.service.BaggageService;
import org.example.peoplepulsett.util.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaggageServiceImpl implements BaggageService {
    private static final Logger logger = LoggerFactory.getLogger(BaggageServiceImpl.class);
    private final BaggageRepository baggageRepository;
    private final Cache cache;

    @Override
    public Boolean isValidBaggage(Long baggageId, Long destinationId) {
        logger.info("Checking validity for baggageId: {} and destinationId: {}", baggageId, destinationId);
        String cacheKey = "baggage_" + baggageId + destinationId;


        if (cache.getCache().containsKey(cacheKey)) {
            logger.info("Cache hit for key: {}", cacheKey);
            return (Boolean) cache.getCache().get(cacheKey);
        }

        boolean isValidBaggage = baggageRepository.isValidBaggage(baggageId, destinationId);

        if (!isValidBaggage) {
            cache.put(cacheKey, isValidBaggage);
            throw new EntityNotFoundException("No such baggage with id : " + baggageId + " and destination with id : " + destinationId);
        }

        logger.info("Cache miss for key: {}", cacheKey);
        cache.put(cacheKey, isValidBaggage);
        return isValidBaggage;
    }
}
