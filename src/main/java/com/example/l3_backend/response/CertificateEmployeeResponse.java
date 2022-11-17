package com.example.l3_backend.response;

import com.example.l3_backend.dto.employee.GetEmployeeDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CertificateEmployeeResponse {
    private Long id;
    private String name;
    private String code;
    private LocalDate endDay;
    private GetEmployeeDto employee;
}
