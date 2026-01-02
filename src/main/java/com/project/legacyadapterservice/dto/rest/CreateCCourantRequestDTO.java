package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class CreateCCourantRequestDTO {
    private Long clientId;
    private Double solde;
    private String status;
    private Boolean autorisePaiementEnLigne;
}
