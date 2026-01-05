package com.project.legacyadapterservice.dto.rest;

import lombok.Data;
import java.util.List;

@Data
public class GetVirementsEmisResponseDTO {
    private List<VirementSoapInfoDTO> virements;
}
