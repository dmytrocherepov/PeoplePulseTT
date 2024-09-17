package org.example.peoplepulsett.service;

import org.example.peoplepulsett.exception.EntityNotFoundException;
import org.example.peoplepulsett.repository.TicketRepository;
import org.example.peoplepulsett.service.impl.TicketServiceImpl;
import org.example.peoplepulsett.util.Cache;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Description;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TicketServiceImplTest {
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private Cache cache;
    @InjectMocks
    private TicketServiceImpl ticketService;


    @Test
    @Description("Check that result is true with valid ticketId")
    public void isValidTicket_ValidData() {
        Long ticketId = 1L;
        when(ticketRepository.isAvailable(ticketId)).thenReturn(true);
        Boolean result = ticketService.isValidTicket(ticketId);
        assertTrue(result);
        verify(ticketRepository, times(1)).isAvailable(ticketId);
    }

    @Test
    void isValidTicket_invalidData_ThrowsException() {
        Long ticketId = 1L;
        when(ticketRepository.isAvailable(ticketId)).thenReturn(false);
        assertThatThrownBy(() -> ticketService.isValidTicket(ticketId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Ticket with id " + ticketId + " is not available");
    }
}
