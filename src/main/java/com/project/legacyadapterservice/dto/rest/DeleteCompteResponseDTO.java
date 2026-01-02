package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class DeleteCompteResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private Boolean success;
}
