package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class CalculateCEpargneInterestsResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private Double calculatedInterests;
    private Double currentBalance;
    private Double tauxInterets;
    private Double projectedBalance;
}
