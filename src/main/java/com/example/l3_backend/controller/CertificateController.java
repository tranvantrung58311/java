package com.example.l3_backend.controller;

import com.example.l3_backend.constant.AppConstant;
import com.example.l3_backend.dto.certificate.CreateCertificateDto;
import com.example.l3_backend.dto.certificate.UpdateCertificateDto;
import com.example.l3_backend.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Api/Certificate")
public class CertificateController {
    @Autowired
    private CertificateService certificateService;

    @PostMapping
    public ResponseEntity<Object> createCertificate(@RequestBody CreateCertificateDto createCertificateDto) {
        certificateService.createCertificate(createCertificateDto);
        return new ResponseEntity<>(AppConstant.SUCCESS_CREATE, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getCertificate() {
        return new ResponseEntity<>(certificateService.getCertificate(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCertificate(@PathVariable Long id) {
        certificateService.deleteCertificate(id);
        return new ResponseEntity<>(AppConstant.SUCCESS_DELETE, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCertificate(@PathVariable Long id, @RequestBody UpdateCertificateDto updateCertificateDto) {
        certificateService.updateCertificate(id, updateCertificateDto);
        return new ResponseEntity<>(AppConstant.SUCCESS_UPDATE, HttpStatus.OK);
    }
}
