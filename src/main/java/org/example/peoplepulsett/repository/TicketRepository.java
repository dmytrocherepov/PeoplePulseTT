package org.example.peoplepulsett.repository;

import org.example.peoplepulsett.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TicketRepository {
    private final Map<Long, Ticket> tickets;

    public TicketRepository() {
        tickets = new HashMap<>();
        tickets.put(1L, new Ticket(1L, true));
        tickets.put(2L, new Ticket(2L, false));
        tickets.put(3L, new Ticket(3L, true));
        tickets.put(4L, new Ticket(6L, false));
    }

    public boolean isAvailable(Long ticketId) {
        for (Ticket ticket : tickets.values()) {
            if (ticket.getId().equals(ticketId) && ticket.isAvailable()) {
                return true;
            }
        }
        return false;
    }
}
