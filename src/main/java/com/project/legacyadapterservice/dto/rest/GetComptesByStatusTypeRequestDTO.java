package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class GetComptesByStatusTypeRequestDTO {
    private String type;
    private String status;
}
