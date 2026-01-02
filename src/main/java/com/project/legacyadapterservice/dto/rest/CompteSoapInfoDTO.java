package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class CompteSoapInfoDTO {
    private Long id;
    private Long clientId;
    private String rib;
    private Double solde;
    private String status;
    private String type;
    private Boolean autorisePaiementEnLigne;
    private Double tauxInterets;
    private Double balanceQ1;
    private Double balanceQ2;
    private Double interests;
}
