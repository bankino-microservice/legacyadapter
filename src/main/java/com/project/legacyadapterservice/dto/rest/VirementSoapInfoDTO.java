package com.project.legacyadapterservice.dto.rest;

import lombok.Data;

@Data
public class VirementSoapInfoDTO {
    private Long id;
    private Double montant;
    private String type;
    private String compteEmetteurRib;
    private String compteRecepteurRib;
    private Double ammouttakefromyou;
    private String date;

    // Getters/Setters
}