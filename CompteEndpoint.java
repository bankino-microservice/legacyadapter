package com.project.core.endpoint;

import com.ebank.ebanking2.soap.dto.*;
import com.project.core.model.dto.*;
import com.project.core.model.entitie.*;
import com.project.core.service.CompteService;
import org.springframework.ws.server.endpoint.annotation.*;

import java.util.List;

@Endpoint
public class CompteEndpoint {

    private static final String NAMESPACE_URI = "http://ebank.com/ebanking2/soap";
    private final CompteService compteService;

    public CompteEndpoint(CompteService compteService) {
        this.compteService = compteService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetComptesByStatusTypeRequest")
    @ResponsePayload
    public GetComptesByStatusTypeResponse getComptesByStatusType(
            @RequestPayload GetComptesByStatusTypeRequest request) {
        GetComptesByStatusTypeResponse response = new GetComptesByStatusTypeResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            List<?> result = compteService.get(request.getType(), request.getStatus());

            st.setStatusCode("SUCCESS");
            st.setMessage("OK");
            response.setServiceStatus(st);

            for (Object o : result) {
                response.getComptes().add(toSoap(o));
            }

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetSoldeRequest")
    @ResponsePayload
    public GetSoldeResponse getSolde(@RequestPayload GetSoldeRequest request) {
        GetSoldeResponse response = new GetSoldeResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            double solde = compteService.getSolde(request.getRib());

            st.setStatusCode("SUCCESS");
            st.setMessage("OK");
            response.setServiceStatus(st);
            response.setSolde(solde);

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCompteByIdRequest")
    @ResponsePayload
    public GetCompteByIdResponse getCompteById(@RequestPayload GetCompteByIdRequest request) {
        GetCompteByIdResponse response = new GetCompteByIdResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            Compte compte = compteService.getById(request.getId());

            st.setStatusCode("SUCCESS");
            st.setMessage("OK");
            response.setServiceStatus(st);

            response.setCompte(toSoap(compte));

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetComptesByClientRequest")
    @ResponsePayload
    public GetComptesByClientResponse getComptesByClient(@RequestPayload GetComptesByClientRequest request) {
        GetComptesByClientResponse response = new GetComptesByClientResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            List<?> result = compteService.getByClientId(request.getClientId(), request.getType(), request.getStatus());

            st.setStatusCode("SUCCESS");
            st.setMessage("OK");
            response.setServiceStatus(st);

            for (Object o : result) {
                response.getComptes().add(toSoap(o));
            }

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ChangeDotationStatusRequest")
    @ResponsePayload
    public ChangeDotationStatusResponse changeDotation(@RequestPayload ChangeDotationStatusRequest request) {
        ChangeDotationStatusResponse response = new ChangeDotationStatusResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            boolean ok = compteService.changeDotationStatus(request.getAccountId(),
                    request.isAutorisePaiementEnLigne());

            st.setStatusCode("SUCCESS");
            st.setMessage("OK");
            response.setServiceStatus(st);
            response.setSuccess(ok);

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
            response.setSuccess(false);
        }
        return response;
    }

    // Accept either Entity or DTO coming from CompteService.filterListCompte()
    private CompteSoapInfo toSoap(Object obj) {
        if (obj instanceof Compte c)
            return toSoapEntity(c);
        if (obj instanceof CompteResDTO dto)
            return toSoapCompteResDTO(dto);
        if (obj instanceof CCourantResDTO dto)
            return toSoapCCourantResDTO(dto);
        if (obj instanceof CEpargneResDTO dto)
            return toSoapCEpargneResDTO(dto);

        throw new IllegalArgumentException("Type non supporté pour mapping SOAP: " + obj.getClass().getName());
    }

    private CompteSoapInfo toSoapEntity(Compte c) {
        CompteSoapInfo info = new CompteSoapInfo();
        info.setId(c.getId());
        info.setClientId(c.getClientId());
        info.setRib(c.getRib());
        info.setSolde(c.getSolde());
        info.setStatus(c.getStatus() != null ? c.getStatus().name() : null);
        info.setType(c.getAccountType());

        if (c instanceof CCourant cc) {
            info.setAutorisePaiementEnLigne(cc.isAutorisePaiementEnLigne());
        }
        if (c instanceof CEpargne ce) {
            info.setTauxInterets(ce.getTauxInterets());
            info.setBalanceQ1(ce.getBalanceQ1());
            info.setBalanceQ2(ce.getBalanceQ2());
            info.setInterests(ce.getInterests());
        }
        return info;
    }

    private CompteSoapInfo toSoapCompteResDTO(CompteResDTO dto) {
        CompteSoapInfo info = new CompteSoapInfo();
        info.setId(dto.getId());
        info.setClientId(dto.getClientId());
        info.setRib(dto.getRib());
        info.setSolde(dto.getSolde());
        info.setStatus(dto.getStatus() != null ? dto.getStatus().name() : null);
        info.setType(dto.getAccountType());
        return info;
    }

    private CompteSoapInfo toSoapCCourantResDTO(CCourantResDTO dto) {
        CompteSoapInfo info = new CompteSoapInfo();
        info.setId(dto.getId());
        info.setClientId(dto.getClientId());
        info.setRib(dto.getRib());
        info.setSolde(dto.getSolde());
        info.setStatus(dto.getStatus() != null ? dto.getStatus().name() : null);
        info.setType("CCourant");
        info.setAutorisePaiementEnLigne(dto.isAutorisePaiementEnLigne());
        return info;
    }

    private CompteSoapInfo toSoapCEpargneResDTO(CEpargneResDTO dto) {
        CompteSoapInfo info = new CompteSoapInfo();
        info.setId(dto.getId());
        info.setClientId(dto.getClientId());
        info.setRib(dto.getRib());
        info.setSolde(dto.getSolde());
        info.setStatus(dto.getStatus() != null ? dto.getStatus().name() : null);
        info.setType("CEpargne");
        info.setTauxInterets(dto.getTauxInterets());
        info.setBalanceQ1(dto.getBalanceQ1());
        info.setBalanceQ2(dto.getBalanceQ2());
        info.setInterests(dto.getInterests());
        return info;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateCCourantRequest")
    @ResponsePayload
    public CreateCCourantResponse createCCourant(@RequestPayload CreateCCourantRequest request) {
        CreateCCourantResponse response = new CreateCCourantResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            // build DTO expected by your service (you used CCourantResDTO in saveCCourant)
            CCourantResDTO dto = new CCourantResDTO();
            dto.setClientId(request.getClientId());

            if (request.getSolde() != null)
                dto.setSolde(request.getSolde());
            if (request.getStatus() != null)
                dto.setStatus(com.project.core.model.entitie.StatusCompte.valueOf(request.getStatus()));

            if (request.isAutorisePaiementEnLigne() != null)
                dto.setAutorisePaiementEnLigne(request.isAutorisePaiementEnLigne());

            CCourantResDTO saved = compteService.saveCCourant(dto);

            st.setStatusCode("SUCCESS");
            st.setMessage("CCourant créé");
            response.setServiceStatus(st);
            response.setCompte(toSoap(saved));

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateCEpargneRequest")
    @ResponsePayload
    public CreateCEpargneResponse createCEpargne(@RequestPayload CreateCEpargneRequest request) {
        CreateCEpargneResponse response = new CreateCEpargneResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            CEpargneDTO dto = new CEpargneDTO();
            dto.setClientId(request.getClientId());
            dto.setTauxInterets(request.getTauxInterets());

            if (request.getSolde() != null)
                dto.setSolde(request.getSolde());
            if (request.getStatus() != null)
                dto.setStatus(com.project.core.model.entitie.StatusCompte.valueOf(request.getStatus()));
            // dateInterets as String -> you can parse it if CEpargneDTO expects
            // LocalDateTime
            // dto.setDateInterets(LocalDateTime.parse(request.getDateInterets()));

            CEpargneResDTO saved = compteService.saveCEpargne(dto);

            st.setStatusCode("SUCCESS");
            st.setMessage("CEpargne créé");
            response.setServiceStatus(st);
            response.setCompte(toSoap(saved));

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetClientIdByCompteIdRequest")
    @ResponsePayload
    public GetClientIdByCompteIdResponse getClientIdByCompteId(@RequestPayload GetClientIdByCompteIdRequest request) {
        GetClientIdByCompteIdResponse response = new GetClientIdByCompteIdResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            Long clientId = compteService.getClientByCompteId(request.getCompteId());

            st.setStatusCode("SUCCESS");
            st.setMessage("OK");
            response.setServiceStatus(st);
            response.setClientId(clientId);

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetClientIdByRibRequest")
    @ResponsePayload
    public GetClientIdByRibResponse getClientIdByRib(@RequestPayload GetClientIdByRibRequest request) {
        GetClientIdByRibResponse response = new GetClientIdByRibResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            Long clientId = compteService.getClientByCompteRib(request.getRib());

            st.setStatusCode("SUCCESS");
            st.setMessage("OK");
            response.setServiceStatus(st);
            response.setClientId(clientId);

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdateCompteRequest")
    @ResponsePayload
    public UpdateCompteResponse updateCompte(@RequestPayload UpdateCompteRequest request) {
        UpdateCompteResponse response = new UpdateCompteResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            UpdateCompteDTO dto = new UpdateCompteDTO();
            dto.setId(request.getId());

            if (request.getSolde() != null)
                dto.setSolde(request.getSolde());
            if (request.getStatus() != null)
                dto.setStatus(com.project.core.model.entitie.StatusCompte.valueOf(request.getStatus()));
            if (request.isAutorisePaiementEnLigne() != null)
                dto.setAutorisePaiementEnLigne(request.isAutorisePaiementEnLigne());
            if (request.getTauxInterets() != null)
                dto.setTauxInterets(request.getTauxInterets());

            CompteResDTO updated = compteService.updateCompte(dto);

            st.setStatusCode("SUCCESS");
            st.setMessage("Compte mis à jour avec succès");
            response.setServiceStatus(st);
            response.setCompte(toSoap(updated));

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ChangeCompteStatusRequest")
    @ResponsePayload
    public ChangeCompteStatusResponse changeCompteStatus(@RequestPayload ChangeCompteStatusRequest request) {
        ChangeCompteStatusResponse response = new ChangeCompteStatusResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            com.project.core.model.entitie.StatusCompte status = com.project.core.model.entitie.StatusCompte
                    .valueOf(request.getStatus());
            CompteResDTO updated = compteService.changeCompteStatus(request.getCompteId(), status);

            st.setStatusCode("SUCCESS");
            st.setMessage("Statut du compte modifié avec succès");
            response.setServiceStatus(st);
            response.setCompte(toSoap(updated));

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteCompteRequest")
    @ResponsePayload
    public DeleteCompteResponse deleteCompte(@RequestPayload DeleteCompteRequest request) {
        DeleteCompteResponse response = new DeleteCompteResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            boolean success = compteService.softDeleteCompte(request.getCompteId());

            st.setStatusCode("SUCCESS");
            st.setMessage("Compte supprimé (fermé) avec succès");
            response.setServiceStatus(st);
            response.setSuccess(success);

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
            response.setSuccess(false);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCEpargneQuarterlyInterestsRequest")
    @ResponsePayload
    public GetCEpargneQuarterlyInterestsResponse getCEpargneQuarterlyInterests(
            @RequestPayload GetCEpargneQuarterlyInterestsRequest request) {
        GetCEpargneQuarterlyInterestsResponse response = new GetCEpargneQuarterlyInterestsResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            CEpargneResDTO cepargne = compteService.getCEpargneQuarterlyData(request.getCompteId());

            st.setStatusCode("SUCCESS");
            st.setMessage("Données trimestrielles récupérées avec succès");
            response.setServiceStatus(st);
            response.setBalanceQ1(cepargne.getBalanceQ1());
            response.setBalanceQ2(cepargne.getBalanceQ2());
            response.setInterests(cepargne.getInterests());
            response.setTauxInterets(cepargne.getTauxInterets());

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ValidateRibRequest")
    @ResponsePayload
    public ValidateRibResponse validateRib(@RequestPayload ValidateRibRequest request) {
        ValidateRibResponse response = new ValidateRibResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            boolean isValid = compteService.validateRib(request.getRib());

            st.setStatusCode("SUCCESS");
            st.setMessage("Validation effectuée");
            response.setServiceStatus(st);
            response.setIsValid(isValid);
            response.setMessage(isValid ? "RIB valide" : "Format RIB invalide");

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
            response.setIsValid(false);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CheckAccountExistsRequest")
    @ResponsePayload
    public CheckAccountExistsResponse checkAccountExists(@RequestPayload CheckAccountExistsRequest request) {
        CheckAccountExistsResponse response = new CheckAccountExistsResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            Compte compte = compteService.checkAccountExists(request.getRib());

            st.setStatusCode("SUCCESS");
            st.setMessage("Vérification effectuée");
            response.setServiceStatus(st);
            response.setExists(compte != null);

            if (compte != null) {
                response.setCompteId(compte.getId());
                response.setAccountType(compte.getAccountType());
            }

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
            response.setExists(false);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAccountsByRibsRequest")
    @ResponsePayload
    public GetAccountsByRibsResponse getAccountsByRibs(@RequestPayload GetAccountsByRibsRequest request) {
        GetAccountsByRibsResponse response = new GetAccountsByRibsResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            List<Compte> comptes = compteService.getAccountsByRibs(request.getRibs());

            st.setStatusCode("SUCCESS");
            st.setMessage("Comptes récupérés avec succès");
            response.setServiceStatus(st);

            for (Compte compte : comptes) {
                response.getComptes().add(toSoap(compte));
            }

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "BlockAccountRequest")
    @ResponsePayload
    public BlockAccountResponse blockAccount(@RequestPayload BlockAccountRequest request) {
        BlockAccountResponse response = new BlockAccountResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            CompteResDTO compte = compteService.blockAccount(request.getCompteId(), request.getReason());

            st.setStatusCode("SUCCESS");
            st.setMessage(
                    "Compte bloqué avec succès" + (request.getReason() != null ? ": " + request.getReason() : ""));
            response.setServiceStatus(st);
            response.setCompte(toSoap(compte));

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UnblockAccountRequest")
    @ResponsePayload
    public UnblockAccountResponse unblockAccount(@RequestPayload UnblockAccountRequest request) {
        UnblockAccountResponse response = new UnblockAccountResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            CompteResDTO compte = compteService.unblockAccount(request.getCompteId(), request.getReason());

            st.setStatusCode("SUCCESS");
            st.setMessage(
                    "Compte débloqué avec succès" + (request.getReason() != null ? ": " + request.getReason() : ""));
            response.setServiceStatus(st);
            response.setCompte(toSoap(compte));

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAccountStatementRequest")
    @ResponsePayload
    public GetAccountStatementResponse getAccountStatement(@RequestPayload GetAccountStatementRequest request) {
        GetAccountStatementResponse response = new GetAccountStatementResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            Compte compte = compteService.getById(request.getCompteId());

            AccountStatementInfo statement = new AccountStatementInfo();
            statement.setCompte(toSoap(compte));
            statement.setStartDate(
                    request.getStartDate() != null ? request.getStartDate() : compte.getCreatedAt().toString());
            statement.setEndDate(
                    request.getEndDate() != null ? request.getEndDate() : java.time.LocalDateTime.now().toString());
            statement.setOpeningBalance(compte.getSolde());
            statement.setClosingBalance(compte.getSolde());
            statement.setTransactionCount(0); // To be implemented with transaction history

            st.setStatusCode("SUCCESS");
            st.setMessage("Relevé de compte généré");
            response.setServiceStatus(st);
            response.setStatement(statement);

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "VerifyAccountOwnershipRequest")
    @ResponsePayload
    public VerifyAccountOwnershipResponse verifyAccountOwnership(
            @RequestPayload VerifyAccountOwnershipRequest request) {
        VerifyAccountOwnershipResponse response = new VerifyAccountOwnershipResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            boolean isOwner = compteService.verifyAccountOwnership(request.getCompteId(), request.getClientId());

            st.setStatusCode("SUCCESS");
            st.setMessage(isOwner ? "Client propriétaire du compte" : "Client non propriétaire du compte");
            response.setServiceStatus(st);
            response.setIsOwner(isOwner);

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
            response.setIsOwner(false);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAccountSummaryRequest")
    @ResponsePayload
    public GetAccountSummaryResponse getAccountSummary(@RequestPayload GetAccountSummaryRequest request) {
        GetAccountSummaryResponse response = new GetAccountSummaryResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            CompteService.AccountSummary summary = compteService.getAccountSummary(request.getClientId());

            AccountSummaryInfo summaryInfo = new AccountSummaryInfo();
            summaryInfo.setTotalAccounts(summary.totalAccounts);
            summaryInfo.setTotalBalance(summary.totalBalance);
            summaryInfo.setActiveAccounts(summary.activeAccounts);
            summaryInfo.setBlockedAccounts(summary.blockedAccounts);
            summaryInfo.setCcourantCount(summary.ccourantCount);
            summaryInfo.setCepargneCount(summary.cepargneCount);

            st.setStatusCode("SUCCESS");
            st.setMessage("Résumé des comptes généré");
            response.setServiceStatus(st);
            response.setSummary(summaryInfo);

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
        }
        return response;
    }

    // ===================== CEpargne Specific Endpoints =====================

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdateCEpargneInterestRateRequest")
    @ResponsePayload
    public UpdateCEpargneInterestRateResponse updateCEpargneInterestRate(
            @RequestPayload UpdateCEpargneInterestRateRequest request) {
        UpdateCEpargneInterestRateResponse response = new UpdateCEpargneInterestRateResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            CEpargneResDTO cepargne = compteService.updateCEpargneInterestRate(
                    request.getCompteId(),
                    request.getNewTauxInterets());

            st.setStatusCode("SUCCESS");
            st.setMessage("Taux d'intérêt mis à jour avec succès");
            response.setServiceStatus(st);
            response.setCompte(toSoap(cepargne));

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ApplyCEpargneInterestsRequest")
    @ResponsePayload
    public ApplyCEpargneInterestsResponse applyCEpargneInterests(
            @RequestPayload ApplyCEpargneInterestsRequest request) {
        ApplyCEpargneInterestsResponse response = new ApplyCEpargneInterestsResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            CompteService.ApplyInterestsResult result = compteService.applyCEpargneInterests(request.getCompteId());

            st.setStatusCode("SUCCESS");
            st.setMessage("Intérêts appliqués avec succès");
            response.setServiceStatus(st);
            response.setCompte(toSoap(result.compte));
            response.setInterestsApplied(result.interestsApplied);
            response.setNewBalance(result.newBalance);

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllCEpargneByInterestRateRequest")
    @ResponsePayload
    public GetAllCEpargneByInterestRateResponse getAllCEpargneByInterestRate(
            @RequestPayload GetAllCEpargneByInterestRateRequest request) {
        GetAllCEpargneByInterestRateResponse response = new GetAllCEpargneByInterestRateResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            List<CEpargneResDTO> comptes = compteService.getAllCEpargneByInterestRateRange(
                    request.getMinTaux(),
                    request.getMaxTaux());

            st.setStatusCode("SUCCESS");
            st.setMessage("Comptes CEpargne récupérés avec succès");
            response.setServiceStatus(st);

            for (CEpargneResDTO compte : comptes) {
                response.getComptes().add(toSoap(compte));
            }

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CalculateCEpargneInterestsRequest")
    @ResponsePayload
    public CalculateCEpargneInterestsResponse calculateCEpargneInterests(
            @RequestPayload CalculateCEpargneInterestsRequest request) {
        CalculateCEpargneInterestsResponse response = new CalculateCEpargneInterestsResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            CompteService.InterestCalculationResult result = compteService.calculateCEpargneInterests(
                    request.getCompteId(),
                    request.getPeriod());

            st.setStatusCode("SUCCESS");
            st.setMessage("Intérêts calculés avec succès");
            response.setServiceStatus(st);
            response.setCalculatedInterests(result.calculatedInterests);
            response.setCurrentBalance(result.currentBalance);
            response.setTauxInterets(result.tauxInterets);
            response.setProjectedBalance(result.projectedBalance);

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCEpargneByIdRequest")
    @ResponsePayload
    public GetCEpargneByIdResponse getCEpargneById(@RequestPayload GetCEpargneByIdRequest request) {
        GetCEpargneByIdResponse response = new GetCEpargneByIdResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            CEpargneResDTO cepargne = compteService.getCEpargneById(request.getCompteId());

            st.setStatusCode("SUCCESS");
            st.setMessage("Compte CEpargne récupéré avec succès");
            response.setServiceStatus(st);
            response.setCompte(toSoap(cepargne));

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllCEpargneByClientRequest")
    @ResponsePayload
    public GetAllCEpargneByClientResponse getAllCEpargneByClient(
            @RequestPayload GetAllCEpargneByClientRequest request) {
        GetAllCEpargneByClientResponse response = new GetAllCEpargneByClientResponse();
        ServiceStatus st = new ServiceStatus();

        try {
            List<CEpargneResDTO> comptes = compteService.getAllCEpargneByClient(
                    request.getClientId(),
                    request.getStatus());

            st.setStatusCode("SUCCESS");
            st.setMessage("Comptes CEpargne du client récupérés avec succès");
            response.setServiceStatus(st);

            for (CEpargneResDTO compte : comptes) {
                response.getComptes().add(toSoap(compte));
            }

        } catch (Exception e) {
            st.setStatusCode("ERROR");
            st.setMessage(e.getMessage());
            response.setServiceStatus(st);
        }
        return response;
    }
}
