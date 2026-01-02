package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class GetSoldeResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private Double solde;
}
