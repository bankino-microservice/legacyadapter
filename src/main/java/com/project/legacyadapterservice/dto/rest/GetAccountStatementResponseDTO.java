package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class GetAccountStatementResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private AccountStatementInfoDTO statement;
}
