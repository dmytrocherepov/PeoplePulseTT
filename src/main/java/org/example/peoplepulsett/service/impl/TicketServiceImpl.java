package org.example.peoplepulsett.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.peoplepulsett.exception.EntityNotFoundException;
import org.example.peoplepulsett.repository.TicketRepository;
import org.example.peoplepulsett.service.TicketService;
import org.example.peoplepulsett.util.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);
    private final TicketRepository ticketRepository;
    private final Cache cache;

    @Override
    public Boolean isValidTicket(Long id) {
        logger.info("Checking ticket validity for id: {}", id);
        String cacheKey = "ticket_" + id;
        if (cache.getCache().containsKey(cacheKey)) {
            logger.info("Cache hit for key: {}", cacheKey);
            return (Boolean) cache.getCache().get(cacheKey);
        }
        logger.info("Cache miss for key: {}", cacheKey);
        boolean isAvailableTicket = ticketRepository.isAvailable(id);

        if (!isAvailableTicket) {
            cache.put(cacheKey, isAvailableTicket);
            throw new EntityNotFoundException("Ticket with id " + id + " is not available");
        }

        cache.put(cacheKey, isAvailableTicket);
        logger.info("Caching result for key: {} with value: {}", cacheKey, isAvailableTicket);
        return isAvailableTicket;
    }

}
