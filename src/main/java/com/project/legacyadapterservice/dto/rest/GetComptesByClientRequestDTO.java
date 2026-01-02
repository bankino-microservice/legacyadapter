package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class GetComptesByClientRequestDTO {
    private Long clientId;
    private String type;
    private String status;
}
