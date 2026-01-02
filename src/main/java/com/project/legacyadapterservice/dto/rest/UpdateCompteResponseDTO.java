package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class UpdateCompteResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private CompteSoapInfoDTO compte;
}
