package com.example.l3_backend.dto.certificate;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class UpdateCertificateDto {
    private String name;
    private String code;
    private LocalDate startDay;
    private LocalDate endDay;
}
