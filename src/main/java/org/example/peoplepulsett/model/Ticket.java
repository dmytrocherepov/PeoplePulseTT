package org.example.peoplepulsett.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Ticket {
    private Long id;
    private boolean isAvailable;
}
