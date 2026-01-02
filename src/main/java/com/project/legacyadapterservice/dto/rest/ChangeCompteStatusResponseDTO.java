package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class ChangeCompteStatusResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private CompteSoapInfoDTO compte;
}
