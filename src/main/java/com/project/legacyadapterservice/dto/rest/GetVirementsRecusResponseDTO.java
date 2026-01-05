package com.project.legacyadapterservice.dto.rest;

import lombok.Data;
import java.util.List;

@Data
public class GetVirementsRecusResponseDTO {
    private List<VirementSoapInfoDTO> virements;
}
