package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class UpdateCompteRequestDTO {
    private Long id;
    private Double solde;
    private String status;
    private Boolean autorisePaiementEnLigne;
    private Double tauxInterets;
}
