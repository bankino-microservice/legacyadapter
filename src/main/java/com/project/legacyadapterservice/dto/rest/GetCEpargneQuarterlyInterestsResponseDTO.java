package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class GetCEpargneQuarterlyInterestsResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private Double balanceQ1;
    private Double balanceQ2;
    private Double interests;
    private Double tauxInterets;
}
