package com.example.l3_backend.dto.certificateemployee;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class CertificateEmployeeDto {
    @NotBlank(message = "name không được để trống")
    private String name;
    @NotBlank(message = "code không được để trống")
    private String code;
    @NotNull(message = "endDay không được để trống")
    private LocalDate endDay;
    @NotNull(message = "employee_id không được để trống")
    private Long employee_id;
}
