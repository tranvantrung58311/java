package com.example.l3_backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class CertificateEmployee extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "end_day",nullable = false)
    private LocalDate endDay;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;
}
