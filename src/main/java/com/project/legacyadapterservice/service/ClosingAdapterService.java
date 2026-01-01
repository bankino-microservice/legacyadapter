package com.project.legacyadapterservice.service;

import com.ebank.ebanking2.soap.dto.*;
import com.project.legacyadapterservice.dto.rest.*;

import com.project.legacyadapterservice.mapper.ClosingMapper;
import com.project.legacyadapterservice.soap.client.CoreBankingSoapClient;
import org.springframework.stereotype.Service;

@Service
public class ClosingAdapterService {

    private final CoreBankingSoapClient soapClient;
    private final ClosingMapper mapper;

    public ClosingAdapterService(CoreBankingSoapClient soapClient, ClosingMapper mapper) {
        this.soapClient = soapClient;
        this.mapper = mapper;
    }

    public ClotureAnnuelleResponseDTO closeQuinzaine(ClotureQuinzaineRequestDTO restRequest) {
        CloturequainzineRequest soapRequest = mapper.toSoapDto(restRequest);
        // Assuming the SOAP service returns a status/message response
        return mapper.toRestDto(soapClient.closeQuinzaine(soapRequest.getRib(),soapRequest.getYear(),soapRequest.getQuaiznine()));
    }

    public ClotureAnnuelleResponseDTO calculateAnnual(CalculateAnnualeRequestDTO restRequest) {
        CalculateAnnuealeRequest soapRequest = mapper.toSoapDto(restRequest);
        return mapper.toRestDto(soapClient.closeAnnual(soapRequest.getRib(),soapRequest.getYear()));
    }
}