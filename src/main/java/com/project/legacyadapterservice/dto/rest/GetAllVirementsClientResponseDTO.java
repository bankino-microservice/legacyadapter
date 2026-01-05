package com.project.legacyadapterservice.dto.rest;

import lombok.Data;
import java.util.List;

@Data
public class GetAllVirementsClientResponseDTO {
    private List<VirementSoapInfoDTO> virements;
}
