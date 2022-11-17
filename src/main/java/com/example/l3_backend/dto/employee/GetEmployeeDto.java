package com.example.l3_backend.dto.employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetEmployeeDto {
    private Long id;

    private String name;

    private String email;

    private String code;

    private String phone;

    private Integer age;
}
