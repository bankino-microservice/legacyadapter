package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class ClotureQuinzaineRequestDTO {
    private String rib;
    private Integer year;
    private Integer quaiznine; // Matches XSD naming

    // Getters/Setters
}