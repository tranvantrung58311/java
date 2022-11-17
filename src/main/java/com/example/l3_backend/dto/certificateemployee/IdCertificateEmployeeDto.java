package com.example.l3_backend.dto.certificateemployee;

import com.example.l3_backend.dto.employee.GetEmployeeDto;
import com.example.l3_backend.dto.province.ProvinceDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class IdCertificateEmployeeDto {
    private Long id;

    private String name;

    private String code;

    private LocalDate endDay;

    private GetEmployeeDto employee;

    private ProvinceDto province;

}
