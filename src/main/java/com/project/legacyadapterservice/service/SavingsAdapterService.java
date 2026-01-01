package com.project.legacyadapterservice.service;

import com.ebank.ebanking2.soap.dto.*;
import com.project.legacyadapterservice.dto.rest.*;
import com.project.legacyadapterservice.dto.rest.CEpargneResponseDTO;
import com.project.legacyadapterservice.mapper.SavingsMapper;
import com.project.legacyadapterservice.soap.client.CoreBankingSoapClient;
import org.springframework.stereotype.Service;

@Service
public class SavingsAdapterService {

    private final CoreBankingSoapClient soapClient;
    private final SavingsMapper mapper;

    public SavingsAdapterService(CoreBankingSoapClient soapClient, SavingsMapper mapper) {
        this.soapClient = soapClient;
        this.mapper = mapper;
    }

    public com.project.legacyadapterservice.dto.rest.CEpargneResponseDTO deposit(DepotRetraitCEpargneRequestDTO restRequest) {

        DepotCEpargneRequest soapRequest = mapper.toSoapDepot(restRequest);

        CEpargneResponseDTO result=   mapper.toRestDto(soapClient.depositCEpargne(soapRequest.getEpargnerib(),soapRequest.getCourantrib(),soapRequest.getMontant(),soapRequest.getYear(),soapRequest.getMonth(),soapRequest.getDay()));
                result.setCourantrib(restRequest.getCourantrib());
                result.setEpargnerib(restRequest.getEpargnerib());
        return result;
    }

    public com.project.legacyadapterservice.dto.rest.CEpargneResponseDTO withdraw(DepotRetraitCEpargneRequestDTO restRequest) {
        RetraitCEpargneRequest soapRequest = mapper.toSoapRetrait(restRequest);
        CEpargneResponseDTO result=   mapper.toRestDto(soapClient.withdrawCEpargne(soapRequest.getEpargnerib(),soapRequest.getCourantrib(),soapRequest.getMontant(),soapRequest.getYear(),soapRequest.getMonth(),soapRequest.getDay()));
        result.setCourantrib(restRequest.getCourantrib());
        result.setEpargnerib(restRequest.getEpargnerib());
        return result;
    }


}