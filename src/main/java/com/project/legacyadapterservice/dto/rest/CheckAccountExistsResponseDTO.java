package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class CheckAccountExistsResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private Boolean exists;
    private Long compteId;
    private String accountType;
}
