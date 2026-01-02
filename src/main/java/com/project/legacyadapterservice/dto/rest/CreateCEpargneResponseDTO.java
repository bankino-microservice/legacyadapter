package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class CreateCEpargneResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private CompteSoapInfoDTO compte;
}
