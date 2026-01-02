package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class ChangeDotationStatusRequestDTO {
    private Long accountId;
    private Boolean autorisePaiementEnLigne;
}
