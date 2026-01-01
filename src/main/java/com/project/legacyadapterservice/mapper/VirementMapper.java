package com.project.legacyadapterservice.mapper;

import com.ebank.ebanking2.soap.dto.*;
import com.project.legacyadapterservice.dto.rest.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VirementMapper {
    com.ebank.ebanking2.soap.dto.Type toSoapType(com.project.legacyadapterservice.dto.rest.Type restType);
    @Mapping(source = "type", target = "type")
    ExecuteVirementRequestDTO toRestDto(ExecuteVirementRequest soap);

    ExecuteVirementResponseDTO toRestDto(ExecuteVirementResponse soap);

    VirementSoapInfoDTO toRestDto(VirementSoapInfo soap);

    ServiceStatusDTO toRestDto(ServiceStatus soap);

    // Reverse for calling SOAP Backend
    ExecuteVirementRequest toSoapDto(ExecuteVirementRequestDTO rest);
}