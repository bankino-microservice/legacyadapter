package com.project.legacyadapterservice.dto.rest;

import lombok.Data;
import java.time.LocalDate;

@Data
public class VirementRestDto {
    private long id;
    private double montant;
    private String type;
    private String compteEmetteur;
    private String compteRecepteur;
    private double ammouttakefromyou;
    private String date;
    private String statusCode;
    private String message;
}
