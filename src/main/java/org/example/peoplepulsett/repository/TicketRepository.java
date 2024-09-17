package org.example.peoplepulsett.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TicketRepository {
    private final Map<Long, Boolean> tickets;

    public TicketRepository() {
        tickets = new HashMap<>();
        tickets.put(1L, true);
        tickets.put(2L, false);
        tickets.put(3L, false);
    }

    public boolean isAvailable(Long ticketId) {
        return tickets.getOrDefault(ticketId , false);
    }
}
