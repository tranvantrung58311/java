package com.example.l3_backend.service;

import com.example.l3_backend.dto.certificateemployee.CreateCertificateEmployeeDto;
import com.example.l3_backend.dto.certificateemployee.IdCertificateEmployeeDto;
import com.example.l3_backend.entity.CertificateEmployee;
import com.example.l3_backend.response.CertificateEmployeeListResponse;

import java.util.List;

public interface CertificateEmployeeService {

    CertificateEmployeeListResponse createCertificateEmployee(CreateCertificateEmployeeDto employeeDto);

    IdCertificateEmployeeDto getCertificateEmployeeId(Long id);

    List<IdCertificateEmployeeDto> deleteCertificateEmployeeId(Long id);
}
