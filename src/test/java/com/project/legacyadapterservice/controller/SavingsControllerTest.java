package com.project.legacyadapterservice.controller;

import com.project.legacyadapterservice.dto.rest.CEpargneResponseDTO;
import com.project.legacyadapterservice.dto.rest.DepotRetraitCEpargneRequestDTO;
import com.project.legacyadapterservice.service.SavingsAdapterService;
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
class SavingsControllerTest {

    @Mock
    private SavingsAdapterService savingsService;

    @InjectMocks
    private SavingsController savingsController;

    @Test
    void deposit_ShouldReturnOkWithResponse() {
        // Arrange
        DepotRetraitCEpargneRequestDTO request = new DepotRetraitCEpargneRequestDTO();
        request.setEpargnerib("EPARGNE123");
        request.setCourantrib("COURANT123");
        request.setMontant(500.0);

        CEpargneResponseDTO response = new CEpargneResponseDTO();
        response.setStatus("SUCCESS");
        response.setMessage("Deposit successful");

        when(savingsService.deposit(any(DepotRetraitCEpargneRequestDTO.class))).thenReturn(response);

        // Act
        ResponseEntity<CEpargneResponseDTO> result = savingsController.deposit(request);

        // Assert
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getStatus()).isEqualTo("SUCCESS");

        verify(savingsService, times(1)).deposit(request);
    }

    @Test
    void withdraw_ShouldReturnOkWithResponse() {
        // Arrange
        DepotRetraitCEpargneRequestDTO request = new DepotRetraitCEpargneRequestDTO();
        request.setEpargnerib("EPARGNE123");
        request.setCourantrib("COURANT123");
        request.setMontant(300.0);

        CEpargneResponseDTO response = new CEpargneResponseDTO();
        response.setStatus("SUCCESS");
        response.setMessage("Withdrawal successful");

        when(savingsService.withdraw(any(DepotRetraitCEpargneRequestDTO.class))).thenReturn(response);

        // Act
        ResponseEntity<CEpargneResponseDTO> result = savingsController.withdraw(request);

        // Assert
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getStatus()).isEqualTo("SUCCESS");

        verify(savingsService, times(1)).withdraw(request);
    }
}
