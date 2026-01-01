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

        return (ExecuteVirementResponse)
                webServiceTemplate.marshalSendAndReceive(request);
    }

    public GetVirementByIdResponse getVirementById(long id) {
        GetVirementByIdRequest request = new GetVirementByIdRequest();
        request.setId(id);

        return (GetVirementByIdResponse)
                webServiceTemplate.marshalSendAndReceive(request);
    }

    // ===== C-EPARGNE OPERATIONS =====
    public CEpargneResponseDTO depositCEpargne(String Epargnerib,String Courantrib, double montant,
                                               int year, int month, int day) {
        DepotCEpargneRequest request = new DepotCEpargneRequest();
        request.setEpargnerib(Epargnerib);
        request.setCourantrib(Courantrib);
        request.setMontant(montant);
        request.setYear(year);
        request.setMonth(month);
        request.setDay(day);

        return (CEpargneResponseDTO)
                webServiceTemplate.marshalSendAndReceive(request);
    }

    public CEpargneResponseDTO withdrawCEpargne(String Epargnerib,String Courantrib, double montant,
                                                int year, int month, int day) {
        RetraitCEpargneRequest request = new RetraitCEpargneRequest();
        request.setEpargnerib(Epargnerib);
        request.setCourantrib(Courantrib);
        request.setMontant(montant);
        request.setYear(year);
        request.setMonth(month);
        request.setDay(day);

        return (CEpargneResponseDTO)
                webServiceTemplate.marshalSendAndReceive(request);
    }



    // ===== ANNUAL / QUINZAINE OPERATIONS =====
    public ClotureAnnuelleResponse closeAnnual(String rib, int year) {
        CalculateAnnuealeRequest request = new CalculateAnnuealeRequest();
        request.setRib(rib);
        request.setYear(year);

        return (ClotureAnnuelleResponse)
                webServiceTemplate.marshalSendAndReceive(request);
    }

    public ClotureAnnuelleResponse closeQuinzaine(String rib, int year, int quaiznine) {
        CloturequainzineRequest request = new CloturequainzineRequest();
        request.setRib(rib);
        request.setYear(year);
        request.setQuaiznine(quaiznine);


        return  (ClotureAnnuelleResponse)
                webServiceTemplate.marshalSendAndReceive(request);
    }


}
