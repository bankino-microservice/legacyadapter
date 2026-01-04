package com.project.legacyadapterservice.service;

import com.ebank.ebanking2.soap.dto.CEpargneResponseDTO;
import com.ebank.ebanking2.soap.dto.DepotCEpargneRequest;
import com.ebank.ebanking2.soap.dto.RetraitCEpargneRequest;
import com.project.legacyadapterservice.dto.rest.DepotRetraitCEpargneRequestDTO;
import com.project.legacyadapterservice.mapper.SavingsMapper;
import com.project.legacyadapterservice.soap.client.CoreBankingSoapClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SavingsAdapterServiceTest {

    @Mock
    private CoreBankingSoapClient soapClient;

    @Mock
    private SavingsMapper mapper;

    @InjectMocks
    private SavingsAdapterService savingsAdapterService;

    @Test
    void deposit_ShouldCallSoapClientWithCorrectParameters() {
        // Arrange
        DepotRetraitCEpargneRequestDTO restRequest = new DepotRetraitCEpargneRequestDTO();
        restRequest.setEpargnerib("EPARGNE123");
        restRequest.setCourantrib("COURANT123");
        restRequest.setMontant(500.0);

        DepotCEpargneRequest soapRequest = new DepotCEpargneRequest();
        soapRequest.setEpargnerib("EPARGNE123");
        soapRequest.setCourantrib("COURANT123");
        soapRequest.setMontant(500.0);
        soapRequest.setYear(2024);
        soapRequest.setMonth(1);
        soapRequest.setDay(15);

        CEpargneResponseDTO soapResponse = new CEpargneResponseDTO();
        soapResponse.setRib("EPARGNE123");
        soapResponse.setStatus("SUCCESS");
        soapResponse.setMessage("Deposit successful");

        com.project.legacyadapterservice.dto.rest.CEpargneResponseDTO restResponse = new com.project.legacyadapterservice.dto.rest.CEpargneResponseDTO();
        restResponse.setEpargnerib("EPARGNE123");
        restResponse.setCourantrib("COURANT123");
        restResponse.setStatus("SUCCESS");
        restResponse.setMessage("Deposit successful");

        when(mapper.toSoapDepot(restRequest)).thenReturn(soapRequest);
        when(soapClient.depositCEpargne("EPARGNE123", "COURANT123", 500.0, 2024, 1, 15)).thenReturn(soapResponse);
        when(mapper.toRestDto(soapResponse)).thenReturn(restResponse);

        // Act
        com.project.legacyadapterservice.dto.rest.CEpargneResponseDTO result = savingsAdapterService
                .deposit(restRequest);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getStatus()).isEqualTo("SUCCESS");
        assertThat(result.getMessage()).isEqualTo("Deposit successful");

        verify(mapper).toSoapDepot(restRequest);
        verify(soapClient).depositCEpargne("EPARGNE123", "COURANT123", 500.0, 2024, 1, 15);
        verify(mapper).toRestDto(soapResponse);
    }

    @Test
    void withdraw_ShouldCallSoapClientWithCorrectParameters() {
        // Arrange
        DepotRetraitCEpargneRequestDTO restRequest = new DepotRetraitCEpargneRequestDTO();
        restRequest.setEpargnerib("EPARGNE123");
        restRequest.setCourantrib("COURANT123");
        restRequest.setMontant(300.0);

        RetraitCEpargneRequest soapRequest = new RetraitCEpargneRequest();
        soapRequest.setEpargnerib("EPARGNE123");
        soapRequest.setCourantrib("COURANT123");
        soapRequest.setMontant(300.0);
        soapRequest.setYear(2024);
        soapRequest.setMonth(1);
        soapRequest.setDay(15);

        CEpargneResponseDTO soapResponse = new CEpargneResponseDTO();
        soapResponse.setRib("EPARGNE123");
        soapResponse.setStatus("SUCCESS");

        com.project.legacyadapterservice.dto.rest.CEpargneResponseDTO restResponse = new com.project.legacyadapterservice.dto.rest.CEpargneResponseDTO();
        restResponse.setEpargnerib("EPARGNE123");
        restResponse.setCourantrib("COURANT123");
        restResponse.setStatus("SUCCESS");

        when(mapper.toSoapRetrait(restRequest)).thenReturn(soapRequest);
        when(soapClient.withdrawCEpargne("EPARGNE123", "COURANT123", 300.0, 2024, 1, 15)).thenReturn(soapResponse);
        when(mapper.toRestDto(soapResponse)).thenReturn(restResponse);

        // Act
        com.project.legacyadapterservice.dto.rest.CEpargneResponseDTO result = savingsAdapterService
                .withdraw(restRequest);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getStatus()).isEqualTo("SUCCESS");

        verify(mapper).toSoapRetrait(restRequest);
        verify(soapClient).withdrawCEpargne("EPARGNE123", "COURANT123", 300.0, 2024, 1, 15);
        verify(mapper).toRestDto(soapResponse);
    }
}
