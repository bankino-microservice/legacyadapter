package com.project.legacyadapterservice.mapper;

import com.ebank.ebanking2.soap.dto.*;
import com.project.legacyadapterservice.dto.rest.*;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClosingMapper {

    @Mapping(source = "quaiznine", target = "quaiznine")
    ClotureQuinzaineRequestDTO toRestDto(CloturequainzineRequest soap);

    CalculateAnnualeRequestDTO toRestDto(CalculateAnnuealeRequest soap);

    ClotureAnnuelleResponseDTO toRestDto(ClotureAnnuelleResponse soap);

    // Reverse mappings
    CloturequainzineRequest toSoapDto(ClotureQuinzaineRequestDTO rest);
    CalculateAnnuealeRequest toSoapDto(CalculateAnnualeRequestDTO rest);
}