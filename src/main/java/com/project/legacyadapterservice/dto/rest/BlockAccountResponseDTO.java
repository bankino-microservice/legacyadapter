package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class BlockAccountResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private CompteSoapInfoDTO compte;
}
