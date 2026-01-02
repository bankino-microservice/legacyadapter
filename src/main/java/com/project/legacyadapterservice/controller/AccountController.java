package com.project.legacyadapterservice.controller;

import com.project.legacyadapterservice.dto.rest.*;
import com.project.legacyadapterservice.service.AccountAdapterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountAdapterService accountService;

    public AccountController(AccountAdapterService accountService) {
        this.accountService = accountService;
    }

    // ===== ACCOUNT QUERY ENDPOINTS =====

    @PostMapping("/by-status-type")
    public ResponseEntity<GetComptesByStatusTypeResponseDTO> getComptesByStatusType(
            @RequestBody GetComptesByStatusTypeRequestDTO request) {
        return ResponseEntity.ok(accountService.getComptesByStatusType(request));
    }

    @PostMapping("/solde")
    public ResponseEntity<GetSoldeResponseDTO> getSolde(@RequestBody GetSoldeRequestDTO request) {
        return ResponseEntity.ok(accountService.getSolde(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCompteByIdResponseDTO> getCompteById(@PathVariable Long id) {
        GetCompteByIdRequestDTO request = new GetCompteByIdRequestDTO();
        request.setId(id);
        return ResponseEntity.ok(accountService.getCompteById(request));
    }

    @PostMapping("/by-client")
    public ResponseEntity<GetComptesByClientResponseDTO> getComptesByClient(
            @RequestBody GetComptesByClientRequestDTO request) {
        return ResponseEntity.ok(accountService.getComptesByClient(request));
    }

    @GetMapping("/client-id/by-compte/{compteId}")
    public ResponseEntity<GetClientIdByCompteIdResponseDTO> getClientIdByCompteId(
            @PathVariable Long compteId) {
        GetClientIdByCompteIdRequestDTO request = new GetClientIdByCompteIdRequestDTO();
        request.setCompteId(compteId);
        return ResponseEntity.ok(accountService.getClientIdByCompteId(request));
    }

    @PostMapping("/client-id/by-rib")
    public ResponseEntity<GetClientIdByRibResponseDTO> getClientIdByRib(
            @RequestBody GetClientIdByRibRequestDTO request) {
        return ResponseEntity.ok(accountService.getClientIdByRib(request));
    }

    // ===== ACCOUNT CREATION ENDPOINTS =====

    @PostMapping("/create/ccourant")
    public ResponseEntity<CreateCCourantResponseDTO> createCCourant(
            @RequestBody CreateCCourantRequestDTO request) {
        return ResponseEntity.ok(accountService.createCCourant(request));
    }

    @PostMapping("/create/cepargne")
    public ResponseEntity<CreateCEpargneResponseDTO> createCEpargne(
            @RequestBody CreateCEpargneRequestDTO request) {
        return ResponseEntity.ok(accountService.createCEpargne(request));
    }

    // ===== ACCOUNT UPDATE ENDPOINTS =====

    @PutMapping("/update")
    public ResponseEntity<UpdateCompteResponseDTO> updateCompte(
            @RequestBody UpdateCompteRequestDTO request) {
        return ResponseEntity.ok(accountService.updateCompte(request));
    }

    @PutMapping("/status")
    public ResponseEntity<ChangeCompteStatusResponseDTO> changeCompteStatus(
            @RequestBody ChangeCompteStatusRequestDTO request) {
        return ResponseEntity.ok(accountService.changeCompteStatus(request));
    }

    @PutMapping("/dotation-status")
    public ResponseEntity<ChangeDotationStatusResponseDTO> changeDotationStatus(
            @RequestBody ChangeDotationStatusRequestDTO request) {
        return ResponseEntity.ok(accountService.changeDotationStatus(request));
    }

    // ===== ACCOUNT DELETION ENDPOINTS =====

    @DeleteMapping("/delete")
    public ResponseEntity<DeleteCompteResponseDTO> deleteCompte(
            @RequestBody DeleteCompteRequestDTO request) {
        return ResponseEntity.ok(accountService.deleteCompte(request));
    }

    // ===== ACCOUNT VALIDATION ENDPOINTS =====

    @PostMapping("/validate-rib")
    public ResponseEntity<ValidateRibResponseDTO> validateRib(
            @RequestBody ValidateRibRequestDTO request) {
        return ResponseEntity.ok(accountService.validateRib(request));
    }

    @PostMapping("/check-exists")
    public ResponseEntity<CheckAccountExistsResponseDTO> checkAccountExists(
            @RequestBody CheckAccountExistsRequestDTO request) {
        return ResponseEntity.ok(accountService.checkAccountExists(request));
    }

    @PostMapping("/verify-ownership")
    public ResponseEntity<VerifyAccountOwnershipResponseDTO> verifyAccountOwnership(
            @RequestBody VerifyAccountOwnershipRequestDTO request) {
        return ResponseEntity.ok(accountService.verifyAccountOwnership(request));
    }

    // ===== ACCOUNT BULK ENDPOINTS =====

    @PostMapping("/by-ribs")
    public ResponseEntity<GetAccountsByRibsResponseDTO> getAccountsByRibs(
            @RequestBody GetAccountsByRibsRequestDTO request) {
        return ResponseEntity.ok(accountService.getAccountsByRibs(request));
    }

    // ===== ACCOUNT SECURITY ENDPOINTS =====

    @PostMapping("/block")
    public ResponseEntity<BlockAccountResponseDTO> blockAccount(
            @RequestBody BlockAccountRequestDTO request) {
        return ResponseEntity.ok(accountService.blockAccount(request));
    }

    @PostMapping("/unblock")
    public ResponseEntity<UnblockAccountResponseDTO> unblockAccount(
            @RequestBody UnblockAccountRequestDTO request) {
        return ResponseEntity.ok(accountService.unblockAccount(request));
    }

    // ===== ACCOUNT REPORTING ENDPOINTS =====

    @PostMapping("/statement")
    public ResponseEntity<GetAccountStatementResponseDTO> getAccountStatement(
            @RequestBody GetAccountStatementRequestDTO request) {
        return ResponseEntity.ok(accountService.getAccountStatement(request));
    }

    @GetMapping("/summary/client/{clientId}")
    public ResponseEntity<GetAccountSummaryResponseDTO> getAccountSummary(
            @PathVariable Long clientId) {
        GetAccountSummaryRequestDTO request = new GetAccountSummaryRequestDTO();
        request.setClientId(clientId);
        return ResponseEntity.ok(accountService.getAccountSummary(request));
    }

    // ===== CEPARGNE SPECIFIC ENDPOINTS =====

    @GetMapping("/cepargne/{compteId}/quarterly-interests")
    public ResponseEntity<GetCEpargneQuarterlyInterestsResponseDTO> getCEpargneQuarterlyInterests(
            @PathVariable Long compteId) {
        GetCEpargneQuarterlyInterestsRequestDTO request = new GetCEpargneQuarterlyInterestsRequestDTO();
        request.setCompteId(compteId);
        return ResponseEntity.ok(accountService.getCEpargneQuarterlyInterests(request));
    }

    @PutMapping("/cepargne/interest-rate")
    public ResponseEntity<UpdateCEpargneInterestRateResponseDTO> updateCEpargneInterestRate(
            @RequestBody UpdateCEpargneInterestRateRequestDTO request) {
        return ResponseEntity.ok(accountService.updateCEpargneInterestRate(request));
    }

    @PostMapping("/cepargne/apply-interests")
    public ResponseEntity<ApplyCEpargneInterestsResponseDTO> applyCEpargneInterests(
            @RequestBody ApplyCEpargneInterestsRequestDTO request) {
        return ResponseEntity.ok(accountService.applyCEpargneInterests(request));
    }

    @PostMapping("/cepargne/calculate-interests")
    public ResponseEntity<CalculateCEpargneInterestsResponseDTO> calculateCEpargneInterests(
            @RequestBody CalculateCEpargneInterestsRequestDTO request) {
        return ResponseEntity.ok(accountService.calculateCEpargneInterests(request));
    }

    @GetMapping("/cepargne/{compteId}")
    public ResponseEntity<GetCEpargneByIdResponseDTO> getCEpargneById(
            @PathVariable Long compteId) {
        GetCEpargneByIdRequestDTO request = new GetCEpargneByIdRequestDTO();
        request.setCompteId(compteId);
        return ResponseEntity.ok(accountService.getCEpargneById(request));
    }

    @PostMapping("/cepargne/by-client")
    public ResponseEntity<GetAllCEpargneByClientResponseDTO> getAllCEpargneByClient(
            @RequestBody GetAllCEpargneByClientRequestDTO request) {
        return ResponseEntity.ok(accountService.getAllCEpargneByClient(request));
    }

    @PostMapping("/cepargne/by-interest-rate")
    public ResponseEntity<GetAllCEpargneByInterestRateResponseDTO> getAllCEpargneByInterestRate(
            @RequestBody GetAllCEpargneByInterestRateRequestDTO request) {
        return ResponseEntity.ok(accountService.getAllCEpargneByInterestRate(request));
    }
}
