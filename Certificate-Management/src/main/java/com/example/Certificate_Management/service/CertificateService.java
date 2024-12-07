package com.example.Certificate_Management.service;

import com.example.Certificate_Management.entity.Certificate;
import java.util.List;

public interface CertificateService {
    Certificate saveCertificate(Certificate certificate); // Save a new certificate
    Certificate getCertificateById(Long id); // Get a certificate by ID
    List<Certificate> getAllCertificates(); // Get all certificates
    Certificate updateCertificate(Long id, Certificate certificateDetails); // Update a certificate by ID
    void deleteCertificate(Long id); // Delete a certificate by ID
}
