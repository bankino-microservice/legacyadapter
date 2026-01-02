package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class AccountSummaryInfoDTO {
    private Integer totalAccounts;
    private Double totalBalance;
    private Integer activeAccounts;
    private Integer blockedAccounts;
    private Integer ccourantCount;
    private Integer cepargneCount;
}
