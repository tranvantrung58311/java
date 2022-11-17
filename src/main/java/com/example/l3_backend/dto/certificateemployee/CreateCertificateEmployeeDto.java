package com.example.l3_backend.dto.certificateemployee;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CreateCertificateEmployeeDto {
    @NotNull
    private Long province_id;

    @NotEmpty
    @Valid
    List<CertificateEmployeeDto> certificateEmployeeDtos;
}
