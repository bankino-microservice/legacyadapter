package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class CalculateCEpargneInterestsRequestDTO {
    private Long compteId;
    private Integer period;
}
