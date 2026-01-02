package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class GetClientIdByRibResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private Long clientId;
}
