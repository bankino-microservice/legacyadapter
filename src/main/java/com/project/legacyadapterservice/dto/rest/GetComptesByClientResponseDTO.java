package com.project.legacyadapterservice.dto.rest;

import lombok.Data;
import java.util.List;

@Data
public class GetComptesByClientResponseDTO {
    private ServiceStatusDTO serviceStatus;
    private List<CompteSoapInfoDTO> comptes;
}
