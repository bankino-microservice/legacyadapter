package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class VerifyAccountOwnershipResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private Boolean isOwner;
}
