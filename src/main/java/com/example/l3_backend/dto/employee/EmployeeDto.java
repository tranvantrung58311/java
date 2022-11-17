package com.example.l3_backend.dto.employee;

import com.example.l3_backend.dto.commune.CommuneDto;
import com.example.l3_backend.dto.district.DistrictDto;
import com.example.l3_backend.dto.province.ProvinceDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
    private Long id;

    private String name;

    private String email;

    private String code;

    private String phone;

    private Integer age;

    private ProvinceDto province;

    private DistrictDto district;

    private CommuneDto commune;

}
