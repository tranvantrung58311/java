package com.example.l3_backend.model.commune;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommuneModel {
    private Long id;
    private String name;
    private String code;
    private Long district_id;
}
