package org.example.peoplepulsett.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.peoplepulsett.service.TicketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Ticket management", description = "endpoint for tickets")
@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/{ticketId}")
    @Operation(
            summary = "Checks the ticket ",
            description = "Checks whether a ticket is valid or not"
    )
    public Boolean checkTicket(@PathVariable @Positive Long ticketId) {
        return ticketService.isValidTicket(ticketId);
    }
}

