package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class GetCEpargneByIdResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private CompteSoapInfoDTO compte;
}
