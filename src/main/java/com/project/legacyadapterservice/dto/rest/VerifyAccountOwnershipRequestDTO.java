package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class VerifyAccountOwnershipRequestDTO {
    private Long compteId;
    private Long clientId;
}
