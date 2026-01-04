package com.project.legacyadapterservice.soap.client;

import com.ebank.ebanking2.soap.dto.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CoreBankingSoapClientTest {

    @Mock
    private WebServiceTemplate webServiceTemplate;

    @InjectMocks
    private CoreBankingSoapClient soapClient;

    // ===== VIREMENT OPERATIONS =====

    @Test
    void executeVirement_ShouldSendRequestAndReturnResponse() {
        ExecuteVirementResponse response = new ExecuteVirementResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(ExecuteVirementRequest.class))).thenReturn(response);

        ExecuteVirementResponse result = soapClient.executeVirement("RIB123", "RIB456", 1000.0, Type.NORMAL);

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(ExecuteVirementRequest.class));
    }

    @Test
    void getVirementById_ShouldSendRequestAndReturnResponse() {
        GetVirementByIdResponse response = new GetVirementByIdResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(GetVirementByIdRequest.class))).thenReturn(response);

        GetVirementByIdResponse result = soapClient.getVirementById(100L);

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(GetVirementByIdRequest.class));
    }

    // ===== C-EPARGNE OPERATIONS =====

    @Test
    void depositCEpargne_ShouldSendRequestAndReturnResponse() {
        CEpargneResponseDTO response = new CEpargneResponseDTO();
        when(webServiceTemplate.marshalSendAndReceive(any(DepotCEpargneRequest.class))).thenReturn(response);

        CEpargneResponseDTO result = soapClient.depositCEpargne("EPARGNE123", "COURANT456", 500.0, 2024, 1, 15);

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(DepotCEpargneRequest.class));
    }

    @Test
    void withdrawCEpargne_ShouldSendRequestAndReturnResponse() {
        CEpargneResponseDTO response = new CEpargneResponseDTO();
        when(webServiceTemplate.marshalSendAndReceive(any(RetraitCEpargneRequest.class))).thenReturn(response);

        CEpargneResponseDTO result = soapClient.withdrawCEpargne("EPARGNE123", "COURANT456", 300.0, 2024, 2, 20);

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(RetraitCEpargneRequest.class));
    }

    // ===== ANNUAL / QUINZAINE OPERATIONS =====

    @Test
    void closeAnnual_ShouldSendRequestAndReturnResponse() {
        ClotureAnnuelleResponse response = new ClotureAnnuelleResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(CalculateAnnuealeRequest.class))).thenReturn(response);

        ClotureAnnuelleResponse result = soapClient.closeAnnual("RIB123", 2024);

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(CalculateAnnuealeRequest.class));
    }

    @Test
    void closeQuinzaine_ShouldSendRequestAndReturnResponse() {
        ClotureAnnuelleResponse response = new ClotureAnnuelleResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(CloturequainzineRequest.class))).thenReturn(response);

        ClotureAnnuelleResponse result = soapClient.closeQuinzaine("RIB123", 2024, 5);

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(CloturequainzineRequest.class));
    }

    // ===== ACCOUNT OPERATIONS =====

    @Test
    void getComptesByStatusType_ShouldSendRequestAndReturnResponse() {
        GetComptesByStatusTypeResponse response = new GetComptesByStatusTypeResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(GetComptesByStatusTypeRequest.class))).thenReturn(response);

        GetComptesByStatusTypeResponse result = soapClient.getComptesByStatusType("COURANT", "ACTIVE");

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(GetComptesByStatusTypeRequest.class));
    }

    @Test
    void getSolde_ShouldSendRequestAndReturnResponse() {
        GetSoldeResponse response = new GetSoldeResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(GetSoldeRequest.class))).thenReturn(response);

        GetSoldeResponse result = soapClient.getSolde("RIB123");

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(GetSoldeRequest.class));
    }

    @Test
    void getCompteById_ShouldSendRequestAndReturnResponse() {
        GetCompteByIdResponse response = new GetCompteByIdResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(GetCompteByIdRequest.class))).thenReturn(response);

        GetCompteByIdResponse result = soapClient.getCompteById(100L);

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(GetCompteByIdRequest.class));
    }

    @Test
    void getComptesByClient_ShouldSendRequestAndReturnResponse() {
        GetComptesByClientResponse response = new GetComptesByClientResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(GetComptesByClientRequest.class))).thenReturn(response);

        GetComptesByClientResponse result = soapClient.getComptesByClient(200L, "COURANT", "ACTIVE");

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(GetComptesByClientRequest.class));
    }

    @Test
    void changeDotationStatus_ShouldSendRequestAndReturnResponse() {
        ChangeDotationStatusResponse response = new ChangeDotationStatusResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(ChangeDotationStatusRequest.class))).thenReturn(response);

        ChangeDotationStatusResponse result = soapClient.changeDotationStatus(100L, true);

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(ChangeDotationStatusRequest.class));
    }

    @Test
    void createCCourant_ShouldSendRequestAndReturnResponse() {
        CreateCCourantResponse response = new CreateCCourantResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(CreateCCourantRequest.class))).thenReturn(response);

        CreateCCourantResponse result = soapClient.createCCourant(200L, 1000.0, "ACTIVE", true);

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(CreateCCourantRequest.class));
    }

    @Test
    void createCEpargne_ShouldSendRequestAndReturnResponse() {
        CreateCEpargneResponse response = new CreateCEpargneResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(CreateCEpargneRequest.class))).thenReturn(response);

        CreateCEpargneResponse result = soapClient.createCEpargne(200L, 3.5, 5000.0, "ACTIVE");

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(CreateCEpargneRequest.class));
    }

    @Test
    void getClientIdByCompteId_ShouldSendRequestAndReturnResponse() {
        GetClientIdByCompteIdResponse response = new GetClientIdByCompteIdResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(GetClientIdByCompteIdRequest.class))).thenReturn(response);

        GetClientIdByCompteIdResponse result = soapClient.getClientIdByCompteId(100L);

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(GetClientIdByCompteIdRequest.class));
    }

    @Test
    void getClientIdByRib_ShouldSendRequestAndReturnResponse() {
        GetClientIdByRibResponse response = new GetClientIdByRibResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(GetClientIdByRibRequest.class))).thenReturn(response);

        GetClientIdByRibResponse result = soapClient.getClientIdByRib("RIB123");

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(GetClientIdByRibRequest.class));
    }

    @Test
    void updateCompte_ShouldSendRequestAndReturnResponse() {
        UpdateCompteResponse response = new UpdateCompteResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(UpdateCompteRequest.class))).thenReturn(response);

        UpdateCompteResponse result = soapClient.updateCompte(100L, 5000.0, "ACTIVE", true, 3.0);

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(UpdateCompteRequest.class));
    }

    @Test
    void changeCompteStatus_ShouldSendRequestAndReturnResponse() {
        ChangeCompteStatusResponse response = new ChangeCompteStatusResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(ChangeCompteStatusRequest.class))).thenReturn(response);

        ChangeCompteStatusResponse result = soapClient.changeCompteStatus(100L, "SUSPENDED");

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(ChangeCompteStatusRequest.class));
    }

    @Test
    void deleteCompte_ShouldSendRequestAndReturnResponse() {
        DeleteCompteResponse response = new DeleteCompteResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(DeleteCompteRequest.class))).thenReturn(response);

        DeleteCompteResponse result = soapClient.deleteCompte(100L);

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(DeleteCompteRequest.class));
    }

    @Test
    void validateRib_ShouldSendRequestAndReturnResponse() {
        ValidateRibResponse response = new ValidateRibResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(ValidateRibRequest.class))).thenReturn(response);

        ValidateRibResponse result = soapClient.validateRib("RIB123");

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(ValidateRibRequest.class));
    }

    @Test
    void checkAccountExists_ShouldSendRequestAndReturnResponse() {
        CheckAccountExistsResponse response = new CheckAccountExistsResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(CheckAccountExistsRequest.class))).thenReturn(response);

        CheckAccountExistsResponse result = soapClient.checkAccountExists("RIB123");

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(CheckAccountExistsRequest.class));
    }

    @Test
    void getAccountsByRibs_ShouldSendRequestAndReturnResponse() {
        GetAccountsByRibsResponse response = new GetAccountsByRibsResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(GetAccountsByRibsRequest.class))).thenReturn(response);

        List<String> ribs = Arrays.asList("RIB1", "RIB2", "RIB3");
        GetAccountsByRibsResponse result = soapClient.getAccountsByRibs(ribs);

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(GetAccountsByRibsRequest.class));
    }

    @Test
    void blockAccount_ShouldSendRequestAndReturnResponse() {
        BlockAccountResponse response = new BlockAccountResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(BlockAccountRequest.class))).thenReturn(response);

        BlockAccountResponse result = soapClient.blockAccount(100L, "Fraud detected");

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(BlockAccountRequest.class));
    }

    @Test
    void unblockAccount_ShouldSendRequestAndReturnResponse() {
        UnblockAccountResponse response = new UnblockAccountResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(UnblockAccountRequest.class))).thenReturn(response);

        UnblockAccountResponse result = soapClient.unblockAccount(100L, "Verified");

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(UnblockAccountRequest.class));
    }

    @Test
    void getAccountStatement_ShouldSendRequestAndReturnResponse() {
        GetAccountStatementResponse response = new GetAccountStatementResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(GetAccountStatementRequest.class))).thenReturn(response);

        GetAccountStatementResponse result = soapClient.getAccountStatement(100L, "2024-01-01", "2024-12-31");

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(GetAccountStatementRequest.class));
    }

    @Test
    void verifyAccountOwnership_ShouldSendRequestAndReturnResponse() {
        VerifyAccountOwnershipResponse response = new VerifyAccountOwnershipResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(VerifyAccountOwnershipRequest.class))).thenReturn(response);

        VerifyAccountOwnershipResponse result = soapClient.verifyAccountOwnership(100L, 200L);

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(VerifyAccountOwnershipRequest.class));
    }

    @Test
    void getAccountSummary_ShouldSendRequestAndReturnResponse() {
        GetAccountSummaryResponse response = new GetAccountSummaryResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(GetAccountSummaryRequest.class))).thenReturn(response);

        GetAccountSummaryResponse result = soapClient.getAccountSummary(200L);

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(GetAccountSummaryRequest.class));
    }

    // ===== CEPARGNE SPECIFIC OPERATIONS =====

    @Test
    void getCEpargneQuarterlyInterests_ShouldSendRequestAndReturnResponse() {
        GetCEpargneQuarterlyInterestsResponse response = new GetCEpargneQuarterlyInterestsResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(GetCEpargneQuarterlyInterestsRequest.class)))
                .thenReturn(response);

        GetCEpargneQuarterlyInterestsResponse result = soapClient.getCEpargneQuarterlyInterests(100L);

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(GetCEpargneQuarterlyInterestsRequest.class));
    }

    @Test
    void updateCEpargneInterestRate_ShouldSendRequestAndReturnResponse() {
        UpdateCEpargneInterestRateResponse response = new UpdateCEpargneInterestRateResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(UpdateCEpargneInterestRateRequest.class)))
                .thenReturn(response);

        UpdateCEpargneInterestRateResponse result = soapClient.updateCEpargneInterestRate(100L, 4.0);

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(UpdateCEpargneInterestRateRequest.class));
    }

    @Test
    void applyCEpargneInterests_ShouldSendRequestAndReturnResponse() {
        ApplyCEpargneInterestsResponse response = new ApplyCEpargneInterestsResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(ApplyCEpargneInterestsRequest.class))).thenReturn(response);

        ApplyCEpargneInterestsResponse result = soapClient.applyCEpargneInterests(100L);

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(ApplyCEpargneInterestsRequest.class));
    }

    @Test
    void calculateCEpargneInterests_ShouldSendRequestAndReturnResponse() {
        CalculateCEpargneInterestsResponse response = new CalculateCEpargneInterestsResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(CalculateCEpargneInterestsRequest.class)))
                .thenReturn(response);

        CalculateCEpargneInterestsResponse result = soapClient.calculateCEpargneInterests(100L, 12);

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(CalculateCEpargneInterestsRequest.class));
    }

    @Test
    void getCEpargneById_ShouldSendRequestAndReturnResponse() {
        GetCEpargneByIdResponse response = new GetCEpargneByIdResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(GetCEpargneByIdRequest.class))).thenReturn(response);

        GetCEpargneByIdResponse result = soapClient.getCEpargneById(100L);

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(GetCEpargneByIdRequest.class));
    }

    @Test
    void getAllCEpargneByClient_ShouldSendRequestAndReturnResponse() {
        GetAllCEpargneByClientResponse response = new GetAllCEpargneByClientResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(GetAllCEpargneByClientRequest.class))).thenReturn(response);

        GetAllCEpargneByClientResponse result = soapClient.getAllCEpargneByClient(200L, "ACTIVE");

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(GetAllCEpargneByClientRequest.class));
    }

    @Test
    void getAllCEpargneByInterestRate_ShouldSendRequestAndReturnResponse() {
        GetAllCEpargneByInterestRateResponse response = new GetAllCEpargneByInterestRateResponse();
        when(webServiceTemplate.marshalSendAndReceive(any(GetAllCEpargneByInterestRateRequest.class)))
                .thenReturn(response);

        GetAllCEpargneByInterestRateResponse result = soapClient.getAllCEpargneByInterestRate(2.0, 5.0);

        assertThat(result).isNotNull();
        verify(webServiceTemplate).marshalSendAndReceive(any(GetAllCEpargneByInterestRateRequest.class));
    }
}
