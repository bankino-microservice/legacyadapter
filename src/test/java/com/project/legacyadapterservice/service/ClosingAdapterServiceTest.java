package com.project.legacyadapterservice.service;

import com.ebank.ebanking2.soap.dto.CalculateAnnuealeRequest;
import com.ebank.ebanking2.soap.dto.ClotureAnnuelleResponse;
import com.ebank.ebanking2.soap.dto.CloturequainzineRequest;
import com.project.legacyadapterservice.dto.rest.*;
import com.project.legacyadapterservice.mapper.ClosingMapper;
import com.project.legacyadapterservice.soap.client.CoreBankingSoapClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClosingAdapterServiceTest {

    @Mock
    private CoreBankingSoapClient soapClient;

    @Mock
    private ClosingMapper mapper;

    @InjectMocks
    private ClosingAdapterService closingAdapterService;

    @Test
    void closeQuinzaine_ShouldCallSoapClientAndReturnMappedResponse() {
        // Arrange
        ClotureQuinzaineRequestDTO request = new ClotureQuinzaineRequestDTO();
        request.setRib("RIB123456789");
        request.setYear(2026);
        request.setQuaiznine(1);

        CloturequainzineRequest soapRequest = new CloturequainzineRequest();
        soapRequest.setRib("RIB123456789");
        soapRequest.setYear(2026);
        soapRequest.setQuaiznine(1);

        ClotureAnnuelleResponse soapResponse = new ClotureAnnuelleResponse();
        soapResponse.setStatus("SUCCESS");
        soapResponse.setMessage("Quinzaine closed successfully");

        ClotureAnnuelleResponseDTO restResponse = new ClotureAnnuelleResponseDTO();
        restResponse.setStatus("SUCCESS");
        restResponse.setMessage("Quinzaine closed successfully");

        when(mapper.toSoapDto(request)).thenReturn(soapRequest);
        when(soapClient.closeQuinzaine("RIB123456789", 2026, 1)).thenReturn(soapResponse);
        when(mapper.toRestDto(soapResponse)).thenReturn(restResponse);

        // Act
        ClotureAnnuelleResponseDTO result = closingAdapterService.closeQuinzaine(request);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getStatus()).isEqualTo("SUCCESS");
        assertThat(result.getMessage()).isEqualTo("Quinzaine closed successfully");

        verify(mapper).toSoapDto(request);
        verify(soapClient).closeQuinzaine("RIB123456789", 2026, 1);
        verify(mapper).toRestDto(soapResponse);
    }

    @Test
    void calculateAnnual_ShouldCallSoapClientAndReturnMappedResponse() {
        // Arrange
        CalculateAnnualeRequestDTO request = new CalculateAnnualeRequestDTO();
        request.setRib("RIB123456789");
        request.setYear(2026);

        CalculateAnnuealeRequest soapRequest = new CalculateAnnuealeRequest();
        soapRequest.setRib("RIB123456789");
        soapRequest.setYear(2026);

        ClotureAnnuelleResponse soapResponse = new ClotureAnnuelleResponse();
        soapResponse.setStatus("SUCCESS");
        soapResponse.setMessage("Annual closing completed");

        ClotureAnnuelleResponseDTO restResponse = new ClotureAnnuelleResponseDTO();
        restResponse.setStatus("SUCCESS");
        restResponse.setMessage("Annual closing completed");

        when(mapper.toSoapDto(request)).thenReturn(soapRequest);
        when(soapClient.closeAnnual("RIB123456789", 2026)).thenReturn(soapResponse);
        when(mapper.toRestDto(soapResponse)).thenReturn(restResponse);

        // Act
        ClotureAnnuelleResponseDTO result = closingAdapterService.calculateAnnual(request);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getStatus()).isEqualTo("SUCCESS");
        assertThat(result.getMessage()).isEqualTo("Annual closing completed");

        verify(mapper).toSoapDto(request);
        verify(soapClient).closeAnnual("RIB123456789", 2026);
        verify(mapper).toRestDto(soapResponse);
    }
}
