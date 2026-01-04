package com.project.legacyadapterservice.service;

import com.ebank.ebanking2.soap.dto.*;
import com.project.legacyadapterservice.dto.rest.*;
import com.project.legacyadapterservice.mapper.VirementMapper;
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
class VirementAdapterServiceTest {

    @Mock
    private CoreBankingSoapClient soapClient;

    @Mock
    private VirementMapper mapper;

    @InjectMocks
    private VirementAdapterService virementAdapterService;

    private ExecuteVirementRequestDTO executeRequest;
    private ExecuteVirementResponse soapExecuteResponse;
    private ExecuteVirementResponseDTO restExecuteResponse;
    private VirementSoapInfo soapVirementInfo;
    private VirementSoapInfoDTO restVirementInfo;

    @BeforeEach
    void setUp() {
        executeRequest = new ExecuteVirementRequestDTO();
        executeRequest.setRibEmetteur("EMT123456789");
        executeRequest.setRibRecepteur("RCP987654321");
        executeRequest.setMontant(1000.00);
        executeRequest.setType(com.project.legacyadapterservice.dto.rest.Type.NORMAL);

        soapVirementInfo = new VirementSoapInfo();
        soapVirementInfo.setId(1L);
        soapVirementInfo.setCompteEmetteurRib("EMT123456789");
        soapVirementInfo.setCompteRecepteurRib("RCP987654321");
        soapVirementInfo.setMontant(1000.00);
        soapVirementInfo.setType("NORMAL");

        soapExecuteResponse = new ExecuteVirementResponse();
        soapExecuteResponse.setVirementInfo(soapVirementInfo);

        restVirementInfo = new VirementSoapInfoDTO();
        restVirementInfo.setId(1L);
        restVirementInfo.setCompteEmetteurRib("EMT123456789");
        restVirementInfo.setCompteRecepteurRib("RCP987654321");
        restVirementInfo.setMontant(1000.00);
        restVirementInfo.setType("NORMAL");

        restExecuteResponse = new ExecuteVirementResponseDTO();
        restExecuteResponse.setVirementInfo(restVirementInfo);
        ServiceStatusDTO serviceStatus = new ServiceStatusDTO();
        serviceStatus.setStatusCode("200");
        serviceStatus.setMessage("Success");
        restExecuteResponse.setServiceStatus(serviceStatus);
    }

    @Test
    void executeVirement_ShouldCallSoapClientAndReturnMappedResponse() {
        // Arrange
        ExecuteVirementRequest soapRequest = new ExecuteVirementRequest();
        soapRequest.setRibEmetteur("EMT123456789");
        soapRequest.setRibRecepteur("RCP987654321");
        soapRequest.setMontant(1000.00);
        soapRequest.setType(com.ebank.ebanking2.soap.dto.Type.NORMAL);

        when(mapper.toSoapType(com.project.legacyadapterservice.dto.rest.Type.NORMAL))
                .thenReturn(com.ebank.ebanking2.soap.dto.Type.NORMAL);
        when(mapper.toSoapDto(executeRequest)).thenReturn(soapRequest);
        when(soapClient.executeVirement("EMT123456789", "RCP987654321", 1000.00,
                com.ebank.ebanking2.soap.dto.Type.NORMAL))
                .thenReturn(soapExecuteResponse);
        when(mapper.toRestDto(soapExecuteResponse))
                .thenReturn(restExecuteResponse);

        // Act
        ExecuteVirementResponseDTO result = virementAdapterService.executeVirement(executeRequest);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getVirementInfo()).isNotNull();
        assertThat(result.getVirementInfo().getId()).isEqualTo(1L);
        assertThat(result.getServiceStatus().getStatusCode()).isEqualTo("200");

        verify(mapper).toSoapType(com.project.legacyadapterservice.dto.rest.Type.NORMAL);
        verify(mapper).toSoapDto(executeRequest);
        verify(soapClient).executeVirement("EMT123456789", "RCP987654321", 1000.00,
                com.ebank.ebanking2.soap.dto.Type.NORMAL);
        verify(mapper).toRestDto(soapExecuteResponse);
    }

    @Test
    void getVirementById_ShouldCallSoapClientAndReturnMappedInfo() {
        // Arrange
        Long virementId = 1L;
        GetVirementByIdResponse soapResponse = new GetVirementByIdResponse();
        soapResponse.setVirementInfo(soapVirementInfo);

        when(soapClient.getVirementById(anyLong())).thenReturn(soapResponse);
        when(mapper.toRestDto(any(VirementSoapInfo.class))).thenReturn(restVirementInfo);

        // Act
        VirementSoapInfoDTO result = virementAdapterService.getVirementById(virementId);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getMontant()).isEqualTo(1000.00);

        verify(soapClient).getVirementById(1L);
        verify(mapper).toRestDto(soapVirementInfo);
    }
}
