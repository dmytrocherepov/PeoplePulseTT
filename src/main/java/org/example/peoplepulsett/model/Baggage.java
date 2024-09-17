package org.example.peoplepulsett.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class Baggage {
    private Long id;
    private Long destinationId;
}
