package com.example.l3_backend.dto.employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateInputEmployee {
    private String name;

    private String email;

    private String code;

    private String phone;

    private Integer age;

    private Long province_id;

    private Long district_id;

    private Long commune_id;
}
