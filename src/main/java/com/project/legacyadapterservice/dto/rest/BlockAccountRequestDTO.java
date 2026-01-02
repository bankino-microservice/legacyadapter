package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class BlockAccountRequestDTO {
    private Long compteId;
    private String reason;
}
