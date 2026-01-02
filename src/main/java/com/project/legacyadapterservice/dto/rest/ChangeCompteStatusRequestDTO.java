package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class ChangeCompteStatusRequestDTO {
    private Long compteId;
    private String status;
}
