package com.example.l3_backend.service;

import com.example.l3_backend.dto.certificate.CertificateDto;
import com.example.l3_backend.entity.Certificate;
import com.example.l3_backend.dto.certificate.CreateCertificateDto;
import com.example.l3_backend.dto.certificate.UpdateCertificateDto;
import org.springframework.stereotype.Service;

import java.util.List;
public interface CertificateService {
    void createCertificate(CreateCertificateDto createCertificateDto);
    List<CertificateDto> getCertificate();
    void deleteCertificate(Long id);
    void updateCertificate(Long id, UpdateCertificateDto updateCertificateDto);
}
