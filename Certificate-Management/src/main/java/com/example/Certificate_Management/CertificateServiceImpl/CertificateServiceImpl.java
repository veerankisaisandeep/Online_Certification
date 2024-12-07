package com.example.Certificate_Management.CertificateServiceImpl;

import com.example.Certificate_Management.entity.Certificate;
import com.example.Certificate_Management.repository.CertificateRepository;
import com.example.Certificate_Management.service.CertificateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private CertificateRepository certificateRepository;

    @Override
    public Certificate saveCertificate(Certificate certificate) {
        return certificateRepository.save(certificate);
    }

    @Override
    public Certificate getCertificateById(Long id) {
        return certificateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found with id: " + id));
    }

    @Override
    public List<Certificate> getAllCertificates() {
        return certificateRepository.findAll();
    }

    @Override
    public Certificate updateCertificate(Long id, Certificate certificateDetails) {
        Certificate certificate = certificateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found with id: " + id));

        certificate.setName(certificateDetails.getName());
        certificate.setIssuingOrganisation(certificateDetails.getIssuingOrganisation());
        certificate.setValidFrom(certificateDetails.getValidFrom());
        certificate.setValidTo(certificateDetails.getValidTo());
        certificate.setFileUrl(certificateDetails.getFileUrl());

        return certificateRepository.save(certificate);
    }

    @Override
    public void deleteCertificate(Long id) {
        Certificate certificate = certificateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found with id: " + id));

        certificateRepository.delete(certificate);
    }
}
