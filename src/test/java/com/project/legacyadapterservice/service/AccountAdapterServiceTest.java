package com.project.legacyadapterservice.service;

import com.ebank.ebanking2.soap.dto.*;
import com.project.legacyadapterservice.dto.rest.*;
import com.project.legacyadapterservice.mapper.AccountMapper;
import com.project.legacyadapterservice.soap.client.CoreBankingSoapClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountAdapterServiceTest {

    @Mock
    private CoreBankingSoapClient soapClient;

    @Mock
    private AccountMapper mapper;

    @InjectMocks
    private AccountAdapterService accountAdapterService;

    @Test
    void getSolde_ShouldCallSoapClientAndReturnMappedResponse() {
        // Arrange
        GetSoldeRequestDTO request = new GetSoldeRequestDTO();
        request.setRib("123456789");

        GetSoldeResponse soapResponse = new GetSoldeResponse();
        soapResponse.setSolde(1500.50);

        GetSoldeResponseDTO restResponse = new GetSoldeResponseDTO();
        restResponse.setSolde(1500.50);
        ServiceStatusDTO serviceStatus = new ServiceStatusDTO();
        serviceStatus.setStatusCode("200");
        serviceStatus.setMessage("Success");
        restResponse.setServiceStatus(serviceStatus);

        when(soapClient.getSolde(anyString())).thenReturn(soapResponse);
        when(mapper.toRestDto(any(GetSoldeResponse.class))).thenReturn(restResponse);

        // Act
        GetSoldeResponseDTO result = accountAdapterService.getSolde(request);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getSolde()).isEqualTo(1500.50);
        assertThat(result.getServiceStatus().getStatusCode()).isEqualTo("200");

        verify(soapClient).getSolde("123456789");
        verify(mapper).toRestDto(soapResponse);
    }

    @Test
    void getComptesByStatusType_ShouldCallSoapClientAndReturnMappedResponse() {
        // Arrange
        GetComptesByStatusTypeRequestDTO request = new GetComptesByStatusTypeRequestDTO();
        request.setType("COURANT");
        request.setStatus("ACTIVE");

        GetComptesByStatusTypeResponse soapResponse = new GetComptesByStatusTypeResponse();

        GetComptesByStatusTypeResponseDTO restResponse = new GetComptesByStatusTypeResponseDTO();
        restResponse.setComptes(Collections.emptyList());
        ServiceStatusDTO serviceStatus = new ServiceStatusDTO();
        serviceStatus.setStatusCode("200");
        restResponse.setServiceStatus(serviceStatus);

        when(soapClient.getComptesByStatusType(anyString(), anyString())).thenReturn(soapResponse);
        when(mapper.toRestDto(any(GetComptesByStatusTypeResponse.class))).thenReturn(restResponse);

        // Act
        GetComptesByStatusTypeResponseDTO result = accountAdapterService.getComptesByStatusType(request);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getServiceStatus().getStatusCode()).isEqualTo("200");

        verify(soapClient).getComptesByStatusType("COURANT", "ACTIVE");
        verify(mapper).toRestDto(soapResponse);
    }

    @Test
    void createCCourant_ShouldCallSoapClientWithCorrectParameters() {
        // Arrange
        CreateCCourantRequestDTO request = new CreateCCourantRequestDTO();
        request.setClientId(1001L);
        request.setSolde(500.0);
        request.setStatus("ACTIVE");
        request.setAutorisePaiementEnLigne(true);

        CreateCCourantResponse soapResponse = new CreateCCourantResponse();
        CompteSoapInfo compteInfo = new CompteSoapInfo();
        compteInfo.setRib("123456789");
        soapResponse.setCompte(compteInfo);

        CreateCCourantResponseDTO restResponse = new CreateCCourantResponseDTO();
        CompteSoapInfoDTO compteDTO = new CompteSoapInfoDTO();
        compteDTO.setRib("123456789");
        restResponse.setCompte(compteDTO);

        when(soapClient.createCCourant(1001L, 500.0, "ACTIVE", true)).thenReturn(soapResponse);
        when(mapper.toRestDto(any(CreateCCourantResponse.class))).thenReturn(restResponse);

        // Act
        CreateCCourantResponseDTO result = accountAdapterService.createCCourant(request);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getCompte().getRib()).isEqualTo("123456789");

        verify(soapClient).createCCourant(1001L, 500.0, "ACTIVE", true);
        verify(mapper).toRestDto(soapResponse);
    }

    @Test
    void getCompteById_ShouldReturnMappedResponse() {
        // Arrange
        GetCompteByIdRequestDTO request = new GetCompteByIdRequestDTO();
        request.setId(100L);

        GetCompteByIdResponse soapResponse = new GetCompteByIdResponse();
        CompteSoapInfo compteInfo = new CompteSoapInfo();
        compteInfo.setId(100L);
        compteInfo.setRib("RIB100");
        soapResponse.setCompte(compteInfo);

        GetCompteByIdResponseDTO restResponse = new GetCompteByIdResponseDTO();
        CompteSoapInfoDTO compteDTO = new CompteSoapInfoDTO();
        compteDTO.setId(100L);
        compteDTO.setRib("RIB100");
        restResponse.setCompte(compteDTO);

        when(soapClient.getCompteById(100L)).thenReturn(soapResponse);
        when(mapper.toRestDto(any(GetCompteByIdResponse.class))).thenReturn(restResponse);

        // Act
        GetCompteByIdResponseDTO result = accountAdapterService.getCompteById(request);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getCompte().getRib()).isEqualTo("RIB100");
        verify(soapClient).getCompteById(100L);
        verify(mapper).toRestDto(soapResponse);
    }

    @Test
    void getComptesByClient_ShouldReturnMappedResponse() {
        // Arrange
        GetComptesByClientRequestDTO request = new GetComptesByClientRequestDTO();
        request.setClientId(500L);
        request.setType("COURANT");
        request.setStatus("ACTIVE");

        GetComptesByClientResponse soapResponse = new GetComptesByClientResponse();

        GetComptesByClientResponseDTO restResponse = new GetComptesByClientResponseDTO();
        restResponse.setComptes(Collections.emptyList());

        when(soapClient.getComptesByClient(500L, "COURANT", "ACTIVE")).thenReturn(soapResponse);
        when(mapper.toRestDto(any(GetComptesByClientResponse.class))).thenReturn(restResponse);

        // Act
        GetComptesByClientResponseDTO result = accountAdapterService.getComptesByClient(request);

        // Assert
        assertThat(result).isNotNull();
        verify(soapClient).getComptesByClient(500L, "COURANT", "ACTIVE");
        verify(mapper).toRestDto(soapResponse);
    }

    @Test
    void createCEpargne_ShouldCallSoapClientWithCorrectParameters() {
        // Arrange
        CreateCEpargneRequestDTO request = new CreateCEpargneRequestDTO();
        request.setClientId(200L);
        request.setTauxInterets(2.5);
        request.setSolde(1000.0);
        request.setStatus("ACTIVE");

        CreateCEpargneResponse soapResponse = new CreateCEpargneResponse();
        CompteSoapInfo compteInfo = new CompteSoapInfo();
        compteInfo.setRib("EPARGNE123");
        soapResponse.setCompte(compteInfo);

        CreateCEpargneResponseDTO restResponse = new CreateCEpargneResponseDTO();
        CompteSoapInfoDTO compteDTO = new CompteSoapInfoDTO();
        compteDTO.setRib("EPARGNE123");
        restResponse.setCompte(compteDTO);

        when(soapClient.createCEpargne(200L, 2.5, 1000.0, "ACTIVE")).thenReturn(soapResponse);
        when(mapper.toRestDto(any(CreateCEpargneResponse.class))).thenReturn(restResponse);

        // Act
        CreateCEpargneResponseDTO result = accountAdapterService.createCEpargne(request);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getCompte().getRib()).isEqualTo("EPARGNE123");
        verify(soapClient).createCEpargne(200L, 2.5, 1000.0, "ACTIVE");
        verify(mapper).toRestDto(soapResponse);
    }

    @Test
    void validateRib_ShouldReturnValidationResult() {
        // Arrange
        ValidateRibRequestDTO request = new ValidateRibRequestDTO();
        request.setRib("TEST123");

        ValidateRibResponse soapResponse = new ValidateRibResponse();
        soapResponse.setIsValid(true);

        ValidateRibResponseDTO restResponse = new ValidateRibResponseDTO();
        restResponse.setIsValid(true);

        when(soapClient.validateRib("TEST123")).thenReturn(soapResponse);
        when(mapper.toRestDto(any(ValidateRibResponse.class))).thenReturn(restResponse);

        // Act
        ValidateRibResponseDTO result = accountAdapterService.validateRib(request);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getIsValid()).isTrue();
        verify(soapClient).validateRib("TEST123");
        verify(mapper).toRestDto(soapResponse);
    }

    @Test
    void getClientIdByCompteId_ShouldReturnClientId() {
        GetClientIdByCompteIdRequestDTO request = new GetClientIdByCompteIdRequestDTO();
        request.setCompteId(100L);

        GetClientIdByCompteIdResponse soapResponse = new GetClientIdByCompteIdResponse();
        soapResponse.setClientId(500L);

        GetClientIdByCompteIdResponseDTO restResponse = new GetClientIdByCompteIdResponseDTO();
        restResponse.setClientId(500L);

        when(soapClient.getClientIdByCompteId(100L)).thenReturn(soapResponse);
        when(mapper.toRestDto(any(GetClientIdByCompteIdResponse.class))).thenReturn(restResponse);

        GetClientIdByCompteIdResponseDTO result = accountAdapterService.getClientIdByCompteId(request);

        assertThat(result).isNotNull();
        assertThat(result.getClientId()).isEqualTo(500L);
        verify(soapClient).getClientIdByCompteId(100L);
    }

    @Test
    void getClientIdByRib_ShouldReturnClientId() {
        GetClientIdByRibRequestDTO request = new GetClientIdByRibRequestDTO();
        request.setRib("RIB123");

        GetClientIdByRibResponse soapResponse = new GetClientIdByRibResponse();
        soapResponse.setClientId(600L);

        GetClientIdByRibResponseDTO restResponse = new GetClientIdByRibResponseDTO();
        restResponse.setClientId(600L);

        when(soapClient.getClientIdByRib("RIB123")).thenReturn(soapResponse);
        when(mapper.toRestDto(any(GetClientIdByRibResponse.class))).thenReturn(restResponse);

        GetClientIdByRibResponseDTO result = accountAdapterService.getClientIdByRib(request);

        assertThat(result).isNotNull();
        assertThat(result.getClientId()).isEqualTo(600L);
        verify(soapClient).getClientIdByRib("RIB123");
    }

    @Test
    void updateCompte_ShouldUpdateAccount() {
        UpdateCompteRequestDTO request = new UpdateCompteRequestDTO();
        request.setId(100L);
        request.setSolde(5000.0);
        request.setStatus("ACTIVE");

        UpdateCompteResponse soapResponse = new UpdateCompteResponse();
        UpdateCompteResponseDTO restResponse = new UpdateCompteResponseDTO();

        when(soapClient.updateCompte(eq(100L), eq(5000.0), eq("ACTIVE"), any(), any())).thenReturn(soapResponse);
        when(mapper.toRestDto(any(UpdateCompteResponse.class))).thenReturn(restResponse);

        UpdateCompteResponseDTO result = accountAdapterService.updateCompte(request);

        assertThat(result).isNotNull();
        verify(soapClient).updateCompte(100L, 5000.0, "ACTIVE", null, null);
    }

    @Test
    void changeCompteStatus_ShouldChangeStatus() {
        ChangeCompteStatusRequestDTO request = new ChangeCompteStatusRequestDTO();
        request.setCompteId(100L);
        request.setStatus("SUSPENDED");

        ChangeCompteStatusResponse soapResponse = new ChangeCompteStatusResponse();
        ChangeCompteStatusResponseDTO restResponse = new ChangeCompteStatusResponseDTO();

        when(soapClient.changeCompteStatus(100L, "SUSPENDED")).thenReturn(soapResponse);
        when(mapper.toRestDto(any(ChangeCompteStatusResponse.class))).thenReturn(restResponse);

        ChangeCompteStatusResponseDTO result = accountAdapterService.changeCompteStatus(request);

        assertThat(result).isNotNull();
        verify(soapClient).changeCompteStatus(100L, "SUSPENDED");
    }

    @Test
    void changeDotationStatus_ShouldChangeDotation() {
        ChangeDotationStatusRequestDTO request = new ChangeDotationStatusRequestDTO();
        request.setAccountId(100L);
        request.setAutorisePaiementEnLigne(false);

        ChangeDotationStatusResponse soapResponse = new ChangeDotationStatusResponse();
        ChangeDotationStatusResponseDTO restResponse = new ChangeDotationStatusResponseDTO();

        when(soapClient.changeDotationStatus(100L, false)).thenReturn(soapResponse);
        when(mapper.toRestDto(any(ChangeDotationStatusResponse.class))).thenReturn(restResponse);

        ChangeDotationStatusResponseDTO result = accountAdapterService.changeDotationStatus(request);

        assertThat(result).isNotNull();
        verify(soapClient).changeDotationStatus(100L, false);
    }

    @Test
    void deleteCompte_ShouldDeleteAccount() {
        DeleteCompteRequestDTO request = new DeleteCompteRequestDTO();
        request.setCompteId(100L);

        DeleteCompteResponse soapResponse = new DeleteCompteResponse();
        DeleteCompteResponseDTO restResponse = new DeleteCompteResponseDTO();

        when(soapClient.deleteCompte(100L)).thenReturn(soapResponse);
        when(mapper.toRestDto(any(DeleteCompteResponse.class))).thenReturn(restResponse);

        DeleteCompteResponseDTO result = accountAdapterService.deleteCompte(request);

        assertThat(result).isNotNull();
        verify(soapClient).deleteCompte(100L);
    }

    @Test
    void checkAccountExists_ShouldReturnExistence() {
        CheckAccountExistsRequestDTO request = new CheckAccountExistsRequestDTO();
        request.setRib("RIB123");

        CheckAccountExistsResponse soapResponse = new CheckAccountExistsResponse();
        CheckAccountExistsResponseDTO restResponse = new CheckAccountExistsResponseDTO();

        when(soapClient.checkAccountExists("RIB123")).thenReturn(soapResponse);
        when(mapper.toRestDto(any(CheckAccountExistsResponse.class))).thenReturn(restResponse);

        CheckAccountExistsResponseDTO result = accountAdapterService.checkAccountExists(request);

        assertThat(result).isNotNull();
        verify(soapClient).checkAccountExists("RIB123");
    }

    @Test
    void verifyAccountOwnership_ShouldVerifyOwnership() {
        VerifyAccountOwnershipRequestDTO request = new VerifyAccountOwnershipRequestDTO();
        request.setCompteId(100L);
        request.setClientId(500L);

        VerifyAccountOwnershipResponse soapResponse = new VerifyAccountOwnershipResponse();
        VerifyAccountOwnershipResponseDTO restResponse = new VerifyAccountOwnershipResponseDTO();

        when(soapClient.verifyAccountOwnership(100L, 500L)).thenReturn(soapResponse);
        when(mapper.toRestDto(any(VerifyAccountOwnershipResponse.class))).thenReturn(restResponse);

        VerifyAccountOwnershipResponseDTO result = accountAdapterService.verifyAccountOwnership(request);

        assertThat(result).isNotNull();
        verify(soapClient).verifyAccountOwnership(100L, 500L);
    }

    @Test
    void blockAccount_ShouldBlockAccount() {
        BlockAccountRequestDTO request = new BlockAccountRequestDTO();
        request.setCompteId(100L);
        request.setReason("Suspicious activity");

        BlockAccountResponse soapResponse = new BlockAccountResponse();
        BlockAccountResponseDTO restResponse = new BlockAccountResponseDTO();

        when(soapClient.blockAccount(100L, "Suspicious activity")).thenReturn(soapResponse);
        when(mapper.toRestDto(any(BlockAccountResponse.class))).thenReturn(restResponse);

        BlockAccountResponseDTO result = accountAdapterService.blockAccount(request);

        assertThat(result).isNotNull();
        verify(soapClient).blockAccount(100L, "Suspicious activity");
    }

    @Test
    void unblockAccount_ShouldUnblockAccount() {
        UnblockAccountRequestDTO request = new UnblockAccountRequestDTO();
        request.setCompteId(100L);
        request.setReason("Verified");

        UnblockAccountResponse soapResponse = new UnblockAccountResponse();
        UnblockAccountResponseDTO restResponse = new UnblockAccountResponseDTO();

        when(soapClient.unblockAccount(100L, "Verified")).thenReturn(soapResponse);
        when(mapper.toRestDto(any(UnblockAccountResponse.class))).thenReturn(restResponse);

        UnblockAccountResponseDTO result = accountAdapterService.unblockAccount(request);

        assertThat(result).isNotNull();
        verify(soapClient).unblockAccount(100L, "Verified");
    }

    @Test
    void getAccountsByRibs_ShouldReturnAccounts() {
        GetAccountsByRibsRequestDTO request = new GetAccountsByRibsRequestDTO();
        request.setRibs(Arrays.asList("RIB1", "RIB2", "RIB3"));

        GetAccountsByRibsResponse soapResponse = new GetAccountsByRibsResponse();
        GetAccountsByRibsResponseDTO restResponse = new GetAccountsByRibsResponseDTO();

        when(soapClient.getAccountsByRibs(anyList())).thenReturn(soapResponse);
        when(mapper.toRestDto(any(GetAccountsByRibsResponse.class))).thenReturn(restResponse);

        GetAccountsByRibsResponseDTO result = accountAdapterService.getAccountsByRibs(request);

        assertThat(result).isNotNull();
        verify(soapClient).getAccountsByRibs(Arrays.asList("RIB1", "RIB2", "RIB3"));
    }

    @Test
    void getAccountStatement_ShouldReturnStatement() {
        GetAccountStatementRequestDTO request = new GetAccountStatementRequestDTO();
        request.setCompteId(100L);
        request.setStartDate("2024-01-01");
        request.setEndDate("2024-12-31");

        GetAccountStatementResponse soapResponse = new GetAccountStatementResponse();
        GetAccountStatementResponseDTO restResponse = new GetAccountStatementResponseDTO();

        when(soapClient.getAccountStatement(100L, "2024-01-01", "2024-12-31")).thenReturn(soapResponse);
        when(mapper.toRestDto(any(GetAccountStatementResponse.class))).thenReturn(restResponse);

        GetAccountStatementResponseDTO result = accountAdapterService.getAccountStatement(request);

        assertThat(result).isNotNull();
        verify(soapClient).getAccountStatement(100L, "2024-01-01", "2024-12-31");
    }

    @Test
    void getAccountSummary_ShouldReturnSummary() {
        GetAccountSummaryRequestDTO request = new GetAccountSummaryRequestDTO();
        request.setClientId(200L);

        GetAccountSummaryResponse soapResponse = new GetAccountSummaryResponse();
        GetAccountSummaryResponseDTO restResponse = new GetAccountSummaryResponseDTO();

        when(soapClient.getAccountSummary(200L)).thenReturn(soapResponse);
        when(mapper.toRestDto(any(GetAccountSummaryResponse.class))).thenReturn(restResponse);

        GetAccountSummaryResponseDTO result = accountAdapterService.getAccountSummary(request);

        assertThat(result).isNotNull();
        verify(soapClient).getAccountSummary(200L);
    }

    @Test
    void getCEpargneById_ShouldReturnCEpargne() {
        GetCEpargneByIdRequestDTO request = new GetCEpargneByIdRequestDTO();
        request.setCompteId(100L);

        GetCEpargneByIdResponse soapResponse = new GetCEpargneByIdResponse();
        GetCEpargneByIdResponseDTO restResponse = new GetCEpargneByIdResponseDTO();

        when(soapClient.getCEpargneById(100L)).thenReturn(soapResponse);
        when(mapper.toRestDto(any(GetCEpargneByIdResponse.class))).thenReturn(restResponse);

        GetCEpargneByIdResponseDTO result = accountAdapterService.getCEpargneById(request);

        assertThat(result).isNotNull();
        verify(soapClient).getCEpargneById(100L);
    }

    @Test
    void getAllCEpargneByClient_ShouldReturnList() {
        GetAllCEpargneByClientRequestDTO request = new GetAllCEpargneByClientRequestDTO();
        request.setClientId(200L);
        request.setStatus("ACTIVE");

        GetAllCEpargneByClientResponse soapResponse = new GetAllCEpargneByClientResponse();
        GetAllCEpargneByClientResponseDTO restResponse = new GetAllCEpargneByClientResponseDTO();

        when(soapClient.getAllCEpargneByClient(200L, "ACTIVE")).thenReturn(soapResponse);
        when(mapper.toRestDto(any(GetAllCEpargneByClientResponse.class))).thenReturn(restResponse);

        GetAllCEpargneByClientResponseDTO result = accountAdapterService.getAllCEpargneByClient(request);

        assertThat(result).isNotNull();
        verify(soapClient).getAllCEpargneByClient(200L, "ACTIVE");
    }

    @Test
    void getAllCEpargneByInterestRate_ShouldReturnList() {
        GetAllCEpargneByInterestRateRequestDTO request = new GetAllCEpargneByInterestRateRequestDTO();
        request.setMinTaux(2.0);
        request.setMaxTaux(5.0);

        GetAllCEpargneByInterestRateResponse soapResponse = new GetAllCEpargneByInterestRateResponse();
        GetAllCEpargneByInterestRateResponseDTO restResponse = new GetAllCEpargneByInterestRateResponseDTO();

        when(soapClient.getAllCEpargneByInterestRate(2.0, 5.0)).thenReturn(soapResponse);
        when(mapper.toRestDto(any(GetAllCEpargneByInterestRateResponse.class))).thenReturn(restResponse);

        GetAllCEpargneByInterestRateResponseDTO result = accountAdapterService.getAllCEpargneByInterestRate(request);

        assertThat(result).isNotNull();
        verify(soapClient).getAllCEpargneByInterestRate(2.0, 5.0);
    }

    @Test
    void getCEpargneQuarterlyInterests_ShouldReturnInterests() {
        GetCEpargneQuarterlyInterestsRequestDTO request = new GetCEpargneQuarterlyInterestsRequestDTO();
        request.setCompteId(100L);

        GetCEpargneQuarterlyInterestsResponse soapResponse = new GetCEpargneQuarterlyInterestsResponse();
        GetCEpargneQuarterlyInterestsResponseDTO restResponse = new GetCEpargneQuarterlyInterestsResponseDTO();

        when(soapClient.getCEpargneQuarterlyInterests(100L)).thenReturn(soapResponse);
        when(mapper.toRestDto(any(GetCEpargneQuarterlyInterestsResponse.class))).thenReturn(restResponse);

        GetCEpargneQuarterlyInterestsResponseDTO result = accountAdapterService.getCEpargneQuarterlyInterests(request);

        assertThat(result).isNotNull();
        verify(soapClient).getCEpargneQuarterlyInterests(100L);
    }

    @Test
    void updateCEpargneInterestRate_ShouldUpdateRate() {
        UpdateCEpargneInterestRateRequestDTO request = new UpdateCEpargneInterestRateRequestDTO();
        request.setCompteId(100L);
        request.setNewTauxInterets(3.5);

        UpdateCEpargneInterestRateResponse soapResponse = new UpdateCEpargneInterestRateResponse();
        UpdateCEpargneInterestRateResponseDTO restResponse = new UpdateCEpargneInterestRateResponseDTO();

        when(soapClient.updateCEpargneInterestRate(100L, 3.5)).thenReturn(soapResponse);
        when(mapper.toRestDto(any(UpdateCEpargneInterestRateResponse.class))).thenReturn(restResponse);

        UpdateCEpargneInterestRateResponseDTO result = accountAdapterService.updateCEpargneInterestRate(request);

        assertThat(result).isNotNull();
        verify(soapClient).updateCEpargneInterestRate(100L, 3.5);
    }

    @Test
    void applyCEpargneInterests_ShouldApplyInterests() {
        ApplyCEpargneInterestsRequestDTO request = new ApplyCEpargneInterestsRequestDTO();
        request.setCompteId(100L);

        ApplyCEpargneInterestsResponse soapResponse = new ApplyCEpargneInterestsResponse();
        ApplyCEpargneInterestsResponseDTO restResponse = new ApplyCEpargneInterestsResponseDTO();

        when(soapClient.applyCEpargneInterests(100L)).thenReturn(soapResponse);
        when(mapper.toRestDto(any(ApplyCEpargneInterestsResponse.class))).thenReturn(restResponse);

        ApplyCEpargneInterestsResponseDTO result = accountAdapterService.applyCEpargneInterests(request);

        assertThat(result).isNotNull();
        verify(soapClient).applyCEpargneInterests(100L);
    }

    @Test
    void calculateCEpargneInterests_ShouldCalculateInterests() {
        CalculateCEpargneInterestsRequestDTO request = new CalculateCEpargneInterestsRequestDTO();
        request.setCompteId(100L);
        request.setPeriod(12);

        CalculateCEpargneInterestsResponse soapResponse = new CalculateCEpargneInterestsResponse();
        CalculateCEpargneInterestsResponseDTO restResponse = new CalculateCEpargneInterestsResponseDTO();

        when(soapClient.calculateCEpargneInterests(100L, 12)).thenReturn(soapResponse);
        when(mapper.toRestDto(any(CalculateCEpargneInterestsResponse.class))).thenReturn(restResponse);

        CalculateCEpargneInterestsResponseDTO result = accountAdapterService.calculateCEpargneInterests(request);

        assertThat(result).isNotNull();
        verify(soapClient).calculateCEpargneInterests(100L, 12);
    }
}
