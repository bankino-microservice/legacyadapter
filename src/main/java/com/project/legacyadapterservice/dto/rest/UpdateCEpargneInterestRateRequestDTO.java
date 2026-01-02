package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class UpdateCEpargneInterestRateRequestDTO {
    private Long compteId;
    private Double newTauxInterets;
}
