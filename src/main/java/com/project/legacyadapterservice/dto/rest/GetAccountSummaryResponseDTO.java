package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class GetAccountSummaryResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private AccountSummaryInfoDTO summary;
}
