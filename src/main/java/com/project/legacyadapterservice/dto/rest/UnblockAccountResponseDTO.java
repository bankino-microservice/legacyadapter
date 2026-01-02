package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class UnblockAccountResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private CompteSoapInfoDTO compte;
}
