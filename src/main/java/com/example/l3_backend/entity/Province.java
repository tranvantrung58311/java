package com.example.l3_backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "province")
public class Province extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    private List<District> districts;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    private List<CertificateEmployee> certificateEmployees;
}
