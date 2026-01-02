package com.project.legacyadapterservice.soap.client;

import com.ebank.ebanking2.soap.dto.*;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

@Component
public class CoreBankingSoapClient {
    private final WebServiceTemplate webServiceTemplate;

    public CoreBankingSoapClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    // ===== VIREMENT OPERATIONS =====
    public ExecuteVirementResponse executeVirement(String ribEmetteur,
            String ribRecepteur,
            double montant,
            Type type) {
        ExecuteVirementRequest request = new ExecuteVirementRequest();
        request.setRibEmetteur(ribEmetteur);
        request.setRibRecepteur(ribRecepteur);
        request.setMontant(montant);
        request.setType(type);

        return (ExecuteVirementResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public GetVirementByIdResponse getVirementById(long id) {
        GetVirementByIdRequest request = new GetVirementByIdRequest();
        request.setId(id);

        return (GetVirementByIdResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    // ===== C-EPARGNE OPERATIONS =====
    public CEpargneResponseDTO depositCEpargne(String Epargnerib, String Courantrib, double montant,
            int year, int month, int day) {
        DepotCEpargneRequest request = new DepotCEpargneRequest();
        request.setEpargnerib(Epargnerib);
        request.setCourantrib(Courantrib);
        request.setMontant(montant);
        request.setYear(year);
        request.setMonth(month);
        request.setDay(day);

        return (CEpargneResponseDTO) webServiceTemplate.marshalSendAndReceive(request);
    }

    public CEpargneResponseDTO withdrawCEpargne(String Epargnerib, String Courantrib, double montant,
            int year, int month, int day) {
        RetraitCEpargneRequest request = new RetraitCEpargneRequest();
        request.setEpargnerib(Epargnerib);
        request.setCourantrib(Courantrib);
        request.setMontant(montant);
        request.setYear(year);
        request.setMonth(month);
        request.setDay(day);

        return (CEpargneResponseDTO) webServiceTemplate.marshalSendAndReceive(request);
    }

    // ===== ANNUAL / QUINZAINE OPERATIONS =====
    public ClotureAnnuelleResponse closeAnnual(String rib, int year) {
        CalculateAnnuealeRequest request = new CalculateAnnuealeRequest();
        request.setRib(rib);
        request.setYear(year);

        return (ClotureAnnuelleResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public ClotureAnnuelleResponse closeQuinzaine(String rib, int year, int quaiznine) {
        CloturequainzineRequest request = new CloturequainzineRequest();
        request.setRib(rib);
        request.setYear(year);
        request.setQuaiznine(quaiznine);

        return (ClotureAnnuelleResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    // ===== ACCOUNT OPERATIONS =====

    public GetComptesByStatusTypeResponse getComptesByStatusType(String type, String status) {
        GetComptesByStatusTypeRequest request = new GetComptesByStatusTypeRequest();
        request.setType(type);
        request.setStatus(status);

        return (GetComptesByStatusTypeResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public GetSoldeResponse getSolde(String rib) {
        GetSoldeRequest request = new GetSoldeRequest();
        request.setRib(rib);

        return (GetSoldeResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public GetCompteByIdResponse getCompteById(long id) {
        GetCompteByIdRequest request = new GetCompteByIdRequest();
        request.setId(id);

        return (GetCompteByIdResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public GetComptesByClientResponse getComptesByClient(long clientId, String type, String status) {
        GetComptesByClientRequest request = new GetComptesByClientRequest();
        request.setClientId(clientId);
        request.setType(type);
        request.setStatus(status);

        return (GetComptesByClientResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public ChangeDotationStatusResponse changeDotationStatus(long accountId, boolean autorisePaiementEnLigne) {
        ChangeDotationStatusRequest request = new ChangeDotationStatusRequest();
        request.setAccountId(accountId);
        request.setAutorisePaiementEnLigne(autorisePaiementEnLigne);

        return (ChangeDotationStatusResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public CreateCCourantResponse createCCourant(long clientId, Double solde, String status,
            Boolean autorisePaiementEnLigne) {
        CreateCCourantRequest request = new CreateCCourantRequest();
        request.setClientId(clientId);
        request.setSolde(solde);
        request.setStatus(status);
        request.setAutorisePaiementEnLigne(autorisePaiementEnLigne);

        return (CreateCCourantResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public CreateCEpargneResponse createCEpargne(long clientId, double tauxInterets, Double solde, String status) {
        CreateCEpargneRequest request = new CreateCEpargneRequest();
        request.setClientId(clientId);
        request.setTauxInterets(tauxInterets);
        request.setSolde(solde);
        request.setStatus(status);

        return (CreateCEpargneResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public GetClientIdByCompteIdResponse getClientIdByCompteId(long compteId) {
        GetClientIdByCompteIdRequest request = new GetClientIdByCompteIdRequest();
        request.setCompteId(compteId);

        return (GetClientIdByCompteIdResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public GetClientIdByRibResponse getClientIdByRib(String rib) {
        GetClientIdByRibRequest request = new GetClientIdByRibRequest();
        request.setRib(rib);

        return (GetClientIdByRibResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public UpdateCompteResponse updateCompte(long id, Double solde, String status, Boolean autorisePaiementEnLigne,
            Double tauxInterets) {
        UpdateCompteRequest request = new UpdateCompteRequest();
        request.setId(id);
        request.setSolde(solde);
        request.setStatus(status);
        request.setAutorisePaiementEnLigne(autorisePaiementEnLigne);
        request.setTauxInterets(tauxInterets);

        return (UpdateCompteResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public ChangeCompteStatusResponse changeCompteStatus(long compteId, String status) {
        ChangeCompteStatusRequest request = new ChangeCompteStatusRequest();
        request.setCompteId(compteId);
        request.setStatus(status);

        return (ChangeCompteStatusResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public DeleteCompteResponse deleteCompte(long compteId) {
        DeleteCompteRequest request = new DeleteCompteRequest();
        request.setCompteId(compteId);

        return (DeleteCompteResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public ValidateRibResponse validateRib(String rib) {
        ValidateRibRequest request = new ValidateRibRequest();
        request.setRib(rib);

        return (ValidateRibResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public CheckAccountExistsResponse checkAccountExists(String rib) {
        CheckAccountExistsRequest request = new CheckAccountExistsRequest();
        request.setRib(rib);

        return (CheckAccountExistsResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public GetAccountsByRibsResponse getAccountsByRibs(java.util.List<String> ribs) {
        GetAccountsByRibsRequest request = new GetAccountsByRibsRequest();
        request.getRibs().addAll(ribs);

        return (GetAccountsByRibsResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public BlockAccountResponse blockAccount(long compteId, String reason) {
        BlockAccountRequest request = new BlockAccountRequest();
        request.setCompteId(compteId);
        request.setReason(reason);

        return (BlockAccountResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public UnblockAccountResponse unblockAccount(long compteId, String reason) {
        UnblockAccountRequest request = new UnblockAccountRequest();
        request.setCompteId(compteId);
        request.setReason(reason);

        return (UnblockAccountResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public GetAccountStatementResponse getAccountStatement(long compteId, String startDate, String endDate) {
        GetAccountStatementRequest request = new GetAccountStatementRequest();
        request.setCompteId(compteId);
        request.setStartDate(startDate);
        request.setEndDate(endDate);

        return (GetAccountStatementResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public VerifyAccountOwnershipResponse verifyAccountOwnership(long compteId, long clientId) {
        VerifyAccountOwnershipRequest request = new VerifyAccountOwnershipRequest();
        request.setCompteId(compteId);
        request.setClientId(clientId);

        return (VerifyAccountOwnershipResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public GetAccountSummaryResponse getAccountSummary(long clientId) {
        GetAccountSummaryRequest request = new GetAccountSummaryRequest();
        request.setClientId(clientId);

        return (GetAccountSummaryResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    // ===== CEPARGNE SPECIFIC OPERATIONS =====

    public GetCEpargneQuarterlyInterestsResponse getCEpargneQuarterlyInterests(long compteId) {
        GetCEpargneQuarterlyInterestsRequest request = new GetCEpargneQuarterlyInterestsRequest();
        request.setCompteId(compteId);

        return (GetCEpargneQuarterlyInterestsResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public UpdateCEpargneInterestRateResponse updateCEpargneInterestRate(long compteId, double newTauxInterets) {
        UpdateCEpargneInterestRateRequest request = new UpdateCEpargneInterestRateRequest();
        request.setCompteId(compteId);
        request.setNewTauxInterets(newTauxInterets);

        return (UpdateCEpargneInterestRateResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public ApplyCEpargneInterestsResponse applyCEpargneInterests(long compteId) {
        ApplyCEpargneInterestsRequest request = new ApplyCEpargneInterestsRequest();
        request.setCompteId(compteId);

        return (ApplyCEpargneInterestsResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public CalculateCEpargneInterestsResponse calculateCEpargneInterests(long compteId, int period) {
        CalculateCEpargneInterestsRequest request = new CalculateCEpargneInterestsRequest();
        request.setCompteId(compteId);
        request.setPeriod(period);

        return (CalculateCEpargneInterestsResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public GetCEpargneByIdResponse getCEpargneById(long compteId) {
        GetCEpargneByIdRequest request = new GetCEpargneByIdRequest();
        request.setCompteId(compteId);

        return (GetCEpargneByIdResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public GetAllCEpargneByClientResponse getAllCEpargneByClient(long clientId, String status) {
        GetAllCEpargneByClientRequest request = new GetAllCEpargneByClientRequest();
        request.setClientId(clientId);
        request.setStatus(status);

        return (GetAllCEpargneByClientResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

    public GetAllCEpargneByInterestRateResponse getAllCEpargneByInterestRate(double minTaux, double maxTaux) {
        GetAllCEpargneByInterestRateRequest request = new GetAllCEpargneByInterestRateRequest();
        request.setMinTaux(minTaux);
        request.setMaxTaux(maxTaux);

        return (GetAllCEpargneByInterestRateResponse) webServiceTemplate.marshalSendAndReceive(request);
    }

}
