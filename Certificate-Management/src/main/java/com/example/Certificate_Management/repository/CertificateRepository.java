package com.example.Certificate_Management.repository;

import com.example.Certificate_Management.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
}
