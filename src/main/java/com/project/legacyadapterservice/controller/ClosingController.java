package com.project.legacyadapterservice.controller;

import com.project.legacyadapterservice.dto.rest.CalculateAnnualeRequestDTO;
import com.project.legacyadapterservice.dto.rest.ClotureAnnuelleResponseDTO;
import com.project.legacyadapterservice.dto.rest.ClotureQuinzaineRequestDTO;
import com.project.legacyadapterservice.service.ClosingAdapterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operations")
public class ClosingController {

    private final ClosingAdapterService closingService;

    public ClosingController(ClosingAdapterService closingService) {
        this.closingService = closingService;
    }

    @PostMapping("/close-quinzaine")
    public ResponseEntity<ClotureAnnuelleResponseDTO> closeQuinzaine(@RequestBody ClotureQuinzaineRequestDTO request) {
        return ResponseEntity.ok(closingService.closeQuinzaine(request));
    }

    @PostMapping("/close-annual")
    public ResponseEntity<ClotureAnnuelleResponseDTO> closeAnnual(@RequestBody CalculateAnnualeRequestDTO request) {
        return ResponseEntity.ok(closingService.calculateAnnual(request));
    }
}