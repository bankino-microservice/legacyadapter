package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class CreateCCourantResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private CompteSoapInfoDTO compte;
}
