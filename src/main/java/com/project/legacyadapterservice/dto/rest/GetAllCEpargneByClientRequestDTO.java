package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class GetAllCEpargneByClientRequestDTO {
    private Long clientId;
    private String status;
}
