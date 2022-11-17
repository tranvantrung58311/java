package com.example.l3_backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "certificate")
public class Certificate extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "code")
    private String code;
    //    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "start_day")
    private LocalDate startDay;
    @Column(name = "end_day")
    private LocalDate endDay;
}
