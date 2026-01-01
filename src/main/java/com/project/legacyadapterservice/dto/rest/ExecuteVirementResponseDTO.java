package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class ExecuteVirementResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private VirementSoapInfoDTO virementInfo;

    // Getters/Setters
}
