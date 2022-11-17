package com.example.l3_backend.service.impl;

import com.example.l3_backend.dto.certificate.CertificateDto;
import com.example.l3_backend.entity.Certificate;
import com.example.l3_backend.mapper.ListMapper;
import com.example.l3_backend.repository.CertificateRepository;
import com.example.l3_backend.dto.certificate.CreateCertificateDto;
import com.example.l3_backend.dto.certificate.UpdateCertificateDto;
import com.example.l3_backend.service.CertificateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private CertificateRepository certificateRepository;
    @Autowired
    private ListMapper listMapper;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void createCertificate(CreateCertificateDto createCertificateDto) {
        Certificate certificate = mapper.map(createCertificateDto, Certificate.class);
        certificateRepository.save(certificate);
    }

    @Override
    public List<CertificateDto> getCertificate() {
        List<Certificate> certificateList = certificateRepository.findAll();
        return listMapper.mapList(certificateList, CertificateDto.class);
    }

    @Override
    public void deleteCertificate(Long id) {
        certificateRepository.deleteById(id);
    }

    @Override
    public void updateCertificate(Long id, UpdateCertificateDto updateCertificateDto) {
        Certificate certificate = certificateRepository.findById(id).orElseThrow(null);
        mapper.map(updateCertificateDto, certificate);
        certificateRepository.save(certificate);
    }
}
