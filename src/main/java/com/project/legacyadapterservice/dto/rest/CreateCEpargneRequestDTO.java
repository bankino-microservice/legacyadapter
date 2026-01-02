package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class CreateCEpargneRequestDTO {
    private Long clientId;
    private Double tauxInterets;
    private Double solde;
    private String status;
}
