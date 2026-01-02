package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class GetAccountStatementRequestDTO {
    private Long compteId;
    private String startDate;
    private String endDate;
}
