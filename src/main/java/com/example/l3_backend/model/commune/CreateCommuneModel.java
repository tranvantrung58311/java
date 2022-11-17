package com.example.l3_backend.model.commune;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommuneModel {
    private String name;
    private String code;
    private Long district_id;
}
