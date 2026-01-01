package com.project.legacyadapterservice.controller;
import com.project.legacyadapterservice.dto.rest.CEpargneResponseDTO;
import com.project.legacyadapterservice.dto.rest.DepotRetraitCEpargneRequestDTO;
import com.project.legacyadapterservice.service.SavingsAdapterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/savings")
public class SavingsController {

    private final SavingsAdapterService savingsService;

    public SavingsController(SavingsAdapterService savingsService) {
        this.savingsService = savingsService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<CEpargneResponseDTO> deposit(@RequestBody DepotRetraitCEpargneRequestDTO request) {
        return ResponseEntity.ok(savingsService.deposit(request));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<CEpargneResponseDTO> withdraw(@RequestBody DepotRetraitCEpargneRequestDTO request) {
        return ResponseEntity.ok(savingsService.withdraw(request));
    }


}