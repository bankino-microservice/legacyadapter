package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class AccountStatementInfoDTO {
    private CompteSoapInfoDTO compte;
    private String startDate;
    private String endDate;
    private Double openingBalance;
    private Double closingBalance;
    private Integer transactionCount;
}
