package com.project.legacyadapterservice.mapper;

import com.ebank.ebanking2.soap.dto.*;
import com.project.legacyadapterservice.dto.rest.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    // CompteSoapInfo mappings
    CompteSoapInfoDTO toRestDto(CompteSoapInfo soap);

    List<CompteSoapInfoDTO> toRestDtoList(List<CompteSoapInfo> soap);

    // ServiceStatus mapping
    ServiceStatusDTO toRestDto(ServiceStatus soap);

    // GetComptesByStatusType
    GetComptesByStatusTypeResponseDTO toRestDto(GetComptesByStatusTypeResponse soap);

    // GetSolde
    GetSoldeResponseDTO toRestDto(GetSoldeResponse soap);

    // GetCompteById
    GetCompteByIdResponseDTO toRestDto(GetCompteByIdResponse soap);

    // GetComptesByClient
    GetComptesByClientResponseDTO toRestDto(GetComptesByClientResponse soap);

    // ChangeDotationStatus
    ChangeDotationStatusResponseDTO toRestDto(ChangeDotationStatusResponse soap);

    // CreateCCourant
    CreateCCourantResponseDTO toRestDto(CreateCCourantResponse soap);

    // CreateCEpargne
    CreateCEpargneResponseDTO toRestDto(CreateCEpargneResponse soap);

    // GetClientIdByCompteId
    GetClientIdByCompteIdResponseDTO toRestDto(GetClientIdByCompteIdResponse soap);

    // GetClientIdByRib
    GetClientIdByRibResponseDTO toRestDto(GetClientIdByRibResponse soap);

    // UpdateCompte
    UpdateCompteResponseDTO toRestDto(UpdateCompteResponse soap);

    // ChangeCompteStatus
    ChangeCompteStatusResponseDTO toRestDto(ChangeCompteStatusResponse soap);

    // DeleteCompte
    DeleteCompteResponseDTO toRestDto(DeleteCompteResponse soap);

    // ValidateRib
    ValidateRibResponseDTO toRestDto(ValidateRibResponse soap);

    // CheckAccountExists
    CheckAccountExistsResponseDTO toRestDto(CheckAccountExistsResponse soap);

    // GetAccountsByRibs
    GetAccountsByRibsResponseDTO toRestDto(GetAccountsByRibsResponse soap);

    // BlockAccount
    BlockAccountResponseDTO toRestDto(BlockAccountResponse soap);

    // UnblockAccount
    UnblockAccountResponseDTO toRestDto(UnblockAccountResponse soap);

    // GetAccountStatement
    AccountStatementInfoDTO toRestDto(AccountStatementInfo soap);

    GetAccountStatementResponseDTO toRestDto(GetAccountStatementResponse soap);

    // VerifyAccountOwnership
    VerifyAccountOwnershipResponseDTO toRestDto(VerifyAccountOwnershipResponse soap);

    // GetAccountSummary
    AccountSummaryInfoDTO toRestDto(AccountSummaryInfo soap);

    GetAccountSummaryResponseDTO toRestDto(GetAccountSummaryResponse soap);

    // CEpargne Specific Operations

    // GetCEpargneQuarterlyInterests
    GetCEpargneQuarterlyInterestsResponseDTO toRestDto(GetCEpargneQuarterlyInterestsResponse soap);

    // UpdateCEpargneInterestRate
    UpdateCEpargneInterestRateResponseDTO toRestDto(UpdateCEpargneInterestRateResponse soap);

    // ApplyCEpargneInterests
    ApplyCEpargneInterestsResponseDTO toRestDto(ApplyCEpargneInterestsResponse soap);

    // CalculateCEpargneInterests
    CalculateCEpargneInterestsResponseDTO toRestDto(CalculateCEpargneInterestsResponse soap);

    // GetCEpargneById
    GetCEpargneByIdResponseDTO toRestDto(GetCEpargneByIdResponse soap);

    // GetAllCEpargneByClient
    GetAllCEpargneByClientResponseDTO toRestDto(GetAllCEpargneByClientResponse soap);

    // GetAllCEpargneByInterestRate
    GetAllCEpargneByInterestRateResponseDTO toRestDto(GetAllCEpargneByInterestRateResponse soap);
}
