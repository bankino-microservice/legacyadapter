package com.project.legacyadapterservice.mapper;

import com.ebank.ebanking2.soap.dto.*;
import com.project.legacyadapterservice.dto.rest.*;
import com.project.legacyadapterservice.dto.rest.CEpargneResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SavingsMapper {

    // Maps both deposit and withdrawal requests to the same REST DTO
    DepotRetraitCEpargneRequestDTO toRestDto(DepotCEpargneRequest soap);
    DepotRetraitCEpargneRequestDTO toRestDto(RetraitCEpargneRequest soap);

    // Response mapping
    CEpargneResponseDTO toRestDto(com.ebank.ebanking2.soap.dto.CEpargneResponseDTO soap);

    // Reverse mappings

    DepotCEpargneRequest toSoapDepot(DepotRetraitCEpargneRequestDTO rest);
    RetraitCEpargneRequest toSoapRetrait(DepotRetraitCEpargneRequestDTO rest);
}