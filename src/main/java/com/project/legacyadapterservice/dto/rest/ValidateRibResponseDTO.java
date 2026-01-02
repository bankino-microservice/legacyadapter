package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class ValidateRibResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private Boolean isValid;
    private String message;
}
