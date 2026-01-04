package com.project.legacyadapterservice.controller;

import com.project.legacyadapterservice.dto.rest.*;
import com.project.legacyadapterservice.service.VirementAdapterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VirementControllerTest {

    @Mock
    private VirementAdapterService virementService;

    @InjectMocks
    private VirementController virementController;

    private ExecuteVirementRequestDTO executeRequest;
    private ExecuteVirementResponseDTO executeResponse;

    @BeforeEach
    void setUp() {
        executeRequest = new ExecuteVirementRequestDTO();
        executeRequest.setRibEmetteur("EMT123456789");
        executeRequest.setRibRecepteur("RCP987654321");
        executeRequest.setMontant(1000.00);
        executeRequest.setType(Type.NORMAL);

        VirementSoapInfoDTO virementInfo = new VirementSoapInfoDTO();
        virementInfo.setId(1L);
        virementInfo.setMontant(1000.00);
        virementInfo.setType("NORMAL");

        executeResponse = new ExecuteVirementResponseDTO();
        executeResponse.setVirementInfo(virementInfo);

        ServiceStatusDTO serviceStatus = new ServiceStatusDTO();
        serviceStatus.setStatusCode("200");
        serviceStatus.setMessage("Success");
        executeResponse.setServiceStatus(serviceStatus);
    }

    @Test
    void executeVirement_ShouldReturnOkWithResponse() {
        // Arrange
        when(virementService.executeVirement(any(ExecuteVirementRequestDTO.class)))
                .thenReturn(executeResponse);

        // Act
        ResponseEntity<ExecuteVirementResponseDTO> response = virementController.executeVirement(executeRequest);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getVirementInfo().getId()).isEqualTo(1L);
        assertThat(response.getBody().getServiceStatus().getStatusCode()).isEqualTo("200");

        verify(virementService, times(1)).executeVirement(executeRequest);
    }

    @Test
    void getVirementById_ShouldReturnOkWithVirement() {
        // Arrange
        Long virementId = 1L;
        VirementSoapInfoDTO virementInfo = new VirementSoapInfoDTO();
        virementInfo.setId(virementId);
        virementInfo.setMontant(1000.00);

        when(virementService.getVirementById(anyLong())).thenReturn(virementInfo);

        // Act
        ResponseEntity<VirementSoapInfoDTO> response = virementController.getVirementById(virementId);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(virementId);

        verify(virementService, times(1)).getVirementById(virementId);
    }
}
