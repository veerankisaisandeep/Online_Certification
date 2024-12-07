package com.example.Certificate_Management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String issuingOrganisation;
    private String validFrom;
    private String validTo;
    private String fileUrl; // Path to the uploaded certificate file (could be a link or path)

    // Default constructor
    public Certificate() {}

    // Parameterized constructor
    public Certificate(String name, String issuingOrganisation, String validFrom, String validTo, String fileUrl) {
        this.name = name;
        this.issuingOrganisation = issuingOrganisation;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.fileUrl = fileUrl;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIssuingOrganisation() {
        return issuingOrganisation;
    }

    public void setIssuingOrganisation(String issuingOrganisation) {
        this.issuingOrganisation = issuingOrganisation;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
