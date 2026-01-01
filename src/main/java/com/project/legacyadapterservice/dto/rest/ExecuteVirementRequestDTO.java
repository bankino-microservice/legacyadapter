package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class ExecuteVirementRequestDTO {
    private String ribEmetteur;
    private String ribRecepteur;
    private Double montant;
    private Type type;

    // Getters/Setters
}