package com.project.legacyadapterservice.controller;

import com.project.legacyadapterservice.dto.rest.ExecuteVirementRequestDTO;
import com.project.legacyadapterservice.dto.rest.ExecuteVirementResponseDTO;
import com.project.legacyadapterservice.dto.rest.VirementSoapInfoDTO;
import com.project.legacyadapterservice.service.VirementAdapterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/virement")
public class VirementController {

    private final VirementAdapterService virementService;

    public VirementController(VirementAdapterService virementService) {
        this.virementService = virementService;
    }

    @PostMapping("/execute")
    public ResponseEntity<ExecuteVirementResponseDTO> executeVirement(@RequestBody ExecuteVirementRequestDTO request) {
        return ResponseEntity.ok(virementService.executeVirement(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VirementSoapInfoDTO> getVirementById(@PathVariable Long id) {
        return ResponseEntity.ok(virementService.getVirementById(id));
    }
}