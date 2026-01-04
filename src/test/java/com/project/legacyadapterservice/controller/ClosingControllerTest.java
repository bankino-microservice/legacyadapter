package com.project.legacyadapterservice.controller;

import com.project.legacyadapterservice.dto.rest.*;
import com.project.legacyadapterservice.service.ClosingAdapterService;
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
class ClosingControllerTest {

    @Mock
    private ClosingAdapterService closingService;

    @InjectMocks
    private ClosingController closingController;

    @Test
    void closeQuinzaine_ShouldReturnOkWithResponse() {
        // Arrange
        ClotureQuinzaineRequestDTO request = new ClotureQuinzaineRequestDTO();
        request.setRib("RIB123456789");
        request.setYear(2026);
        request.setQuaiznine(1);

        ClotureAnnuelleResponseDTO response = new ClotureAnnuelleResponseDTO();
        response.setStatus("SUCCESS");
        response.setMessage("Quinzaine closed successfully");

        when(closingService.closeQuinzaine(any(ClotureQuinzaineRequestDTO.class))).thenReturn(response);

        // Act
        ResponseEntity<ClotureAnnuelleResponseDTO> result = closingController.closeQuinzaine(request);

        // Assert
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getStatus()).isEqualTo("SUCCESS");

        verify(closingService, times(1)).closeQuinzaine(request);
    }

    @Test
    void closeAnnual_ShouldReturnOkWithResponse() {
        // Arrange
        CalculateAnnualeRequestDTO request = new CalculateAnnualeRequestDTO();
        request.setRib("RIB123456789");
        request.setYear(2026);

        ClotureAnnuelleResponseDTO response = new ClotureAnnuelleResponseDTO();
        response.setStatus("SUCCESS");
        response.setMessage("Annual closing completed");

        when(closingService.calculateAnnual(any(CalculateAnnualeRequestDTO.class))).thenReturn(response);

        // Act
        ResponseEntity<ClotureAnnuelleResponseDTO> result = closingController.closeAnnual(request);

        // Assert
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getStatus()).isEqualTo("SUCCESS");

        verify(closingService, times(1)).calculateAnnual(request);
    }
}
