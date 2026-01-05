package com.project.legacyadapterservice.controller;

import com.project.legacyadapterservice.dto.rest.*;
import com.project.legacyadapterservice.service.VirementAdapterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/virements")
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

    @GetMapping("/client/{clientRib}")
    public ResponseEntity<GetAllVirementsClientResponseDTO> getAllVirementsClient(@PathVariable String clientRib) {
        return ResponseEntity.ok(virementService.getAllVirementsClient(clientRib));
    }

    @GetMapping("/emis/{clientRib}")
    public ResponseEntity<GetVirementsEmisResponseDTO> getVirementsEmis(@PathVariable String clientRib) {
        return ResponseEntity.ok(virementService.getVirementsEmis(clientRib));
    }

    @GetMapping("/recus/{clientRib}")
    public ResponseEntity<GetVirementsRecusResponseDTO> getVirementsRecus(@PathVariable String clientRib) {
        return ResponseEntity.ok(virementService.getVirementsRecus(clientRib));
    }

}