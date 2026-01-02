package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class ChangeDotationStatusResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private Boolean success;
}
