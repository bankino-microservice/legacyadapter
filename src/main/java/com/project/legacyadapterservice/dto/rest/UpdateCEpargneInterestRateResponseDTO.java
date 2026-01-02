package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class UpdateCEpargneInterestRateResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private CompteSoapInfoDTO compte;
}
