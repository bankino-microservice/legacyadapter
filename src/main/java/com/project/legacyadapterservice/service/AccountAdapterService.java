package com.project.legacyadapterservice.service;

import com.ebank.ebanking2.soap.dto.*;
import com.project.legacyadapterservice.dto.rest.*;
import com.project.legacyadapterservice.mapper.AccountMapper;
import com.project.legacyadapterservice.soap.client.CoreBankingSoapClient;
import org.springframework.stereotype.Service;

@Service
public class AccountAdapterService {

    private final CoreBankingSoapClient soapClient;
    private final AccountMapper mapper;

    public AccountAdapterService(CoreBankingSoapClient soapClient, AccountMapper mapper) {
        this.soapClient = soapClient;
        this.mapper = mapper;
    }

    // ===== ACCOUNT QUERY OPERATIONS =====

    public GetComptesByStatusTypeResponseDTO getComptesByStatusType(GetComptesByStatusTypeRequestDTO restRequest) {
        GetComptesByStatusTypeResponse response = soapClient.getComptesByStatusType(
                restRequest.getType(),
                restRequest.getStatus());
        return mapper.toRestDto(response);
    }

    public GetSoldeResponseDTO getSolde(GetSoldeRequestDTO restRequest) {
        GetSoldeResponse response = soapClient.getSolde(restRequest.getRib());
        return mapper.toRestDto(response);
    }

    public GetCompteByIdResponseDTO getCompteById(GetCompteByIdRequestDTO restRequest) {
        GetCompteByIdResponse response = soapClient.getCompteById(restRequest.getId());
        return mapper.toRestDto(response);
    }

    public GetComptesByClientResponseDTO getComptesByClient(GetComptesByClientRequestDTO restRequest) {
        GetComptesByClientResponse response = soapClient.getComptesByClient(
                restRequest.getClientId(),
                restRequest.getType(),
                restRequest.getStatus());
        return mapper.toRestDto(response);
    }

    public GetClientIdByCompteIdResponseDTO getClientIdByCompteId(GetClientIdByCompteIdRequestDTO restRequest) {
        GetClientIdByCompteIdResponse response = soapClient.getClientIdByCompteId(restRequest.getCompteId());
        return mapper.toRestDto(response);
    }

    public GetClientIdByRibResponseDTO getClientIdByRib(GetClientIdByRibRequestDTO restRequest) {
        GetClientIdByRibResponse response = soapClient.getClientIdByRib(restRequest.getRib());
        return mapper.toRestDto(response);
    }

    // ===== ACCOUNT CREATION OPERATIONS =====

    public CreateCCourantResponseDTO createCCourant(CreateCCourantRequestDTO restRequest) {
        CreateCCourantResponse response = soapClient.createCCourant(
                restRequest.getClientId(),
                restRequest.getSolde(),
                restRequest.getStatus(),
                restRequest.getAutorisePaiementEnLigne());
        return mapper.toRestDto(response);
    }

    public CreateCEpargneResponseDTO createCEpargne(CreateCEpargneRequestDTO restRequest) {
        CreateCEpargneResponse response = soapClient.createCEpargne(
                restRequest.getClientId(),
                restRequest.getTauxInterets(),
                restRequest.getSolde(),
                restRequest.getStatus());
        return mapper.toRestDto(response);
    }

    // ===== ACCOUNT UPDATE OPERATIONS =====

    public UpdateCompteResponseDTO updateCompte(UpdateCompteRequestDTO restRequest) {
        UpdateCompteResponse response = soapClient.updateCompte(
                restRequest.getId(),
                restRequest.getSolde(),
                restRequest.getStatus(),
                restRequest.getAutorisePaiementEnLigne(),
                restRequest.getTauxInterets());
        return mapper.toRestDto(response);
    }

    public ChangeCompteStatusResponseDTO changeCompteStatus(ChangeCompteStatusRequestDTO restRequest) {
        ChangeCompteStatusResponse response = soapClient.changeCompteStatus(
                restRequest.getCompteId(),
                restRequest.getStatus());
        return mapper.toRestDto(response);
    }

    public ChangeDotationStatusResponseDTO changeDotationStatus(ChangeDotationStatusRequestDTO restRequest) {
        ChangeDotationStatusResponse response = soapClient.changeDotationStatus(
                restRequest.getAccountId(),
                restRequest.getAutorisePaiementEnLigne());
        return mapper.toRestDto(response);
    }

    // ===== ACCOUNT DELETION OPERATIONS =====

    public DeleteCompteResponseDTO deleteCompte(DeleteCompteRequestDTO restRequest) {
        DeleteCompteResponse response = soapClient.deleteCompte(restRequest.getCompteId());
        return mapper.toRestDto(response);
    }

    // ===== ACCOUNT VALIDATION OPERATIONS =====

    public ValidateRibResponseDTO validateRib(ValidateRibRequestDTO restRequest) {
        ValidateRibResponse response = soapClient.validateRib(restRequest.getRib());
        return mapper.toRestDto(response);
    }

    public CheckAccountExistsResponseDTO checkAccountExists(CheckAccountExistsRequestDTO restRequest) {
        CheckAccountExistsResponse response = soapClient.checkAccountExists(restRequest.getRib());
        return mapper.toRestDto(response);
    }

    public VerifyAccountOwnershipResponseDTO verifyAccountOwnership(VerifyAccountOwnershipRequestDTO restRequest) {
        VerifyAccountOwnershipResponse response = soapClient.verifyAccountOwnership(
                restRequest.getCompteId(),
                restRequest.getClientId());
        return mapper.toRestDto(response);
    }

    // ===== ACCOUNT BULK OPERATIONS =====

    public GetAccountsByRibsResponseDTO getAccountsByRibs(GetAccountsByRibsRequestDTO restRequest) {
        GetAccountsByRibsResponse response = soapClient.getAccountsByRibs(restRequest.getRibs());
        return mapper.toRestDto(response);
    }

    // ===== ACCOUNT SECURITY OPERATIONS =====

    public BlockAccountResponseDTO blockAccount(BlockAccountRequestDTO restRequest) {
        BlockAccountResponse response = soapClient.blockAccount(
                restRequest.getCompteId(),
                restRequest.getReason());
        return mapper.toRestDto(response);
    }

    public UnblockAccountResponseDTO unblockAccount(UnblockAccountRequestDTO restRequest) {
        UnblockAccountResponse response = soapClient.unblockAccount(
                restRequest.getCompteId(),
                restRequest.getReason());
        return mapper.toRestDto(response);
    }

    // ===== ACCOUNT REPORTING OPERATIONS =====

    public GetAccountStatementResponseDTO getAccountStatement(GetAccountStatementRequestDTO restRequest) {
        GetAccountStatementResponse response = soapClient.getAccountStatement(
                restRequest.getCompteId(),
                restRequest.getStartDate(),
                restRequest.getEndDate());
        return mapper.toRestDto(response);
    }

    public GetAccountSummaryResponseDTO getAccountSummary(GetAccountSummaryRequestDTO restRequest) {
        GetAccountSummaryResponse response = soapClient.getAccountSummary(restRequest.getClientId());
        return mapper.toRestDto(response);
    }

    // ===== CEPARGNE SPECIFIC OPERATIONS =====

    public GetCEpargneQuarterlyInterestsResponseDTO getCEpargneQuarterlyInterests(
            GetCEpargneQuarterlyInterestsRequestDTO restRequest) {
        GetCEpargneQuarterlyInterestsResponse response = soapClient.getCEpargneQuarterlyInterests(
                restRequest.getCompteId());
        return mapper.toRestDto(response);
    }

    public UpdateCEpargneInterestRateResponseDTO updateCEpargneInterestRate(
            UpdateCEpargneInterestRateRequestDTO restRequest) {
        UpdateCEpargneInterestRateResponse response = soapClient.updateCEpargneInterestRate(
                restRequest.getCompteId(),
                restRequest.getNewTauxInterets());
        return mapper.toRestDto(response);
    }

    public ApplyCEpargneInterestsResponseDTO applyCEpargneInterests(
            ApplyCEpargneInterestsRequestDTO restRequest) {
        ApplyCEpargneInterestsResponse response = soapClient.applyCEpargneInterests(
                restRequest.getCompteId());
        return mapper.toRestDto(response);
    }

    public CalculateCEpargneInterestsResponseDTO calculateCEpargneInterests(
            CalculateCEpargneInterestsRequestDTO restRequest) {
        CalculateCEpargneInterestsResponse response = soapClient.calculateCEpargneInterests(
                restRequest.getCompteId(),
                restRequest.getPeriod());
        return mapper.toRestDto(response);
    }

    public GetCEpargneByIdResponseDTO getCEpargneById(GetCEpargneByIdRequestDTO restRequest) {
        GetCEpargneByIdResponse response = soapClient.getCEpargneById(restRequest.getCompteId());
        return mapper.toRestDto(response);
    }

    public GetAllCEpargneByClientResponseDTO getAllCEpargneByClient(
            GetAllCEpargneByClientRequestDTO restRequest) {
        GetAllCEpargneByClientResponse response = soapClient.getAllCEpargneByClient(
                restRequest.getClientId(),
                restRequest.getStatus());
        return mapper.toRestDto(response);
    }

    public GetAllCEpargneByInterestRateResponseDTO getAllCEpargneByInterestRate(
            GetAllCEpargneByInterestRateRequestDTO restRequest) {
        GetAllCEpargneByInterestRateResponse response = soapClient.getAllCEpargneByInterestRate(
                restRequest.getMinTaux(),
                restRequest.getMaxTaux());
        return mapper.toRestDto(response);
    }
}
