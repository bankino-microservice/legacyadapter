package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class ApplyCEpargneInterestsResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private CompteSoapInfoDTO compte;
    private Double interestsApplied;
    private Double newBalance;
}
