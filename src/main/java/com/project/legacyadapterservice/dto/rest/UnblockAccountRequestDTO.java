package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class UnblockAccountRequestDTO {
    private Long compteId;
    private String reason;
}
