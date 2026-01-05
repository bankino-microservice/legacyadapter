package com.project.legacyadapterservice.service;

import com.ebank.ebanking2.soap.dto.*;
import com.project.legacyadapterservice.dto.rest.*;

import com.project.legacyadapterservice.mapper.VirementMapper;
import com.project.legacyadapterservice.soap.client.CoreBankingSoapClient;
import org.springframework.stereotype.Service;

@Service
public class VirementAdapterService {

    private final CoreBankingSoapClient soapClient;
    private final VirementMapper mapper;

    public VirementAdapterService(CoreBankingSoapClient soapClient, VirementMapper mapper) {
        this.soapClient = soapClient;
        this.mapper = mapper;
    }

    public ExecuteVirementResponseDTO executeVirement(ExecuteVirementRequestDTO restRequest) {
        System.out.println(restRequest.getMontant());
        System.out.println(restRequest.getRibEmetteur());
        // Map REST request to SOAP request
        com.ebank.ebanking2.soap.dto.Type soapType = mapper.toSoapType(restRequest.getType());
        ExecuteVirementRequest soapRequest = mapper.toSoapDto(restRequest);
        System.out.println(soapRequest.getMontant());
        // Call SOAP Client
        ExecuteVirementResponse response = soapClient.executeVirement(restRequest.getRibEmetteur(),restRequest.getRibRecepteur(),restRequest.getMontant(),soapType);
        System.out.println(response.getVirementInfo().getCompteEmetteurRib());
        // Return mapped REST response
        return mapper.toRestDto(response);
    }

    public VirementSoapInfoDTO getVirementById(Long id) {

        GetVirementByIdResponse response = soapClient.getVirementById(id);
        System.out.println(response.getVirementInfo().getType());
        System.out.println(response.getVirementInfo().getDate());
        System.out.println(response.getVirementInfo().getAmmouttakefromyou());
        return mapper.toRestDto(response.getVirementInfo());
    }

    public GetAllVirementsClientResponseDTO getAllVirementsClient(String clientRib) {
        GetAllVirementsClientResponse response = soapClient.getAllVirementsClient(clientRib);
        return mapper.toRestDto(response);
    }

    public GetVirementsEmisResponseDTO getVirementsEmis(String clientRib) {
        GetVirementsEmisResponse response = soapClient.getVirementsEmis(clientRib);
        return mapper.toRestDto(response);
    }

    public GetVirementsRecusResponseDTO getVirementsRecus(String clientRib) {
        GetVirementsRecusResponse response = soapClient.getVirementsRecus(clientRib);
        return mapper.toRestDto(response);
    }
}
