package com.example.l3_backend.controller;

import com.example.l3_backend.common.DataResponse;
import com.example.l3_backend.constant.AppConstant;
import com.example.l3_backend.dto.certificateemployee.CreateCertificateEmployeeDto;
import com.example.l3_backend.dto.certificateemployee.IdCertificateEmployeeDto;
import com.example.l3_backend.entity.CertificateEmployee;
import com.example.l3_backend.response.CertificateEmployeeListResponse;
import com.example.l3_backend.service.CertificateEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Api/CertificateEmployee")
public class CertificateEmployeeController {
    @Autowired
    private CertificateEmployeeService certificateEmployeeService;

    @PostMapping
    public ResponseEntity<Object> createCertificateEmployee(@Valid @RequestBody CreateCertificateEmployeeDto employeeDto) {
        CertificateEmployeeListResponse response = certificateEmployeeService.createCertificateEmployee(employeeDto);
        return DataResponse.setDataCustom(response, AppConstant.SUCCESS_CREATE, "200", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getCertificateEmployeeId(@RequestParam Long id) {
        IdCertificateEmployeeDto certificateEmployeeDto = certificateEmployeeService.getCertificateEmployeeId(id);
        return DataResponse.setDataCustom(certificateEmployeeDto, AppConstant.SUCCESS_SEARCH, "200", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCertificateEmployeeId(@PathVariable Long id) {
        List<IdCertificateEmployeeDto> certificateEmployees = certificateEmployeeService.deleteCertificateEmployeeId(id);
        return DataResponse.setDataCustom(certificateEmployees, AppConstant.SUCCESS_DELETE, "200", HttpStatus.OK);
    }
}
