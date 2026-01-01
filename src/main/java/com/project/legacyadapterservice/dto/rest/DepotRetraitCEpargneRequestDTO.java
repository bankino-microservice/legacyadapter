package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class DepotRetraitCEpargneRequestDTO {
    private String Epargnerib;
    private String Courantrib;
    private Double montant;
    private Integer year;
    private Integer month;
    private Integer day;

    // Getters/Setters
}
