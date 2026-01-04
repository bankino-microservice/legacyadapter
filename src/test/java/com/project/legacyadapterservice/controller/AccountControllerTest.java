package com.project.legacyadapterservice.controller;

import com.project.legacyadapterservice.dto.rest.*;
import com.project.legacyadapterservice.service.AccountAdapterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @Mock
    private AccountAdapterService accountService;

    @InjectMocks
    private AccountController accountController;

    @Test
    void getSolde_ShouldReturnOkWithBalance() {
        // Arrange
        GetSoldeRequestDTO request = new GetSoldeRequestDTO();
        request.setRib("123456789");

        GetSoldeResponseDTO response = new GetSoldeResponseDTO();
        response.setSolde(1500.50);
        ServiceStatusDTO serviceStatus = new ServiceStatusDTO();
        serviceStatus.setStatusCode("200");
        response.setServiceStatus(serviceStatus);

        when(accountService.getSolde(any(GetSoldeRequestDTO.class))).thenReturn(response);

        // Act
        ResponseEntity<GetSoldeResponseDTO> result = accountController.getSolde(request);

        // Assert
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getSolde()).isEqualTo(1500.50);

        verify(accountService, times(1)).getSolde(request);
    }

    @Test
    void getComptesByStatusType_ShouldReturnOkWithAccounts() {
        // Arrange
        GetComptesByStatusTypeRequestDTO request = new GetComptesByStatusTypeRequestDTO();
        request.setType("COURANT");
        request.setStatus("ACTIVE");

        GetComptesByStatusTypeResponseDTO response = new GetComptesByStatusTypeResponseDTO();
        response.setComptes(Collections.emptyList());
        ServiceStatusDTO serviceStatus = new ServiceStatusDTO();
        serviceStatus.setStatusCode("200");
        response.setServiceStatus(serviceStatus);

        when(accountService.getComptesByStatusType(any(GetComptesByStatusTypeRequestDTO.class)))
                .thenReturn(response);

        // Act
        ResponseEntity<GetComptesByStatusTypeResponseDTO> result = accountController.getComptesByStatusType(request);

        // Assert
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getComptes()).isEmpty();

        verify(accountService, times(1)).getComptesByStatusType(request);
    }

    @Test
    void createCCourant_ShouldReturnOkWithCreatedAccount() {
        // Arrange
        CreateCCourantRequestDTO request = new CreateCCourantRequestDTO();
        request.setClientId(1001L);
        request.setSolde(500.0);
        request.setStatus("ACTIVE");
        request.setAutorisePaiementEnLigne(true);

        CreateCCourantResponseDTO response = new CreateCCourantResponseDTO();
        CompteSoapInfoDTO compteInfo = new CompteSoapInfoDTO();
        compteInfo.setRib("123456789");
        response.setCompte(compteInfo);

        when(accountService.createCCourant(any(CreateCCourantRequestDTO.class))).thenReturn(response);

        // Act
        ResponseEntity<CreateCCourantResponseDTO> result = accountController.createCCourant(request);

        // Assert
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getCompte().getRib()).isEqualTo("123456789");

        verify(accountService, times(1)).createCCourant(request);
    }

    @Test
    void getCompteById_ShouldReturnOkWithAccount() {
        // Arrange
        GetCompteByIdRequestDTO request = new GetCompteByIdRequestDTO();
        request.setId(100L);

        GetCompteByIdResponseDTO response = new GetCompteByIdResponseDTO();
        CompteSoapInfoDTO compteDTO = new CompteSoapInfoDTO();
        compteDTO.setId(100L);
        compteDTO.setRib("RIB100");
        response.setCompte(compteDTO);

        when(accountService.getCompteById(any(GetCompteByIdRequestDTO.class))).thenReturn(response);

        // Act
        ResponseEntity<GetCompteByIdResponseDTO> result = accountController.getCompteById(100L);

        // Assert
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getCompte().getRib()).isEqualTo("RIB100");

        verify(accountService, times(1)).getCompteById(any(GetCompteByIdRequestDTO.class));
    }

    @Test
    void getComptesByClient_ShouldReturnOkWithAccounts() {
        // Arrange
        GetComptesByClientRequestDTO request = new GetComptesByClientRequestDTO();
        request.setClientId(500L);

        GetComptesByClientResponseDTO response = new GetComptesByClientResponseDTO();
        response.setComptes(Collections.emptyList());

        when(accountService.getComptesByClient(any(GetComptesByClientRequestDTO.class))).thenReturn(response);

        // Act
        ResponseEntity<GetComptesByClientResponseDTO> result = accountController.getComptesByClient(request);

        // Assert
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getComptes()).isEmpty();

        verify(accountService, times(1)).getComptesByClient(request);
    }

    @Test
    void createCEpargne_ShouldReturnOkWithCreatedAccount() {
        // Arrange
        CreateCEpargneRequestDTO request = new CreateCEpargneRequestDTO();
        request.setClientId(200L);
        request.setTauxInterets(2.5);
        request.setSolde(1000.0);
        request.setStatus("ACTIVE");

        CreateCEpargneResponseDTO response = new CreateCEpargneResponseDTO();
        CompteSoapInfoDTO compteDTO = new CompteSoapInfoDTO();
        compteDTO.setRib("EPARGNE123");
        response.setCompte(compteDTO);

        when(accountService.createCEpargne(any(CreateCEpargneRequestDTO.class))).thenReturn(response);

        // Act
        ResponseEntity<CreateCEpargneResponseDTO> result = accountController.createCEpargne(request);

        // Assert
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getCompte().getRib()).isEqualTo("EPARGNE123");

        verify(accountService, times(1)).createCEpargne(request);
    }

    @Test
    void validateRib_ShouldReturnOkWithValidationResult() {
        // Arrange
        ValidateRibRequestDTO request = new ValidateRibRequestDTO();
        request.setRib("TEST123");

        ValidateRibResponseDTO response = new ValidateRibResponseDTO();
        response.setIsValid(true);

        when(accountService.validateRib(any(ValidateRibRequestDTO.class))).thenReturn(response);

        // Act
        ResponseEntity<ValidateRibResponseDTO> result = accountController.validateRib(request);

        // Assert
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getIsValid()).isTrue();

        verify(accountService, times(1)).validateRib(request);
    }

    @Test
    void getClientIdByCompteId_ShouldReturnOk() {
        GetClientIdByCompteIdRequestDTO request = new GetClientIdByCompteIdRequestDTO();
        request.setCompteId(100L);

        GetClientIdByCompteIdResponseDTO response = new GetClientIdByCompteIdResponseDTO();
        response.setClientId(500L);

        when(accountService.getClientIdByCompteId(any(GetClientIdByCompteIdRequestDTO.class))).thenReturn(response);

        ResponseEntity<GetClientIdByCompteIdResponseDTO> result = accountController.getClientIdByCompteId(100L);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getClientId()).isEqualTo(500L);
        verify(accountService, times(1)).getClientIdByCompteId(any(GetClientIdByCompteIdRequestDTO.class));
    }

    @Test
    void getClientIdByRib_ShouldReturnOk() {
        GetClientIdByRibRequestDTO request = new GetClientIdByRibRequestDTO();
        request.setRib("RIB123");

        GetClientIdByRibResponseDTO response = new GetClientIdByRibResponseDTO();
        response.setClientId(600L);

        when(accountService.getClientIdByRib(any(GetClientIdByRibRequestDTO.class))).thenReturn(response);

        ResponseEntity<GetClientIdByRibResponseDTO> result = accountController.getClientIdByRib(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getClientId()).isEqualTo(600L);
        verify(accountService, times(1)).getClientIdByRib(request);
    }

    @Test
    void updateCompte_ShouldReturnOk() {
        UpdateCompteRequestDTO request = new UpdateCompteRequestDTO();
        request.setId(100L);

        UpdateCompteResponseDTO response = new UpdateCompteResponseDTO();

        when(accountService.updateCompte(any(UpdateCompteRequestDTO.class))).thenReturn(response);

        ResponseEntity<UpdateCompteResponseDTO> result = accountController.updateCompte(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(accountService, times(1)).updateCompte(request);
    }

    @Test
    void changeCompteStatus_ShouldReturnOk() {
        ChangeCompteStatusRequestDTO request = new ChangeCompteStatusRequestDTO();
        request.setCompteId(100L);
        request.setStatus("SUSPENDED");

        ChangeCompteStatusResponseDTO response = new ChangeCompteStatusResponseDTO();

        when(accountService.changeCompteStatus(any(ChangeCompteStatusRequestDTO.class))).thenReturn(response);

        ResponseEntity<ChangeCompteStatusResponseDTO> result = accountController.changeCompteStatus(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(accountService, times(1)).changeCompteStatus(request);
    }

    @Test
    void changeDotationStatus_ShouldReturnOk() {
        ChangeDotationStatusRequestDTO request = new ChangeDotationStatusRequestDTO();
        request.setAccountId(100L);

        ChangeDotationStatusResponseDTO response = new ChangeDotationStatusResponseDTO();

        when(accountService.changeDotationStatus(any(ChangeDotationStatusRequestDTO.class))).thenReturn(response);

        ResponseEntity<ChangeDotationStatusResponseDTO> result = accountController.changeDotationStatus(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(accountService, times(1)).changeDotationStatus(request);
    }

    @Test
    void deleteCompte_ShouldReturnOk() {
        DeleteCompteRequestDTO request = new DeleteCompteRequestDTO();
        request.setCompteId(100L);

        DeleteCompteResponseDTO response = new DeleteCompteResponseDTO();

        when(accountService.deleteCompte(any(DeleteCompteRequestDTO.class))).thenReturn(response);

        ResponseEntity<DeleteCompteResponseDTO> result = accountController.deleteCompte(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(accountService, times(1)).deleteCompte(request);
    }

    @Test
    void checkAccountExists_ShouldReturnOk() {
        CheckAccountExistsRequestDTO request = new CheckAccountExistsRequestDTO();
        request.setRib("RIB123");

        CheckAccountExistsResponseDTO response = new CheckAccountExistsResponseDTO();

        when(accountService.checkAccountExists(any(CheckAccountExistsRequestDTO.class))).thenReturn(response);

        ResponseEntity<CheckAccountExistsResponseDTO> result = accountController.checkAccountExists(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(accountService, times(1)).checkAccountExists(request);
    }

    @Test
    void verifyAccountOwnership_ShouldReturnOk() {
        VerifyAccountOwnershipRequestDTO request = new VerifyAccountOwnershipRequestDTO();
        request.setCompteId(100L);
        request.setClientId(500L);

        VerifyAccountOwnershipResponseDTO response = new VerifyAccountOwnershipResponseDTO();

        when(accountService.verifyAccountOwnership(any(VerifyAccountOwnershipRequestDTO.class))).thenReturn(response);

        ResponseEntity<VerifyAccountOwnershipResponseDTO> result = accountController.verifyAccountOwnership(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(accountService, times(1)).verifyAccountOwnership(request);
    }

    @Test
    void getAccountsByRibs_ShouldReturnOk() {
        GetAccountsByRibsRequestDTO request = new GetAccountsByRibsRequestDTO();
        GetAccountsByRibsResponseDTO response = new GetAccountsByRibsResponseDTO();

        when(accountService.getAccountsByRibs(any(GetAccountsByRibsRequestDTO.class))).thenReturn(response);

        ResponseEntity<GetAccountsByRibsResponseDTO> result = accountController.getAccountsByRibs(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(accountService, times(1)).getAccountsByRibs(request);
    }

    @Test
    void getAccountStatement_ShouldReturnOk() {
        GetAccountStatementRequestDTO request = new GetAccountStatementRequestDTO();
        GetAccountStatementResponseDTO response = new GetAccountStatementResponseDTO();

        when(accountService.getAccountStatement(any(GetAccountStatementRequestDTO.class))).thenReturn(response);

        ResponseEntity<GetAccountStatementResponseDTO> result = accountController.getAccountStatement(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(accountService, times(1)).getAccountStatement(request);
    }

    @Test
    void getAccountSummary_ShouldReturnOk() {
        Long clientId = 200L;
        GetAccountSummaryResponseDTO response = new GetAccountSummaryResponseDTO();

        when(accountService.getAccountSummary(any(GetAccountSummaryRequestDTO.class))).thenReturn(response);

        ResponseEntity<GetAccountSummaryResponseDTO> result = accountController.getAccountSummary(clientId);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(accountService, times(1)).getAccountSummary(any(GetAccountSummaryRequestDTO.class));
    }

    @Test
    void getCEpargneById_ShouldReturnOk() {
        Long compteId = 100L;
        GetCEpargneByIdResponseDTO response = new GetCEpargneByIdResponseDTO();

        when(accountService.getCEpargneById(any(GetCEpargneByIdRequestDTO.class))).thenReturn(response);

        ResponseEntity<GetCEpargneByIdResponseDTO> result = accountController.getCEpargneById(compteId);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(accountService, times(1)).getCEpargneById(any(GetCEpargneByIdRequestDTO.class));
    }

    @Test
    void getAllCEpargneByClient_ShouldReturnOk() {
        GetAllCEpargneByClientRequestDTO request = new GetAllCEpargneByClientRequestDTO();
        GetAllCEpargneByClientResponseDTO response = new GetAllCEpargneByClientResponseDTO();

        when(accountService.getAllCEpargneByClient(any(GetAllCEpargneByClientRequestDTO.class))).thenReturn(response);

        ResponseEntity<GetAllCEpargneByClientResponseDTO> result = accountController.getAllCEpargneByClient(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(accountService, times(1)).getAllCEpargneByClient(request);
    }

    @Test
    void getAllCEpargneByInterestRate_ShouldReturnOk() {
        GetAllCEpargneByInterestRateRequestDTO request = new GetAllCEpargneByInterestRateRequestDTO();
        GetAllCEpargneByInterestRateResponseDTO response = new GetAllCEpargneByInterestRateResponseDTO();

        when(accountService.getAllCEpargneByInterestRate(any(GetAllCEpargneByInterestRateRequestDTO.class)))
                .thenReturn(response);

        ResponseEntity<GetAllCEpargneByInterestRateResponseDTO> result = accountController
                .getAllCEpargneByInterestRate(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(accountService, times(1)).getAllCEpargneByInterestRate(request);
    }

    @Test
    void getCEpargneQuarterlyInterests_ShouldReturnOk() {
        Long compteId = 100L;
        GetCEpargneQuarterlyInterestsResponseDTO response = new GetCEpargneQuarterlyInterestsResponseDTO();

        when(accountService.getCEpargneQuarterlyInterests(any(GetCEpargneQuarterlyInterestsRequestDTO.class)))
                .thenReturn(response);

        ResponseEntity<GetCEpargneQuarterlyInterestsResponseDTO> result = accountController
                .getCEpargneQuarterlyInterests(compteId);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(accountService, times(1))
                .getCEpargneQuarterlyInterests(any(GetCEpargneQuarterlyInterestsRequestDTO.class));
    }

    @Test
    void updateCEpargneInterestRate_ShouldReturnOk() {
        UpdateCEpargneInterestRateRequestDTO request = new UpdateCEpargneInterestRateRequestDTO();
        UpdateCEpargneInterestRateResponseDTO response = new UpdateCEpargneInterestRateResponseDTO();

        when(accountService.updateCEpargneInterestRate(any(UpdateCEpargneInterestRateRequestDTO.class)))
                .thenReturn(response);

        ResponseEntity<UpdateCEpargneInterestRateResponseDTO> result = accountController
                .updateCEpargneInterestRate(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(accountService, times(1)).updateCEpargneInterestRate(request);
    }

    @Test
    void applyCEpargneInterests_ShouldReturnOk() {
        ApplyCEpargneInterestsRequestDTO request = new ApplyCEpargneInterestsRequestDTO();
        ApplyCEpargneInterestsResponseDTO response = new ApplyCEpargneInterestsResponseDTO();

        when(accountService.applyCEpargneInterests(any(ApplyCEpargneInterestsRequestDTO.class))).thenReturn(response);

        ResponseEntity<ApplyCEpargneInterestsResponseDTO> result = accountController.applyCEpargneInterests(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(accountService, times(1)).applyCEpargneInterests(request);
    }

    @Test
    void calculateCEpargneInterests_ShouldReturnOk() {
        CalculateCEpargneInterestsRequestDTO request = new CalculateCEpargneInterestsRequestDTO();
        CalculateCEpargneInterestsResponseDTO response = new CalculateCEpargneInterestsResponseDTO();

        when(accountService.calculateCEpargneInterests(any(CalculateCEpargneInterestsRequestDTO.class)))
                .thenReturn(response);

        ResponseEntity<CalculateCEpargneInterestsResponseDTO> result = accountController
                .calculateCEpargneInterests(request);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(accountService, times(1)).calculateCEpargneInterests(request);
    }
}
