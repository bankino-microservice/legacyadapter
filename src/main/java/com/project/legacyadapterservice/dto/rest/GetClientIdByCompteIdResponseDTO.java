package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class GetClientIdByCompteIdResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private Long clientId;
}
